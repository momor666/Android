package com.sbhachu.weatherapplication.data.parser;

import android.util.Log;

import com.sbhachu.weatherapplication.data.model.WeatherModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class WeatherDataParser {
    private static final String TAG = WeatherDataParser.class.getSimpleName();

    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String COORDINATES = "coord";
    private static final String LONGITUDE = "lon";
    private static final String LATITUDE = "lat";
    private static final String SYS = "sys";
    private static final String MESSAGE = "message";
    private static final String COUNTRY = "country";
    private static final String SUNRISE = "sunrise";
    private static final String SUNSET = "sunset";
    private static final String WEATHER = "weather";
    private static final String WEATHER_ID = "id";
    private static final String WEATHER_MAIN = "main";
    private static final String WEATHER_DESCRIPTION = "description";
    private static final String WEATHER_ICON = "icon";
    private static final String MAIN = "main";
    private static final String MAIN_TEMP = "temp";
    private static final String MAIN_TEMP_MIN = "temp_min";
    private static final String MAIN_TEMP_MAX = "temp_max";
    private static final String MAIN_PRESSURE = "pressure";
    private static final String MAIN_HUMIDITY = "humidity";
    private static final String WIND = "wind";
    private static final String WIND_SPEED = "speed";
    private static final String WIND_DEG = "deg";

    public WeatherModel parse(String jsonString) throws JSONException {
        JSONObject jsonObject = new JSONObject(jsonString);

        WeatherModel weatherData = new WeatherModel();

        try {
            weatherData.setId(jsonObject.getInt(ID));
            weatherData.setName(jsonObject.getString(NAME));

            JSONObject coordinates = jsonObject.getJSONObject(COORDINATES);
            weatherData.setLongitude(coordinates.getDouble(LONGITUDE));
            weatherData.setLatitude(coordinates.getDouble(LATITUDE));

            JSONObject sys = jsonObject.getJSONObject(SYS);
            weatherData.setMessage(sys.getDouble(MESSAGE));
            weatherData.setCountry(sys.getString(COUNTRY));
            weatherData.setSunrise(sys.getLong(SUNRISE));
            weatherData.setSunset(sys.getLong(SUNSET));

            JSONArray weather = jsonObject.getJSONArray(WEATHER);
            for (int i = 0; i < weather.length(); i++) {
                JSONObject weatherObject = weather.getJSONObject(i);
                weatherData.setWeatherId(weatherObject.getInt(WEATHER_ID));
                weatherData.setWeatherMain(weatherObject.getString(WEATHER_MAIN));
                weatherData.setWeatherDescription(weatherObject.getString(WEATHER_DESCRIPTION));
                weatherData.setWeatherIcon(weatherObject.getString(WEATHER_ICON));
            }

            JSONObject main = jsonObject.getJSONObject(MAIN);
            weatherData.setMainTemp(main.getDouble(MAIN_TEMP));
            weatherData.setMainTempMin(main.getDouble(MAIN_TEMP_MIN));
            weatherData.setMainTempMax(main.getDouble(MAIN_TEMP_MAX));
            weatherData.setMainPressure(main.getDouble(MAIN_PRESSURE));
            weatherData.setMainHumidity(main.getDouble(MAIN_HUMIDITY));

            JSONObject wind = jsonObject.getJSONObject(WIND);
            weatherData.setWindSpeed(wind.getDouble(WIND_SPEED));
            weatherData.setWindDeg(wind.getInt(WIND_DEG));
        } catch (JSONException e) {
            Log.e(TAG, "Weather data parsing error");
        }

        return weatherData;
    }
}
