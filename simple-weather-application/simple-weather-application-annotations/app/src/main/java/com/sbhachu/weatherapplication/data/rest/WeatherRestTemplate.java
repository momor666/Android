package com.sbhachu.weatherapplication.data.rest;

import com.sbhachu.weatherapplication.data.model.WeatherModel;

import org.androidannotations.annotations.rest.Get;
import org.androidannotations.annotations.rest.Rest;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

/**
 * Created by sbhachu on 20/10/2014.
 */
@Rest(converters = {MappingJackson2HttpMessageConverter.class})
public interface WeatherRestTemplate {

    @Get("http://api.openweathermap.org/data/2.5/weather?q={city},{country}&units=metric")
    public WeatherModel fetchWeatherData(String city, String country);
}
