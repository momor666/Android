package com.sbhachu.oauth.authentication.service;

import android.content.Context;
import android.widget.Toast;

import com.sbhachu.oauth.authentication.application.OAuth2ClientApplication;
import com.sbhachu.oauth.authentication.application.OAuth2ClientApplicationConfiguration;
import com.sbhachu.oauth.authentication.data.AuthenticationPreferences_;
import com.sbhachu.oauth.authentication.data.OAuth2AccessToken;
import com.sbhachu.oauth.authentication.data.dto.RegisteredUserDTO;
import com.sbhachu.oauth.authentication.data.model.User;
import com.sbhachu.oauth.authentication.network.RequestHeaderManager;
import com.sbhachu.oauth.authentication.network.RestClient;
import com.sbhachu.oauth.authentication.network.RestClientErrorHandler;
import com.sbhachu.oauth.authentication.util.Logger;
import com.sbhachu.oauth.authentication.util.Md5EncoderUtil;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.App;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.rest.RestService;
import org.androidannotations.annotations.sharedpreferences.Pref;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static com.sbhachu.oauth.authentication.application.OAuth2ClientApplicationConfiguration.DEBUG;

/**
 * Created by sbhachu on 13/12/2014.
 */
@EBean
public class DataService {

    private static final String TAG = DataService.class.getSimpleName();

    @RootContext
    public Context context;

    @App
    public OAuth2ClientApplication application;

    @Bean
    public RestClientErrorHandler errorHandler;

    @Bean
    public RequestHeaderManager manager;

    @Pref
    public AuthenticationPreferences_ authenticationPreferences;

    @Background(serial = "OAUTH")
    public void getUserList() {

        manager.appendBearerAuthorizationHeader();

        List<User> response = application.getClient().getUserList();

        if (response != null) {
            Logger.i(TAG, response.toString());

            showResponse(response);
        }
    }

    @UiThread
    public void showResponse(List<User> response) {
        Toast.makeText(context, response.toString(), Toast.LENGTH_LONG).show();
    }

}
