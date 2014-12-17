package com.sbhachu.oauth.authentication.presentation.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.TextView;

import com.sbhachu.oauth.authentication.R;
import com.sbhachu.oauth.authentication.data.model.User;
import com.sbhachu.oauth.authentication.service.AuthorizationService;
import com.sbhachu.oauth.authentication.service.DataService;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;

@EFragment(R.layout.fragment_placeholder)
public class PlaceholderFragment extends Fragment {

    private static final String TAG = PlaceholderFragment.class.getSimpleName();

    private static final String USER_EXTRA = "user";

    @ViewById(R.id.pf_tv_email_address)
    public TextView emailAddressTextView;

    @FragmentArg(USER_EXTRA)
    public User user;

    @Bean
    public AuthorizationService authorizationService;

    @Bean
    public DataService dataService;

    public static final PlaceholderFragment_ newInstance(User user) {
        PlaceholderFragment_ fragment = new PlaceholderFragment_();
        if (user != null) {
            Bundle args = new Bundle();
            args.putSerializable(USER_EXTRA, user);
            fragment.setArguments(args);
        }
        return fragment;
    }

    public PlaceholderFragment() {
        // Required empty public constructor
    }

    @AfterViews
    public void afterViews() {
        emailAddressTextView.setText(user.getEmail());


    }

    @Click(R.id.btn_get_users)
    public void getAllUsers() {
        authorizationService.refreshToken();
        dataService.getUserList();
    }
}
