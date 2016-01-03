package com.example.aloha.myapplication.data;

import org.json.JSONObject;

/**
 * Created by Aloha on 12/31/2015.
 */
public class Condition implements JSONPopulator {
    private int code;
    private int temperture;
    private String description;

    public int getCode() {
        return code;
    }

    public int getTemperture() {
        return temperture;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public void populate(JSONObject data) {
        code = data.optInt("code");
        temperture = data.optInt("temp");
        description = data.optString("text");

    }
}
