package com.sbhachu.weatherapplication.application;

import android.app.Application;

/**
 * Created by sbhachu on 06/10/2014.
 */
public class SimpleWeatherApplication extends Application {

    private static SimpleWeatherApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static SimpleWeatherApplication getInstance() {
        return instance;
    }
}
