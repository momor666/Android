package com.sbhachu.weatherapplication.service;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;

import com.sbhachu.weatherapplication.data.model.WeatherModel;
import com.sbhachu.weatherapplication.data.rest.WeatherRestTemplate;
import com.sbhachu.weatherapplication.util.NetworkStateUtil;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EIntentService;
import org.androidannotations.annotations.ServiceAction;
import org.androidannotations.annotations.rest.RestService;

/**
 * Created by sbhachu on 06/10/2014.
 */
@EIntentService
public class WeatherService extends IntentService {

    private static final String TAG = WeatherService.class.getSimpleName();

    private static final String OPEN_WEATHER_URL = "http://api.openweathermap.org/data/2.5/weather?q=%s,%s&units=metric";

    @Bean
    protected NetworkStateUtil networkStateUtil;

    @RestService
    protected WeatherRestTemplate weatherRestTemplate;

    public WeatherService() {
        super("WeatherService");
    }

    @ServiceAction
    protected void fetchWeatherData(ResultReceiver resultReceiver, String cityName, String countryCode) {

        if (networkStateUtil.isNetworkAvailable(this)) {
            WeatherModel model = weatherRestTemplate.fetchWeatherData(cityName, countryCode);

            if (model != null) {
                Bundle bundle = new Bundle();
                bundle.putParcelable("weatherModel", model);

                resultReceiver.send(1, bundle);
            }
        } else {
            resultReceiver.send(0, null);
        }
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        // TODO: NOTHING HERE
    }
}
