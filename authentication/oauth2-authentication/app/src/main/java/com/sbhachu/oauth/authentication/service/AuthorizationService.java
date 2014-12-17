package com.sbhachu.oauth.authentication.service;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

import com.sbhachu.oauth.authentication.application.OAuth2ClientApplication;
import com.sbhachu.oauth.authentication.data.AuthenticationPreferences_;
import com.sbhachu.oauth.authentication.data.OAuth2AccessToken;
import com.sbhachu.oauth.authentication.data.dto.RegisteredUserDTO;
import com.sbhachu.oauth.authentication.data.model.User;
import com.sbhachu.oauth.authentication.network.RequestHeaderManager;
import com.sbhachu.oauth.authentication.network.RestClientErrorHandler;
import com.sbhachu.oauth.authentication.util.Logger;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.App;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;
import org.androidannotations.annotations.sharedpreferences.Pref;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;

/**
 * Created by sbhachu on 14/12/2014.
 */
@EBean
public class AuthorizationService {

    private static final String TAG = AuthorizationService.class.getSimpleName();

    public static final String ACTION_LOGIN = "on_login";

    public static final String ACTION_REGISTER_USER = "on_register_user";

    public static final String ACTION_REFRESH_TOKEN = "on_refresh_token";

    public static final String ACTION_AUTHENTICATED_USER = "on_authenticated_user";

    @RootContext
    public Context context;

    @App
    public OAuth2ClientApplication application;

    @Bean
    public RequestHeaderManager manager;

    @Pref
    public AuthenticationPreferences_ authenticationPreferences;

    @Background
    public void registerUser(User user) {
        manager.appendBasicAuthorizationHeader();

        ResponseEntity<RegisteredUserDTO> response = application.getClient().registerUser(user);

        if (response != null) {
            Logger.i("BLAH", response.getBody().toString());

            if (StringUtils.isNotEmpty(response.getBody().getAccessToken().getRefreshToken())) {
                authenticationPreferences.edit().refreshToken()
                        .put(response.getBody().getAccessToken().getRefreshToken()).apply();

                Intent intent = new Intent(ACTION_REGISTER_USER);
                if (response.getBody().getUser() != null) {
                    intent.putExtra("user", response.getBody().getUser());

                    LocalBroadcastManager.getInstance(context)
                            .sendBroadcast(intent);
                }
            }
        }
    }

    @Background
    public void login(String emailAddress, String password) {

        manager.appendBasicAuthorizationHeader();

        ResponseEntity<OAuth2AccessToken> response = application.getClient().login(emailAddress, password);

        if (response != null) {
            OAuth2AccessToken token = response.getBody();

            if (StringUtils.isNotEmpty(token.getRefreshToken())) {
                authenticationPreferences.edit()
                        .refreshToken().put(token.getRefreshToken())
                        .accessToken().put(token.getAccessToken())
                        .apply();

                LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent(ACTION_LOGIN));
            }
        }
    }

    @Background(serial = "OAUTH")
    public void refreshToken() {
        manager.appendBasicAuthorizationHeader();

        String refreshToken = authenticationPreferences.refreshToken().get();

        ResponseEntity<OAuth2AccessToken> response = null;

        if (StringUtils.isNotEmpty(refreshToken)) {
            response = application.getClient().refreshToken(refreshToken);
        }

        if (response != null) {
            OAuth2AccessToken token = response.getBody();

            if (StringUtils.isNotEmpty(token.getAccessToken())) {
                authenticationPreferences.edit()
                        .accessToken().put(token.getAccessToken())
                        .apply();
            }
        }
    }

    @Background(serial = "OAUTH")
    public void getAuthenticatedUser() {

        manager.appendBearerAuthorizationHeader();

        ResponseEntity<User> authenticatedUserResponse = application.getClient().getAuthenticatedUser();

        if (authenticatedUserResponse != null) {
            Intent intent = new Intent(ACTION_AUTHENTICATED_USER);

            if (authenticatedUserResponse.getBody() != null) {
                intent.putExtra("user", authenticatedUserResponse.getBody());

                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
            }
        }
    }
}
