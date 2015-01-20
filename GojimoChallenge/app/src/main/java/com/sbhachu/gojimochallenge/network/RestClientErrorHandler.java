package com.sbhachu.gojimochallenge.network;

import android.util.Log;

import com.sbhachu.gojimochallenge.event.ApplicationEventBus;
import com.sbhachu.gojimochallenge.event.ApplicationEvents;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.api.rest.RestErrorHandler;
import org.springframework.core.NestedRuntimeException;

@EBean
public class RestClientErrorHandler implements RestErrorHandler {

    public static final String TAG = RestClientErrorHandler.class.getSimpleName();

    @Bean
    public ApplicationEventBus applicationEventBus;

    @Override
    public void onRestClientExceptionThrown(NestedRuntimeException e) {
        applicationEventBus.post(ApplicationEvents.errorResponseEvent(e.getMessage()));
    }

}
