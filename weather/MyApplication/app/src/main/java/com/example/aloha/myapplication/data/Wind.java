package com.example.aloha.myapplication.data;

import org.json.JSONObject;

/**
 * Created by Aloha on 1/3/2016.
 */
public class Wind implements JSONPopulator{
    private int chill;
    private int detrection;
    private int speed;

    public int getChill() {
        return chill;
    }

    public int getDetrection() {
        return detrection;
    }

    public int getSpeed() {
        return speed;
    }

    @Override
    public void populate(JSONObject data) {
        chill = data.optInt("chill");
        detrection = data.optInt("direction");
        speed = data.optInt("speed");
    }
}
