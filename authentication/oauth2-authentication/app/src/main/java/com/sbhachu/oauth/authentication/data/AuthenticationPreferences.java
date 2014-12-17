package com.sbhachu.oauth.authentication.data;

import org.androidannotations.annotations.sharedpreferences.SharedPref;

import static org.androidannotations.annotations.sharedpreferences.SharedPref.Scope;

/**
 * Created by sbhachu on 13/12/2014.
 */
@SharedPref(value = Scope.UNIQUE)
public interface AuthenticationPreferences {

    public String clientId();

    public String secret();

    public String refreshToken();

    public String accessToken();
}
