package com.example.aloha.myapplication;

import android.app.ProgressDialog;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aloha.myapplication.data.Channel;
import com.example.aloha.myapplication.data.Item;
import com.example.aloha.myapplication.data.Wind;
import com.example.aloha.myapplication.service.WeatherCallBack;
import com.example.aloha.myapplication.service.WeatherService;

public class MainActivity extends AppCompatActivity implements WeatherCallBack {

    private ImageView weatherIcon;
    private TextView temperTv;
    private TextView condiTv;
    private TextView locatTv;
    private TextView windTv;
    private TextView humidityTv;

    private WeatherService service;
    private ProgressDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        weatherIcon = (ImageView)findViewById(R.id.weaIv);
        temperTv = (TextView)findViewById(R.id.temper);
        condiTv = (TextView)findViewById(R.id.condition);
        locatTv = (TextView)findViewById(R.id.locat);
        windTv = (TextView)findViewById(R.id.windTv);
        humidityTv = (TextView)findViewById(R.id.humidityTv);

        service = new WeatherService(this);
        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading.....");



        service.refresWeather("Phuket, Thailand");
    }

    @Override
    public void serviceSuccess(Channel channel) {
        dialog.hide();
        Item item = channel.getItem();

        int resourceId = getResources().getIdentifier("drawable/icon_" + item.getCondition().getCode(), null, getPackageName());


        @SuppressWarnings("deprecation") Drawable weatherIconDraw = getResources().getDrawable(resourceId);

        weatherIcon.setImageDrawable(weatherIconDraw);
        temperTv.setText(item.getCondition().getTemperture() + "\u00B0" + channel.getUnits().getTemperTrue());
        condiTv.setText(item.getCondition().getDescription());
        locatTv.setText(service.getLocat());

    }

    @Override
    public void serViceFail(Exception exception) {
        dialog.hide();
        Toast.makeText(this,exception.getMessage(),Toast.LENGTH_LONG).show();

    }
}
