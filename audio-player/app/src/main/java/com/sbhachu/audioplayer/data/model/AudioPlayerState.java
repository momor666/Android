package com.sbhachu.audioplayer.data.model;

import org.androidannotations.annotations.EBean;

import static org.androidannotations.annotations.EBean.Scope;

/**
 * Created by sbhachu on 25/12/2014.
 */
@EBean(scope = Scope.Singleton)
public class AudioPlayerState {

    public static final String AUDIO_STOPPED = "audio_stopped";
    public static final String AUDIO_PLAYING = "audio_playing";
    public static final String AUDIO_PAUSED = "audio_paused";
    public static final String AUDIO_PREPARING = "audio_preparing";

    public static final String AUDIO_SHUFFLE = "audio_shuffle";
    public static final String AUDIO_REPEAT = "audio_repeat";
    public static final String AUDIO_REPEAT_ONE = "audio_repeat_one";

    private String currentState = AUDIO_STOPPED;

    public AudioPlayerState() {
    }

    public String getCurrentState() {
        return currentState;
    }

    public void setCurrentState(String currentState) {
        this.currentState = currentState;
    }
}
