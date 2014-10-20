package com.sbhachu.weatherapplication.util;

import com.sbhachu.weatherapplication.R;

import org.androidannotations.annotations.EBean;

/**
 * Created by sbhachu on 06/10/2014.
 */
@EBean
public class WeatherImageUtil {

    public int getImageResourceID(String imageReference) {

        if (imageReference.equals("01d"))
            return R.drawable.clear_day;
        else if (imageReference.equals("01n"))
            return R.drawable.clear_night;
        else if (imageReference.equals("02d") || imageReference.equals("03d") || imageReference.equals("04d"))
            return R.drawable.clouds_day;
        else if (imageReference.equals("02n") || imageReference.equals("03n") || imageReference.equals("04n"))
            return R.drawable.clouds_night;
        else if (imageReference.equals("09d") || imageReference.equals("10d"))
            return R.drawable.rain_day;
        else if (imageReference.equals("09n") || imageReference.equals("10n"))
            return R.drawable.rain_night;
        else if (imageReference.equals("11d"))
            return R.drawable.storm_day;
        else if (imageReference.equals("11n"))
            return R.drawable.storm_night;
        else if (imageReference.equals("13d"))
            return R.drawable.snow_day;
        else if (imageReference.equals("13n"))
            return R.drawable.snow_night;
        else if (imageReference.equals("50d"))
            return R.drawable.mist_day;
        else if (imageReference.equals("50n"))
            return R.drawable.mist_night;

        return -1;
    }
}
