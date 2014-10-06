package com.sbhachu.audioplayer.data.model;

/**
 * Created by sbhachu on 03/10/2014.
 */
public final class AudioPlayerState {

    public static final String FETCHING_TRACKS = "fetching_tracks";
    public static final String AUDIO_STOPPED = "audio_stopped";
    public static final String AUDIO_PLAYING = "audio_playing";
    public static final String AUDIO_PAUSED = "audio_paused";
    public static final String AUDIO_PREPARING = "audio_preparing";

    public static AudioPlayerState instance;

    public String currentState;

    // prevent manual instantiation of AudioPlayerState class
    private AudioPlayerState() {
    }

    public static AudioPlayerState getInstance() {
        if (instance == null) {
            instance = new AudioPlayerState();

            // set initial state
            instance.setCurrentState(FETCHING_TRACKS);
        }

        return instance;
    }

    public String getCurrentState() {
        return currentState;
    }

    public void setCurrentState(String currentState) {
        this.currentState = currentState;
    }

}
