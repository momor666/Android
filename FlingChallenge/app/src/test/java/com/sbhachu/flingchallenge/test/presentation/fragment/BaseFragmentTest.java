package com.sbhachu.flingchallenge.test.presentation.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import org.junit.After;
import org.robolectric.Robolectric;

public class BaseFragmentTest<T extends Fragment> {

    private static final String FRAGMENT_TAG = "image_list_fragment";

    protected FragmentActivity activity;
    protected T fragment;

    public void startFragment(T fragment) {
        this.fragment = fragment;
        activity = Robolectric.buildActivity(
                FragmentActivity.class).create().start().visible().get();
        FragmentManager manager = activity.getSupportFragmentManager();
        manager.beginTransaction().add(fragment, FRAGMENT_TAG).commit();
    }

    @After
    public void destroyFragment() {
        if (fragment != null) {
            FragmentManager manager = activity.getSupportFragmentManager();
            manager.beginTransaction().remove(fragment).commit();
            fragment = null;
            activity = null;
        }
    }
}