package com.sbhachu.weatherapplication.service;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.util.Log;

import com.sbhachu.weatherapplication.data.model.WeatherModel;
import com.sbhachu.weatherapplication.data.parser.WeatherDataParser;
import com.sbhachu.weatherapplication.service.receiver.WeatherServiceReceiver;
import com.sbhachu.weatherapplication.util.HttpUtil;
import com.sbhachu.weatherapplication.util.Logger;
import com.sbhachu.weatherapplication.util.NetworkStateUtil;

import org.json.JSONException;

import java.io.IOException;

/**
 * Created by sbhachu on 06/10/2014.
 */
public class WeatherService extends IntentService {

    private static final String TAG = WeatherService.class.getSimpleName();

    public static final String RECEIVER_EXTRA = "com.sbhachu.weatherapplication.extra.RECEIVER";
    public static final String CITY_EXTRA = "com.sbhachu.weatherapplication.extra.CITY";
    public static final String COUNTRY_CODE_EXTRA = "com.sbhachu.weatherapplication.extra.COUNTY_CODE";

    private static final String OPEN_WEATHER_URL = "http://api.openweathermap.org/data/2.5/weather?q=%s,%s&units=metric";

    public WeatherService() {
        super("WeatherService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        final ResultReceiver resultReceiver = intent.getParcelableExtra(RECEIVER_EXTRA);
        final String cityName = intent.getStringExtra(CITY_EXTRA);
        final String countryCode = intent.getStringExtra(COUNTRY_CODE_EXTRA);

        String url = String.format(OPEN_WEATHER_URL, cityName, countryCode);

        if (NetworkStateUtil.isNetworkAvailable(this)) {
            HttpUtil httpUtil = new HttpUtil();
            try
            {
                String jsonData = httpUtil.getWeatherData( url );

                if ( !jsonData.isEmpty() )
                {
                    WeatherDataParser dataParser = new WeatherDataParser();
                    WeatherModel weatherModel = dataParser.parse(jsonData);

                    Bundle bundle = new Bundle();
                    bundle.putParcelable("weatherModel", weatherModel);

                    resultReceiver.send(1, bundle);
                }
                else
                {
                    Log.e(TAG, "No data to parse");
                }
            }
            catch ( IOException e )
            {
                e.printStackTrace();
            }
            catch ( JSONException e )
            {
                e.printStackTrace();
            }
        }
        else {
            resultReceiver.send(0, null);
        }
    }
}
