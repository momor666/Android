package com.sbhachu.flingchallenge.application;

import android.app.Application;

import com.sbhachu.flingchallenge.event.ApplicationEventBus;
import com.sbhachu.flingchallenge.network.RestClient;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EApplication;
import org.androidannotations.annotations.rest.RestService;

/**
 * Created by sbhachu on 08/01/2015.
 */
@EApplication
public class FlingChallengeApplication extends Application {

    @Bean
    public ApplicationEventBus eventBus;

    @RestService
    public RestClient restClient;

    public ApplicationEventBus getEventBus() {
        return eventBus;
    }

    public RestClient getRestClient() {
        return restClient;
    }

}
