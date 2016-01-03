package com.example.aloha.myapplication.service;

import com.example.aloha.myapplication.data.Channel;

/**
 * Created by Aloha on 12/31/2015.
 */
public interface WeatherCallBack {
    void serviceSuccess(Channel channel);
    void serViceFail(Exception execption);

}
