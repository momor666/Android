package com.sbhachu.tddtemplate.application;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;

import org.androidannotations.annotations.EApplication;

/**
 * Created by sbhachu on 18/09/2014.
 */
@EApplication
public class TDDApplication extends Application {

    @Override
    public void onCreate()
    {
        init();
        super.onCreate();
    }

    private void init() {
    }

}
