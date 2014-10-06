package com.sbhachu.audioplayer.application;

import android.app.Application;

import com.sbhachu.audioplayer.data.model.AudioTrack;

/**
 * Created by sbhachu on 18/09/2014.
 */
public final class SimpleAudioApplication extends Application {

    private static SimpleAudioApplication instance;

    @Override
    public void onCreate()
    {
        super.onCreate();
        instance = this;
    }

    public static SimpleAudioApplication getInstance() {
        return instance;
    }

}
