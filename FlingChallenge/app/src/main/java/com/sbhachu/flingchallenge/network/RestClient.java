package com.sbhachu.flingchallenge.network;

import com.sbhachu.flingchallenge.data.model.ImageItem;
import com.sbhachu.flingchallenge.data.model.dto.ImageItemDTO;

import org.androidannotations.annotations.rest.Accept;
import org.androidannotations.annotations.rest.Get;
import org.androidannotations.annotations.rest.Rest;
import org.androidannotations.api.rest.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.util.List;

/**
 * RestClient Interface
 * Android Annotations Library uses Spring for Android (RestTemplate) library under the hood.
 * The generated class is in the following directory:
 * /build/generated/source/apt/debug/com.sbhachu.flingchallege/network/RestClient_
 */
@Rest(rootUrl = "http://challenge.superfling.com", converters = {MappingJackson2HttpMessageConverter.class})
public interface RestClient {

    @Get("/")
    @Accept(MediaType.APPLICATION_JSON)
    public List<ImageItemDTO> fetchImageList();
}
