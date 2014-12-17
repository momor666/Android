package com.sbhachu.oauth.authentication.network;

import com.sbhachu.oauth.authentication.application.OAuth2ClientApplication;
import com.sbhachu.oauth.authentication.data.AuthenticationPreferences_;
import com.sbhachu.oauth.authentication.util.Logger;

import org.androidannotations.annotations.App;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.rest.RestService;
import org.androidannotations.annotations.sharedpreferences.Pref;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

import static com.sbhachu.oauth.authentication.application.OAuth2ClientApplicationConfiguration.DEBUG;

/**
 * Created by sbhachu on 14/12/2014.
 */
@EBean
public class RequestHeaderManager {

    private static final String TAG = RequestHeaderManager.class.getSimpleName();

    private static final String AUTHORIZATION = "Authorization";
    private static final String BASIC = "Basic ";
    private static final String BEARER = "Bearer ";

    @App
    public OAuth2ClientApplication application;

    @Pref
    public AuthenticationPreferences_ authenticationPreferences;

    public void appendBasicAuthorizationHeader() {

        String clientId = authenticationPreferences.clientId().get();
        String secret = authenticationPreferences.secret().get();

        if (StringUtils.isNotEmpty(clientId) && StringUtils.isNotEmpty(secret)) {

            String preHash = new StringBuffer(clientId)
                    .append(":")
                    .append(secret)
                    .toString();

            String hash = new String(Base64.encodeBase64(preHash.getBytes()));

            application.getClient().setHeader(AUTHORIZATION, BASIC + hash);
        } else {
            if (DEBUG) {
                Logger.e(TAG, "Failed to Append Basic Authorization Header");
            }
        }
    }

    public void appendBearerAuthorizationHeader() {

        String accessToken = authenticationPreferences.accessToken().get();

        if (StringUtils.isNotEmpty(accessToken)) {
            application.getClient().setHeader(AUTHORIZATION, BEARER + accessToken);
        } else {
            if (DEBUG) {
                Logger.e(TAG, "Failed to Append Bearer Authorization Header");
            }
        }
    }
}
