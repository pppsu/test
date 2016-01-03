package com.example.aloha.myapplication.data;

import org.json.JSONObject;

/**
 * Created by Aloha on 12/31/2015.
 */
public class Units implements JSONPopulator {
     private String temperTrue;

    public String getTemperTrue() {
        return temperTrue;
    }

    @Override
    public void populate(JSONObject data) {
        temperTrue = data.optString("temperature");
    }
}
