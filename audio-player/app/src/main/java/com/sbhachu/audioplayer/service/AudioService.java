package com.sbhachu.audioplayer.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.os.IBinder;
import android.os.PowerManager;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.widget.Toast;

import com.sbhachu.audioplayer.data.PlayerQueue;
import com.sbhachu.audioplayer.data.model.AudioPlayerState;
import com.sbhachu.audioplayer.data.model.media.Track;
import com.sbhachu.audioplayer.presentation.activity.MainActivity_;
import com.sbhachu.audioplayer.util.Logger;
import com.sbhachu.audioplayer.util.StringUtil;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EService;
import org.androidannotations.annotations.SystemService;

import java.io.IOException;

import static com.sbhachu.audioplayer.data.model.AudioPlayerState.AUDIO_PAUSED;
import static com.sbhachu.audioplayer.data.model.AudioPlayerState.AUDIO_PLAYING;
import static com.sbhachu.audioplayer.data.model.AudioPlayerState.AUDIO_PREPARING;
import static com.sbhachu.audioplayer.data.model.AudioPlayerState.AUDIO_STOPPED;

/**
 * Created by sbhachu on 21/12/2014.
 */
@EService
public class AudioService extends Service implements MediaPlayer.OnCompletionListener,
        MediaPlayer.OnPreparedListener,
        MediaPlayer.OnErrorListener {

    public static final String ACTION_TOGGLE_PLAY = "com.sbhachu.audioplayer.action.TOGGLE_PLAYBACK";
    public static final String ACTION_PLAY = "com.sbhachu.audioplayer.action.PLAY";
    public static final String ACTION_PAUSE = "com.sbhachu.audioplayer.action.PAUSE";
    public static final String ACTION_STOP = "com.sbhachu.audioplayer.action.STOP";
    public static final String ACTION_SKIP = "com.sbhachu.audioplayer.action.SKIP";
    public static final String ACTION_REWIND = "com.sbhachu.audioplayer.action.REWIND";

    public static final int NOTIFICATION_ID = 31415926;

    public static final String TAG = AudioService.class.getSimpleName();

    @SystemService
    public AudioManager audioManager;

    @SystemService
    public NotificationManager notificationManager;

    @Bean
    public AudioPlayerState playerState;

    @Bean
    public PlayerQueue queue;

    private MediaPlayer mediaPlayer;

    private Track currentTrack;

    private Handler positionHandler = new Handler();

    private NotificationCompat.Builder notificationBuilder;

    @Override
    public void onCreate() {
        super.onCreate();
//        playerState.setCurrentState(AUDIO_STOPPED);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        playerState.setCurrentState(AUDIO_STOPPED);
        releaseResources(true);

        stopForeground(true);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String action = intent.getAction();

        switch (action) {
            case ACTION_TOGGLE_PLAY:
                togglePlayback();
                break;
            case ACTION_PLAY:
                playAudio();
                break;
            case ACTION_PAUSE:
                pauseAudio();
                break;
            case ACTION_STOP:
                stopAudio();
                break;
            case ACTION_SKIP:
                skipAudio();
                break;
            case ACTION_REWIND:
                rewindAudio();
                break;
            default:
                break;
        }

        return START_NOT_STICKY;
    }


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        playNextTrack();
    }

    @Override
    public boolean onError(MediaPlayer mediaPlayer, int what, int extra) {
        Toast.makeText(getApplicationContext(), "Media player error!", Toast.LENGTH_SHORT).show();
        Logger.e(TAG, "ERROR[" + String.valueOf(what) + " - " + String.valueOf(extra) + "]");
        playerState.setCurrentState(AUDIO_STOPPED);
        releaseResources(true);
        return true;
    }

    @Override
    public void onPrepared(MediaPlayer mediaPlayer) {
        playerState.setCurrentState(AUDIO_PLAYING);

        if (!mediaPlayer.isPlaying()) {
            broadcastPlayerControlStatus(false, false);
            mediaPlayer.start();
            buildNotification();
            positionHandler.postDelayed(updateCurrentTrackPosition, 250);
        }
    }

    /**
     * Media Player Control Methods
     */
    private void togglePlayback() {
        if (isState(AUDIO_PAUSED) || isState(AUDIO_STOPPED)) {
            playAudio();
        } else {
            pauseAudio();
        }
    }

    private void playAudio() {
        if (queue.size() < 1)
            return;

        if (isState(AUDIO_PLAYING) || isState(AUDIO_STOPPED)) {
            playNextTrack();
        } else if (isState(AUDIO_PAUSED)) {
            playerState.setCurrentState(AUDIO_PLAYING);

            if (!mediaPlayer.isPlaying()) {
                broadcastPlayerControlStatus(false, false);
                mediaPlayer.start();
                positionHandler.postDelayed(updateCurrentTrackPosition, 250);
            }
        }
    }

    private void pauseAudio() {
        if (isState(AUDIO_PLAYING)) {
            playerState.setCurrentState(AUDIO_PAUSED);
            broadcastPlayerControlStatus(true, false);
            mediaPlayer.pause();

            notificationBuilder.setContentTitle("Paused");
            notificationBuilder.setSmallIcon(android.R.drawable.ic_media_pause);
            notificationManager.notify(NOTIFICATION_ID, notificationBuilder.build());
        }
    }

    private void rewindAudio() {
        if (isState(AUDIO_PLAYING) || isState(AUDIO_PAUSED)) {
            mediaPlayer.seekTo(0);
        }
    }

    private void skipAudio() {
        if (isState(AUDIO_PLAYING) || isState(AUDIO_PAUSED)) {
            playNextTrack();
        }
    }

    private void stopAudio() {
        if (isState(AUDIO_PLAYING) || isState(AUDIO_PAUSED)) {
            playerState.setCurrentState(AUDIO_STOPPED);
            broadcastPlayerControlStatus(false, true);
            releaseResources(true);
            positionHandler.removeCallbacksAndMessages(updateCurrentTrackPosition);
            stopSelf();
        }
    }

    private void playNextTrack() {
        playerState.setCurrentState(AUDIO_STOPPED);
        releaseResources(false);

        try {
            currentTrack = null;

            if (queue.size() > 0)
                currentTrack = queue.poll();

            if (currentTrack == null) {
                stopAudio();
                releaseResources(true);
                return;
            }

            broadcastTrack(currentTrack);
            createMediaPlayer();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

            mediaPlayer.setDataSource(this, Uri.parse(currentTrack.getPath()));

            playerState.setCurrentState(AUDIO_PREPARING);

            mediaPlayer.prepare();
        } catch (IOException e) {
            Logger.e(TAG, "Unable to play audio track stream");
            e.printStackTrace();
        }
    }

    /**
     * Broadcast
     */
    private void broadcastTrack(Track track) {
        Intent intent = new Intent("audioTrackBroadcast");
        intent.putExtra("track", currentTrack);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

    private void broadcastPlayerControlStatus(boolean isPaused, boolean hasStopped) {
        Intent intent = new Intent("updateControls");
        intent.putExtra("is_paused", isPaused);
        intent.putExtra("has_stopped", hasStopped);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

    private void broadcastCurrentTrackPosition(Track track) {
        final double position = mediaPlayer.getCurrentPosition();
        final double duration = track.getDuration();

        double progress = (position/duration) * 100;

        Intent intent = new Intent("currentTrackPosition");
        intent.putExtra("position", position);
        intent.putExtra("progress", progress);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);

        notificationBuilder.setProgress(100, (int)progress, false);
        notificationBuilder.setSmallIcon(android.R.drawable.ic_media_play);
        notificationBuilder.setContentTitle(StringUtil.getTimeString((long)position) + " - Playing");
        notificationBuilder.setContentText(currentTrack.getTitle() + " by " + currentTrack.getArtist());
        notificationManager.notify(NOTIFICATION_ID, notificationBuilder.build());
    }

    private Runnable updateCurrentTrackPosition = new Runnable() {
        @Override
        public void run() {
            if (mediaPlayer != null && currentTrack != null) {
                if (mediaPlayer.isPlaying()) {
                    broadcastCurrentTrackPosition(currentTrack);
                    positionHandler.postDelayed(this, 250);
                }
            }
        }
    };

    /**
     * Utility
     */
    private void createMediaPlayer() {
        if (mediaPlayer == null) {
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setWakeMode(this, PowerManager.PARTIAL_WAKE_LOCK);

            mediaPlayer.setOnPreparedListener(this);
            mediaPlayer.setOnCompletionListener(this);
            mediaPlayer.setOnErrorListener(this);
        } else {
            mediaPlayer.reset();
        }
    }

    private void releaseResources(boolean fullRelease) {
        stopForeground(true);

        if (fullRelease && mediaPlayer != null) {
            mediaPlayer.reset();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    public boolean isState(String state) {
        return playerState.getCurrentState().equals(state);
    }

    public void buildNotification() {
        Intent notificationIntent = new Intent(getApplicationContext(), MainActivity_.class);
//        notificationIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);

        PendingIntent pendInt = PendingIntent.getActivity(getApplicationContext(), 0,
                                                          notificationIntent,
                                                          PendingIntent.FLAG_UPDATE_CURRENT);

        notificationBuilder = new NotificationCompat.Builder(this);


        notificationBuilder.setContentIntent(pendInt)
                .setSmallIcon(android.R.drawable.ic_media_play)
                .setTicker(currentTrack.getTitle())
                .setOngoing(true)
                .setProgress(100, 0, false)
                .setContentTitle("Playing")
                .setContentText("00:00" + "\nTitle: " + currentTrack.getTitle() + "\nArtist: " + currentTrack.getArtist());

        Notification notification = notificationBuilder.build();
        notification.flags |= Notification.FLAG_AUTO_CANCEL;

        startForeground(NOTIFICATION_ID, notification);
    }
}
