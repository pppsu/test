package com.example.aloha.myapplication.service;

import android.net.Uri;
import android.os.AsyncTask;

import com.example.aloha.myapplication.data.Channel;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;

import java.io.InputStream;
import java.io.InputStreamReader;

import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Aloha on 12/31/2015.
 */
public class WeatherService {
    private WeatherCallBack callBack;
    private String locat;
    private Exception error;

    public WeatherService(WeatherCallBack callBack){
        this.callBack = callBack;
    }

    public String getLocat() {
        return locat;
    }

    public void refresWeather(String l){
        this.locat =l;
        new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... strings) {
                String YQL = String.format("select * from weather.forecast where woeid in (select woeid from geo.places(1) where text=\"%s\") and u ='c'",strings[0]);
                String endpoint = String.format("https://query.yahooapis.com/v1/public/yql?q=%s&format=json", Uri.encode(YQL));

                try {
                    URL url = new URL(endpoint);
                    URLConnection connection =  url.openConnection();
                    InputStream inputStream =connection.getInputStream();

                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                    StringBuilder result = new StringBuilder();
                    String line;
                    while((line = reader.readLine()) != null){
                        result.append(line);

                    }
                    return result.toString();
                } catch (Exception e) {
                    error = e;
                }

                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                if (s == null && error != null){
                    callBack.serViceFail(error);
                    return;
                }

                try {

                    JSONObject data = new JSONObject(s);
                    JSONObject queryResult =data.optJSONObject("query");
                    int count = queryResult.optInt("count");
                    if (count == 0){
                        callBack.serViceFail(new LocationException("No weather information found for "+locat));
                        return;
                    }
                    Channel channel = new Channel();
                    channel.populate(queryResult.optJSONObject("results").optJSONObject("channel"));
                    callBack.serviceSuccess(channel);
                } catch (JSONException e) {
                    callBack.serViceFail(e);
                }

            }
        }.execute(l);

    }

    public class LocationException extends Exception{
        public LocationException(String detailMessage) {
            super(detailMessage);
        }
    }
}

