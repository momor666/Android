package com.sbhachu.audioplayer.service;

import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.IBinder;
import android.os.PowerManager;
import android.support.v4.content.LocalBroadcastManager;
import android.widget.Toast;

import com.sbhachu.audioplayer.data.AudioTrackRetriever;
import com.sbhachu.audioplayer.data.model.AudioPlayerState;
import com.sbhachu.audioplayer.data.model.AudioTrack;
import com.sbhachu.audioplayer.task.PrepareAudioTask;
import com.sbhachu.audioplayer.util.Logger;

import java.io.IOException;

/**
 * Created by sbhachu on 03/10/2014.
 */
public class AudioService extends Service implements MediaPlayer.OnCompletionListener,
        MediaPlayer.OnPreparedListener,
        MediaPlayer.OnErrorListener,
        MediaPlayer.OnSeekCompleteListener,
        PrepareAudioTask.AudioTrackRetrieverListener {

    public static final String TAG = AudioService.class.getSimpleName();

    public static final String ACTION_TOGGLE_PLAY = "com.sbhachu.audioplayer.action.TOGGLE_PLAYBACK";
    public static final String ACTION_PLAY = "com.sbhachu.audioplayer.action.PLAY";
    public static final String ACTION_PAUSE = "com.sbhachu.audioplayer.action.PAUSE";
    public static final String ACTION_STOP = "com.sbhachu.audioplayer.action.STOP";
    public static final String ACTION_SKIP = "com.sbhachu.audioplayer.action.SKIP";
    public static final String ACTION_REWIND = "com.sbhachu.audioplayer.action.REWIND";

    private MediaPlayer mediaPlayer;

    private AudioPlayerState audioPlayerState = AudioPlayerState.getInstance();

    private AudioTrackRetriever audioTrackRetriever;

    private AudioTrack currentTrack;

    private AudioManager audioManager;

    private Handler positionHandler = new Handler();

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String action = intent.getAction();

        if (action.equals(ACTION_TOGGLE_PLAY)) executeTogglePlaybackRequest();
        else if (action.equals(ACTION_PLAY)) executePlayAudioRequest();
        else if (action.equals(ACTION_PAUSE)) executePauseAudioRequest();
        else if (action.equals(ACTION_SKIP)) executeSkipAudioRequest();
        else if (action.equals(ACTION_STOP)) executeStopAudioRequest();
        else if (action.equals(ACTION_REWIND)) executeRewindAudioRequest();

        return START_NOT_STICKY;
    }

    @Override
    public void onCreate() {
        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        audioTrackRetriever = new AudioTrackRetriever(getContentResolver());
        PrepareAudioTask prepareAudioTask = new PrepareAudioTask(audioTrackRetriever, this);
        prepareAudioTask.execute();
    }

    @Override
    public void onDestroy() {
        audioPlayerState.setCurrentState(AudioPlayerState.AUDIO_STOPPED);
        releaseResources(true);
    }

    @Override
    public void onAudioTracksRetrieverCompleted() {
        audioPlayerState.setCurrentState(AudioPlayerState.AUDIO_STOPPED);
        // could implement an auto play feature here, i.e. when track list
        // retrieval completes, play a track.
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        playNextAudioTrack();
    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        Toast.makeText(getApplicationContext(), "Media player error!", Toast.LENGTH_SHORT).show();
        Logger.e(TAG, "ERROR[" + String.valueOf(what) + " - " + String.valueOf(extra) + "]");
        audioPlayerState.setCurrentState(AudioPlayerState.AUDIO_STOPPED);
        releaseResources(true);
        return true;
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        audioPlayerState.setCurrentState(AudioPlayerState.AUDIO_PLAYING);

        if (!mediaPlayer.isPlaying()) {
            broadcastPlayerControlStatus(false, false);
            mediaPlayer.start();
            positionHandler.postDelayed(updateCurrentTrackPositionRunnable, 250);
        }
    }

    @Override
    public void onSeekComplete(MediaPlayer mp) {

    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void createMediaPlayer() {
        if (mediaPlayer == null) {
            mediaPlayer = new MediaPlayer();

            mediaPlayer.setWakeMode(getApplicationContext(), PowerManager.PARTIAL_WAKE_LOCK);

            mediaPlayer.setOnPreparedListener(this);
            mediaPlayer.setOnCompletionListener(this);
            mediaPlayer.setOnErrorListener(this);
        } else {
            mediaPlayer.reset();
        }
    }

    /**
     * Media Player Control Methods
     */
    private void executeTogglePlaybackRequest() {
        String state = audioPlayerState.getCurrentState();

        if (state.equals(AudioPlayerState.AUDIO_PAUSED) || state.equals(AudioPlayerState.AUDIO_STOPPED)) {
            executePlayAudioRequest();
        } else {
            executePauseAudioRequest();
        }
    }

    private void executePlayAudioRequest() {
        if (audioPlayerState.getCurrentState().equals(AudioPlayerState.FETCHING_TRACKS)) {
            return;
        }

        if (audioPlayerState.getCurrentState().equals(AudioPlayerState.AUDIO_STOPPED)) {
            playNextAudioTrack();
        } else if (audioPlayerState.getCurrentState().equals(AudioPlayerState.AUDIO_PAUSED)) {
            audioPlayerState.setCurrentState(AudioPlayerState.AUDIO_PLAYING);

            if (!mediaPlayer.isPlaying()) {
                broadcastPlayerControlStatus(false, false);
                mediaPlayer.start();
                positionHandler.postDelayed(updateCurrentTrackPositionRunnable, 250);
            }
        }
    }

    private void executePauseAudioRequest() {
        if (audioPlayerState.getCurrentState().equals(AudioPlayerState.FETCHING_TRACKS)) {
            return;
        }

        if (audioPlayerState.getCurrentState().equals(AudioPlayerState.AUDIO_PLAYING)) {
            audioPlayerState.setCurrentState(AudioPlayerState.AUDIO_PAUSED);
            broadcastPlayerControlStatus(true, false);
            mediaPlayer.pause();
        }
    }

    private void executeRewindAudioRequest() {
        String state = audioPlayerState.getCurrentState();

        if (state.equals(AudioPlayerState.AUDIO_PLAYING) || state.equals(AudioPlayerState.AUDIO_PAUSED)) {
            mediaPlayer.seekTo(0);
        }
    }

    private void executeSkipAudioRequest() {
        String state = audioPlayerState.getCurrentState();

        if (state.equals(AudioPlayerState.AUDIO_PLAYING) || state.equals(AudioPlayerState.AUDIO_PAUSED)) {
            playNextAudioTrack();
        }
    }

    private void executeStopAudioRequest() {
        String state = audioPlayerState.getCurrentState();

        if (state.equals(AudioPlayerState.AUDIO_PLAYING) || state.equals(AudioPlayerState.AUDIO_PAUSED)) {
            audioPlayerState.setCurrentState(AudioPlayerState.AUDIO_STOPPED);
            broadcastPlayerControlStatus(false, true);
            releaseResources(true);
            stopSelf();
        }
    }

    private void releaseResources(boolean releaseMediaPlayer) {
        stopForeground(true);

        if (releaseMediaPlayer && mediaPlayer != null) {
            mediaPlayer.reset();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    private void playNextAudioTrack() {
        audioPlayerState.setCurrentState(AudioPlayerState.AUDIO_STOPPED);
        releaseResources(false);

        try {
            currentTrack = audioTrackRetriever.getRandomItem();

            if (currentTrack == null) {
                Toast.makeText(this, "There are no audio tracks to play", Toast.LENGTH_LONG).show();
                executeStopAudioRequest();
                return;
            }

            broadcastTrack(currentTrack);

            createMediaPlayer();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setDataSource(getApplicationContext(), currentTrack.getURI());

            audioPlayerState.setCurrentState(AudioPlayerState.AUDIO_PREPARING);

            mediaPlayer.prepareAsync();
        } catch (IOException ioe) {
            Logger.e(TAG, "Unable to play audio track stream", ioe);
        }
    }

    private void broadcastTrack(AudioTrack audioTrack) {
        Intent intent = new Intent("audioTrackBroadcast");
        intent.putExtra("audioTrackTitle", audioTrack.getTitle());
        intent.putExtra("audioAlbumTitle", audioTrack.getAlbum());
        intent.putExtra("audioTrackAlbumId", audioTrack.getAlbumId());
        intent.putExtra("audioTrackDuration", audioTrack.getDuration());
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

    private void broadcastPlayerControlStatus(boolean isPaused, boolean hasStopped) {
        Intent intent = new Intent("updateControls");
        intent.putExtra("isPaused", isPaused);
        intent.putExtra("hasStopped", hasStopped);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

    private void broadcastCurrentTrackPosition(AudioTrack audioTrack) {
        Intent intent = new Intent("audioTrackPosition");
        intent.putExtra("currentTrackPosition", mediaPlayer.getCurrentPosition());
        intent.putExtra("audioTrackDuration", audioTrack.getDuration());
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

    private Runnable updateCurrentTrackPositionRunnable = new Runnable() {
        @Override
        public void run() {
            if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                broadcastCurrentTrackPosition(currentTrack);
                positionHandler.postDelayed(this, 250);
            }
        }
    };
}
