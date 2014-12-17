package com.sbhachu.oauth.authentication.application;

import android.app.Application;
import android.content.res.Resources;

import com.sbhachu.oauth.authentication.R;
import com.sbhachu.oauth.authentication.data.AuthenticationPreferences_;
import com.sbhachu.oauth.authentication.network.RestClient;
import com.sbhachu.oauth.authentication.network.RestClientErrorHandler;
import com.sbhachu.oauth.authentication.util.Logger;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EApplication;
import org.androidannotations.annotations.rest.RestService;
import org.androidannotations.annotations.sharedpreferences.Pref;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static com.sbhachu.oauth.authentication.application.OAuth2ClientApplicationConfiguration.DEBUG;

/**
 * Created by sbhachu on 13/12/2014.
 */
@EApplication
public class OAuth2ClientApplication extends Application {

    private static final String TAG = OAuth2ClientApplication.class.getSimpleName();

    private static final String OAUTH_CLIENT_ID = "oauth.client.id";
    private static final String OAUTH_SECRET = "oauth.secret";

    @Pref
    public AuthenticationPreferences_ authenticationPreferences;

    @RestService
    public RestClient client;

    @Bean
    public RestClientErrorHandler errorHandler;

    @AfterInject
    public void afterInject() {
        client.setRestErrorHandler(errorHandler);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        if (StringUtils.isEmpty(authenticationPreferences.clientId().get()) ||
                StringUtils.isEmpty(authenticationPreferences.secret().get())) {
            loadOauthServerKeys();
        }
    }

    @Background
    protected void loadOauthServerKeys() {
        try {
            InputStream rawResource = getResources().openRawResource(R.raw.oauth2_client);
            Properties properties = new Properties();
            properties.load(rawResource);

            if (properties.containsKey(OAUTH_CLIENT_ID) && properties.containsKey(OAUTH_SECRET)) {
                authenticationPreferences.edit()
                        .clientId().put(properties.getProperty(OAUTH_CLIENT_ID))
                        .secret().put(properties.getProperty(OAUTH_SECRET))
                        .apply();
            } else {
                if (DEBUG) {
                    Logger.e(TAG, "Not able to load oauth clientId and secret");
                }
            }
        } catch (Resources.NotFoundException e) {
            Logger.e(TAG, "Properties file not found", e);
        } catch (IOException e) {
            Logger.e(TAG, "not able to open properties file", e);
        }
    }

    public RestClient getClient() {
        return client;
    }
}
