package com.sbhachu.oauth.authentication.presentation.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;

import com.sbhachu.oauth.authentication.R;
import com.sbhachu.oauth.authentication.data.AuthenticationPreferences_;
import com.sbhachu.oauth.authentication.data.model.User;
import com.sbhachu.oauth.authentication.presentation.fragment.AccessFragment_;
import com.sbhachu.oauth.authentication.presentation.fragment.PlaceholderFragment_;
import com.sbhachu.oauth.authentication.service.AuthorizationService;
import com.sbhachu.oauth.authentication.util.Logger;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.sharedpreferences.Pref;
import org.apache.commons.lang3.StringUtils;

@EActivity(R.layout.activity_main)
@OptionsMenu(R.menu.menu_main)
public class MainActivity extends ActionBarActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Bean
    public AuthorizationService authorizationService;

    @Pref
    public AuthenticationPreferences_ authenticationPreferences;

    @ViewById(R.id.toolbar)
    public Toolbar toolbar;

    @AfterViews
    public void afterViews() {
        this.setSupportActionBar(toolbar);
        this.setTitle("OAuth 2 Demo");

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(AuthorizationService.ACTION_LOGIN);
        intentFilter.addAction(AuthorizationService.ACTION_REGISTER_USER);
        intentFilter.addAction(AuthorizationService.ACTION_AUTHENTICATED_USER);

        LocalBroadcastManager.getInstance(this)
                .registerReceiver(localBroadcastReceiver, intentFilter);

        if (StringUtils.isEmpty(authenticationPreferences.refreshToken().get())) {
            displayAccessFragment();
        } else {
            authorizationService.refreshToken();
            authorizationService.getAuthenticatedUser();
        }
    }

    public void displayAccessFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, AccessFragment_.newInstance())
                .commit();
    }

    public void displayPlaceholderFragment(User user) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, PlaceholderFragment_.newInstance(user))
                .commit();
    }

    private BroadcastReceiver localBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            if (action.equals(AuthorizationService.ACTION_LOGIN)) {
                Logger.i(TAG, "Successful Login");
                authorizationService.getAuthenticatedUser();
            } else if (action.equals(AuthorizationService.ACTION_REGISTER_USER)){
                Logger.i(TAG, "User Registered");
                User user = (User) intent.getSerializableExtra("user");
                displayPlaceholderFragment(user);
            } else if (action.equals(AuthorizationService.ACTION_AUTHENTICATED_USER)) {
                Logger.i(TAG, "Authenticated User");
                User user = (User) intent.getSerializableExtra("user");
                displayPlaceholderFragment(user);
            }
        }
    };

    @OptionsItem(R.id.action_logout)
    public void doLogout() {
        Logger.i(TAG, "Logout");
        authenticationPreferences.edit().refreshToken().remove().apply();
        displayAccessFragment();
    }
}
