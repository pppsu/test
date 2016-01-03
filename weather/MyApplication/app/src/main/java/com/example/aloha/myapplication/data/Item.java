package com.example.aloha.myapplication.data;

import org.json.JSONObject;

/**
 * Created by Aloha on 12/31/2015.
 */
public class Item implements JSONPopulator {
    private Condition condition;

    public Condition getCondition() {
        return condition;
    }

    @Override
    public void populate(JSONObject data) {
        condition =new Condition();
        condition.populate(data.optJSONObject("condition"));

    }
}
