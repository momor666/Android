package com.sbhachu.fanatixchallenge.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sbhachu.fanatixchallenge.application.ApplicationConfiguration;
import com.sbhachu.fanatixchallenge.data.FriendJsonDeserializer;
import com.sbhachu.fanatixchallenge.network.IRestTemplate;

import org.androidannotations.annotations.EBean;

import java.util.Map;

import retrofit.RestAdapter;
import retrofit.converter.Converter;
import retrofit.converter.GsonConverter;

@EBean
public class FanatixDataService {

    private IRestTemplate restTemplate;

    public FanatixDataService() {

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(ApplicationConfiguration.BASE_URL)
                .setConverter(getGsonConverter())
                .build();

        restTemplate = restAdapter.create(IRestTemplate.class);
    }

    private Converter getGsonConverter() {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Map.class, new FriendJsonDeserializer())
                .create();
        return new GsonConverter(gson);
    }

    public IRestTemplate getRestTemplate() {
        return restTemplate;
    }
}
