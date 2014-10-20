package com.sbhachu.weatherapplication.presentation.activity;

import android.app.Activity;
import android.content.Intent;
import android.location.Address;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sbhachu.weatherapplication.R;
import com.sbhachu.weatherapplication.data.model.WeatherModel;
import com.sbhachu.weatherapplication.service.WeatherService;
import com.sbhachu.weatherapplication.service.receiver.WeatherServiceReceiver;
import com.sbhachu.weatherapplication.task.LocationTask;
import com.sbhachu.weatherapplication.util.LocationUtil;
import com.sbhachu.weatherapplication.util.Logger;
import com.sbhachu.weatherapplication.util.StringUtil;
import com.sbhachu.weatherapplication.util.WeatherImageUtil;
import com.squareup.picasso.Picasso;

import org.apache.commons.lang3.text.WordUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class MainActivity extends Activity implements LocationTask.LocationListener, WeatherServiceReceiver.WeatherServiceListener, View.OnClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm a", Locale.getDefault());

    private TextView locationTV;
    private ImageView imageView;
    private TextView temperatureTV;
    private TextView temperatureRangeTV;
    private TextView descriptionTV;
    private TextView windTV;
    private TextView humidityTV;
    private TextView pressureTV;
    private TextView sunriseTV;
    private TextView sunsetTV;

    private Button refreshButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        locationTV = (TextView) findViewById(R.id.location_tv);
        imageView = (ImageView) findViewById(R.id.weather_icon);
        temperatureTV = (TextView) findViewById(R.id.temperature_tv);
        temperatureRangeTV = (TextView) findViewById(R.id.temperature_range_tv);
        descriptionTV = (TextView) findViewById(R.id.description_tv);
        windTV = (TextView) findViewById(R.id.wind_tv);
        humidityTV = (TextView) findViewById(R.id.humidity_tv);
        pressureTV = (TextView) findViewById(R.id.pressure_tv);
        sunriseTV = (TextView) findViewById(R.id.sunrise_tv);
        sunsetTV = (TextView) findViewById(R.id.sunset_tv);

        refreshButton = (Button) findViewById(R.id.refresh_button);
        refreshButton.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        initialiseDataFetch();
    }

    private Intent weatherDataIntent(String city, String countryCode) {
        WeatherServiceReceiver weatherServiceReceiver = new WeatherServiceReceiver(new Handler());
        weatherServiceReceiver.setListener(this);

        Intent intent = new Intent(this, WeatherService.class);
        intent.putExtra(WeatherService.RECEIVER_EXTRA, weatherServiceReceiver);
        intent.putExtra(WeatherService.CITY_EXTRA, city);
        intent.putExtra(WeatherService.COUNTRY_CODE_EXTRA, countryCode);

        return intent;
    }

    @Override
    public void onLocationResolved(Address address) {
        if (address != null) {
            String locality = address.getLocality();
            String countryCode = address.getCountryCode();

            startService(weatherDataIntent(locality, countryCode));
        }
    }

    @Override
    public void onReceiveResult(int resultCode, Bundle resultData) {
        switch (resultCode) {
            case 0:
                Toast.makeText(this, "Unable to fetch weather data, please check your network connection.", Toast.LENGTH_LONG).show();
                break;
            case 1:
                WeatherModel model = (WeatherModel) resultData.get("weatherModel");

                locationTV.setText(model.getName() + ", " + model.getCountry());
                Picasso.with(this).load(WeatherImageUtil.getImageResourceID(model.getWeatherIcon())).resize(350, 350).centerInside().into(imageView);
                temperatureTV.setText(model.getMainTemp() + " °C");
                temperatureRangeTV.setText("H: " + model.getMainTempMax() + " °C  /  L: " + model.getMainTempMin() + " °C");
                descriptionTV.setText(WordUtils.capitalize(model.getWeatherDescription()));

                windTV.setText(model.getWindSpeed() + " m/s   (" + model.getWindDeg() + "°)");
                humidityTV.setText(model.getMainHumidity() + " %");
                pressureTV.setText(model.getMainPressure() + " hpa");

                Date sunrise = new Date(model.getSunrise() * 1000);
                Date sunset = new Date(model.getSunset() * 1000);

                sunriseTV.setText(timeFormat.format(sunrise));
                sunsetTV.setText(timeFormat.format(sunset));

                break;
            default:
                break;
        }
    }

    private void initialiseDataFetch() {
        Location location = LocationUtil.getLocation(this);
        LocationTask locationTask = new LocationTask(getApplicationContext(), this);
        locationTask.execute(location);
    }

    @Override
    public void onClick(View view) {
        if (view.equals(refreshButton)) {
            Toast.makeText(this, "Refreshing data...", Toast.LENGTH_LONG).show();
            initialiseDataFetch();
        }
    }
}
