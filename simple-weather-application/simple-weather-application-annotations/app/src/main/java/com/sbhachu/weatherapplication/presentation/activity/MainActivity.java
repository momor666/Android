package com.sbhachu.weatherapplication.presentation.activity;

import android.app.Activity;
import android.location.Address;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sbhachu.weatherapplication.R;
import com.sbhachu.weatherapplication.data.model.Weather;
import com.sbhachu.weatherapplication.data.model.WeatherModel;
import com.sbhachu.weatherapplication.service.WeatherService_;
import com.sbhachu.weatherapplication.service.receiver.WeatherServiceReceiver;
import com.sbhachu.weatherapplication.task.LocationTask;
import com.sbhachu.weatherapplication.util.LocationUtil;
import com.sbhachu.weatherapplication.util.WeatherImageUtil;
import com.squareup.picasso.Picasso;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.apache.commons.lang3.text.WordUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@EActivity(R.layout.activity_main)
public class MainActivity extends Activity implements LocationTask.LocationListener, WeatherServiceReceiver.WeatherServiceListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm a", Locale.getDefault());

    @ViewById(R.id.location_tv)
    protected TextView locationTV;

    @ViewById(R.id.weather_icon)
    protected ImageView imageView;

    @ViewById(R.id.temperature_tv)
    protected TextView temperatureTV;

    @ViewById(R.id.temperature_range_tv)
    protected TextView temperatureRangeTV;

    @ViewById(R.id.description_tv)
    protected TextView descriptionTV;

    @ViewById(R.id.wind_tv)
    protected TextView windTV;

    @ViewById(R.id.humidity_tv)
    protected TextView humidityTV;

    @ViewById(R.id.pressure_tv)
    protected TextView pressureTV;

    @ViewById(R.id.sunrise_tv)
    protected TextView sunriseTV;

    @ViewById(R.id.sunset_tv)
    protected TextView sunsetTV;

    @Bean
    protected LocationUtil locationUtil;

    @Bean
    protected WeatherImageUtil weatherImageUtil;

    @AfterViews
    protected void afterViews() {
        initialiseDataFetch();
    }

    private void fetchWeatherData(String city, String countryCode) {
        WeatherServiceReceiver weatherServiceReceiver = new WeatherServiceReceiver(new Handler());
        weatherServiceReceiver.setListener(this);

        WeatherService_.intent(getApplication()).fetchWeatherData(weatherServiceReceiver, city, countryCode).start();
    }

    @Override
    public void onLocationResolved(Address address) {
        if (address != null) {
            String locality = address.getLocality();
            String countryCode = address.getCountryCode();

            fetchWeatherData(locality, countryCode);
        }
        else {
            Toast.makeText(this, "Unable to resolve your location.", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onReceiveResult(int resultCode, Bundle resultData) {
        switch (resultCode) {
            case 0:
                Toast.makeText(this, "Weather data request failed.", Toast.LENGTH_LONG).show();
                break;
            case 1:
                Toast.makeText(this, "Network error, please try again later.", Toast.LENGTH_LONG).show();
                break;
            case 2:
                final WeatherModel model = (WeatherModel) resultData.get("weatherModel");
                final Weather weather = model.getWeather().get(0);

                locationTV.setText(model.getName() + ", " + model.getWeatherSys().getCountry());
                Picasso.with(this).load(weatherImageUtil.getImageResourceID(weather.getIcon())).resize(350, 350).centerInside().into(imageView);
                temperatureTV.setText(model.getWeatherMain().getTemp() + " °C");
                temperatureRangeTV.setText("H: " + model.getWeatherMain().getTempMax() + " °C  /  L: " + model.getWeatherMain().getTempMin() + " °C");
                descriptionTV.setText(WordUtils.capitalize(weather.getDescription()));

                windTV.setText(model.getWeatherWind().getSpeed() + " m/s   (" + model.getWeatherWind().getDeg() + "°)");
                humidityTV.setText(model.getWeatherMain().getHumidity() + " %");
                pressureTV.setText(model.getWeatherMain().getPressure() + " hpa");

                Date sunrise = new Date(model.getWeatherSys().getSunrise() * 1000);
                Date sunset = new Date(model.getWeatherSys().getSunset() * 1000);

                sunriseTV.setText(timeFormat.format(sunrise));
                sunsetTV.setText(timeFormat.format(sunset));

                break;
            default:
                break;
        }
    }

    @Background
    protected void initialiseDataFetch() {
        Location location = locationUtil.getLocation(this);
        LocationTask locationTask = new LocationTask(getApplicationContext(), this);
        locationTask.execute(location);
    }

    @Click(R.id.refresh_button)
    public void onRefresh() {
        Toast.makeText(this, "Refreshing data...", Toast.LENGTH_LONG).show();
        initialiseDataFetch();
    }
}
