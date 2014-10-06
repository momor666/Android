package com.sbhachu.audioplayer.presentation.activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.sbhachu.audioplayer.R;
import com.sbhachu.audioplayer.service.AudioService;
import com.sbhachu.audioplayer.util.Logger;
import com.sbhachu.audioplayer.util.StringUtil;
import com.squareup.picasso.Picasso;


public class MainActivity extends Activity implements View.OnClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    private static final Uri artworkUri = Uri.parse("content://media/external/audio/albumart");

    private TextView trackTitleTV;
    private TextView albumTitleTV;
    private TextView positionTV;
    private TextView durationTV;

    private ProgressBar progressBar;

    private Button playButton;
    private Button pauseButton;
    private Button skipButton;
    private Button rewindButton;
    private Button stopButton;

    private ImageView albumArt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        IntentFilter filter = new IntentFilter();
        filter.addAction("audioTrackBroadcast");
        filter.addAction("updateControls");
        filter.addAction("audioTrackPosition");

        LocalBroadcastManager.getInstance(this).registerReceiver(audioTrackBroadcastReceiver, filter);

        startService(new Intent(AudioService.ACTION_PLAY));

        trackTitleTV = (TextView) findViewById(R.id.track_title);
        albumTitleTV = (TextView) findViewById(R.id.album_title);
        positionTV = (TextView) findViewById(R.id.position);
        durationTV = (TextView) findViewById(R.id.duration);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        playButton = (Button) findViewById(R.id.playbutton);
        pauseButton = (Button) findViewById(R.id.pausebutton);
        skipButton = (Button) findViewById(R.id.skipbutton);
        rewindButton = (Button) findViewById(R.id.rewindbutton);
        stopButton = (Button) findViewById(R.id.stopbutton);

        albumArt = (ImageView) findViewById(R.id.album_art);

        playButton.setOnClickListener(this);
        pauseButton.setOnClickListener(this);
        skipButton.setOnClickListener(this);
        rewindButton.setOnClickListener(this);
        stopButton.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        if (view == playButton)
            startService(new Intent(AudioService.ACTION_PLAY));
        else if (view == pauseButton)
            startService(new Intent(AudioService.ACTION_PAUSE));
        else if (view == skipButton)
            startService(new Intent(AudioService.ACTION_SKIP));
        else if (view == rewindButton)
            startService(new Intent(AudioService.ACTION_REWIND));
        else if (view == stopButton)
            startService(new Intent(AudioService.ACTION_STOP));


    }

    private BroadcastReceiver audioTrackBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            if (action.equals("audioTrackBroadcast")) {
                String trackTitle = intent.getStringExtra("audioTrackTitle");
                String albumTitle = intent.getStringExtra("audioAlbumTitle");
                Long albumId = intent.getLongExtra("audioTrackAlbumId", -1);
                int duration = intent.getIntExtra("audioTrackDuration", -1);


                trackTitleTV.setText(trackTitle);
                albumTitleTV.setText(albumTitle);
                durationTV.setText(StringUtil.getTimeString(duration));
                Uri uri = ContentUris.withAppendedId(artworkUri, albumId);
                Logger.i(TAG, uri.toString());
                Picasso.with(getApplicationContext()).load(uri).placeholder(R.drawable.album_placeholder).resize(600, 600).centerInside().into(albumArt);
            } else if (action.equals("updateControls")) {
                boolean isPaused = intent.getBooleanExtra("isPaused", false);
                boolean hasStopped = intent.getBooleanExtra("hasStopped", false);

                if (!isPaused) {
                    playButton.setVisibility(View.GONE);
                    pauseButton.setVisibility(View.VISIBLE);
                } else {
                    playButton.setVisibility(View.VISIBLE);
                    pauseButton.setVisibility(View.GONE);
                }

                if (hasStopped) {
                    Picasso.with(getApplicationContext()).load(R.drawable.album_placeholder).resize(600, 600).centerInside().into(albumArt);
                    progressBar.setProgress(0);
                    positionTV.setText(StringUtil.getTimeString(0));
                    durationTV.setText(StringUtil.getTimeString(0));
                    trackTitleTV.setText("-");
                    albumTitleTV.setText("-");
                    playButton.setVisibility(View.VISIBLE);
                    pauseButton.setVisibility(View.GONE);
                }

            } else if (action.equals("audioTrackPosition")) {
                double position = (double) intent.getIntExtra("currentTrackPosition", -1);
                double duration = (double) intent.getIntExtra("audioTrackDuration", -1);

                positionTV.setText(StringUtil.getTimeString(new Double(position).longValue()));

                // calculate percentage
                double progress = (position / duration) * 100;
                progressBar.setProgress((int) progress);
            }
        }
    };




}
