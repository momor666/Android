package com.sbhachu.flingchallenge.presentation.fragment;

import android.support.v4.app.Fragment;

import com.sbhachu.flingchallenge.event.ApplicationEventBus;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;

/**
 * BaseFragment class handles the EventBus register/unregister
 */
@EFragment
public class BaseFragment extends Fragment {

    @Bean
    public ApplicationEventBus eventBus;

    public void onEvent(Object e) {
    }

    @Override
    public void onResume() {
        eventBus.register(this);
        super.onResume();
    }

    @Override
    public void onPause() {
        eventBus.unregister(this);
        super.onPause();
    }
}
