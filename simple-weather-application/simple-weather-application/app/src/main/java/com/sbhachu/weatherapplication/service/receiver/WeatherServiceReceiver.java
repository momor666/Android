package com.sbhachu.weatherapplication.service.receiver;

import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;

public class WeatherServiceReceiver extends ResultReceiver {

    private WeatherServiceListener weatherServiceListener;

    public WeatherServiceReceiver(Handler handler) {
        super(handler);
    }

    public void setListener(WeatherServiceListener listener) {
        this.weatherServiceListener = listener;
    }

    @Override
    protected void onReceiveResult(int resultCode, Bundle resultData) {
        if (weatherServiceListener != null)
            weatherServiceListener.onReceiveResult(resultCode, resultData);
    }

    public interface WeatherServiceListener {
        void onReceiveResult(int resultCode, Bundle resultData);
    }
}
