package com.sbhachu.oauth.authentication.network;

import com.sbhachu.oauth.authentication.application.OAuth2ClientApplicationConfiguration;
import com.sbhachu.oauth.authentication.data.OAuth2AccessToken;
import com.sbhachu.oauth.authentication.data.dto.RegisteredUserDTO;
import com.sbhachu.oauth.authentication.data.model.User;

import org.androidannotations.annotations.rest.Accept;
import org.androidannotations.annotations.rest.Get;
import org.androidannotations.annotations.rest.Post;
import org.androidannotations.annotations.rest.RequiresHeader;
import org.androidannotations.annotations.rest.Rest;
import org.androidannotations.api.rest.MediaType;
import org.androidannotations.api.rest.RestClientErrorHandling;
import org.androidannotations.api.rest.RestClientHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Created by sbhachu on 13/12/2014.
 */
@Rest(rootUrl = OAuth2ClientApplicationConfiguration.BASE_URL, converters = {MappingJackson2HttpMessageConverter.class})
public interface RestClient extends RestClientErrorHandling {

    @Post("/api/v1/register")
    @Accept(MediaType.APPLICATION_JSON)
    @RequiresHeader("Authorization")
    public ResponseEntity<RegisteredUserDTO> registerUser(User user);

    @Get("/oauth/token?grant_type=refresh_token&refresh_token={refreshToken}")
    @Accept(MediaType.APPLICATION_JSON)
    @RequiresHeader("Authorization")
    public ResponseEntity<OAuth2AccessToken> refreshToken(String refreshToken);

    @Get("/oauth/token?grant_type=password&username={emailAddress}&password={password}")
    @Accept(MediaType.APPLICATION_JSON)
    @RequiresHeader("Authorization")
    public ResponseEntity<OAuth2AccessToken> login(String emailAddress, String password);

    @Get("/api/v1/users/me")
    @Accept(MediaType.APPLICATION_JSON)
    @RequiresHeader("Authorization")
    public ResponseEntity<User> getAuthenticatedUser();

    @Get("/api/v1/users")
    @Accept(MediaType.APPLICATION_JSON)
    @RequiresHeader("Authorization")
    public List<User> getUserList();

    public RestTemplate getRestTemplate();
    public void setRestTemplate(RestTemplate restTemplate);

    public void setHeader(String name, String value);
    public String getHeader(String name);

}
