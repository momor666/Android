package com.sbhachu.gojimochallenge.network;

import com.sbhachu.gojimochallenge.application.ApplicationConfiguration;
import com.sbhachu.gojimochallenge.data.QualificationList;

import org.androidannotations.annotations.rest.Accept;
import org.androidannotations.annotations.rest.Get;
import org.androidannotations.annotations.rest.Rest;
import org.androidannotations.api.rest.MediaType;
import org.androidannotations.api.rest.RestClientErrorHandling;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

/**
 * RestClient Interface
 * Android Annotations Library uses Spring for Android (RestTemplate) library under the hood.
 */
@Rest(rootUrl = ApplicationConfiguration.BASE_URL,
      converters = {MappingJackson2HttpMessageConverter.class})
public interface RestClient extends RestClientErrorHandling {

    @Get("/api/v4/qualifications")
    @Accept(MediaType.APPLICATION_JSON)
    public ResponseEntity<QualificationList> fetchQualifications();
}
