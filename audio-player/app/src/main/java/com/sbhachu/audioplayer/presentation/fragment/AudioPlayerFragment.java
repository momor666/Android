package com.sbhachu.audioplayer.presentation.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sbhachu.audioplayer.R;
import com.sbhachu.audioplayer.data.PlayerQueue;
import com.sbhachu.audioplayer.data.model.AudioPlayerState;
import com.sbhachu.audioplayer.data.model.media.Track;
import com.sbhachu.audioplayer.presentation.adapter.QueueListAdapter;
import com.sbhachu.audioplayer.service.AudioService;
import com.sbhachu.audioplayer.service.AudioService_;
import com.sbhachu.audioplayer.util.Logger;
import com.sbhachu.audioplayer.util.StringUtil;
import com.squareup.picasso.Picasso;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;


@EFragment(R.layout.fragment_audio_player)
public class AudioPlayerFragment extends Fragment {

    private static final String TAG = AudioPlayerFragment.class.getSimpleName();

    @ViewById(R.id.player_progress_bar)
    public ProgressBar progressBar;

    @ViewById(R.id.audio_player_large_art_iv)
    public ImageView largeImageView;

    @ViewById(R.id.audio_player_small_art_iv)
    public ImageView smallImageView;

    @ViewById(R.id.audio_player_header)
    public LinearLayout audioPlayerHeaderLayout;

    @ViewById(R.id.play_button_iv)
    public ImageView playButtonImageView;

    @ViewById(R.id.pause_button_iv)
    public ImageView pauseButtonImageView;

    @ViewById(R.id.play_button_header_iv)
    public ImageView playButtonHeaderImageView;

    @ViewById(R.id.pause_button_header_iv)
    public ImageView pauseButtonHeaderImageView;

    @ViewById(R.id.toggle_list)
    public ImageView toggleQueueImageView;

    @ViewById(R.id.audio_player_position_tv)
    public TextView positionTextView;

    @ViewById(R.id.audio_player_duration_tv)
    public TextView durationTextView;

    @ViewById(R.id.audio_player_title_tv)
    public TextView titleTextView;

    @ViewById(R.id.audio_player_artist_tv)
    public TextView artistTextView;

    @ViewById(R.id.up_next)
    public TextView upNextTextView;

    @ViewById(R.id.queue_list)
    public ListView queueListView;

    @ViewById(R.id.no_tracks_queued)
    public TextView noTracksQueuedTextView;

    @ViewById(R.id.queue_container)
    public RelativeLayout queueContainer;

    @Bean
    public PlayerQueue playerQueue;

    @Bean
    public QueueListAdapter adapter;

    @Bean
    public AudioPlayerState playerState;

    private boolean showQueue = false;

    public AudioPlayerFragment() {
        // Required empty public constructor
    }

    @AfterViews
    public void afterViews() {
        adapter.setTracks(playerQueue);
        adapter.setListener(new QueueItemListener() {
            @Override
            public void onDeleteButtonClicked(int position) {
                final Track track = playerQueue.get(position);
                playerQueue.remove(track);

                adapter.notifyDataSetChanged();
            }
        });
        queueListView.setAdapter(adapter);

        IntentFilter filter = new IntentFilter();
        filter.addAction("queueUpdated");
        filter.addAction("audioTrackBroadcast");
        filter.addAction("updateControls");
        filter.addAction("currentTrackPosition");
        filter.addAction("audioPanelExpanded");
        filter.addAction("audioPanelCollapsed");
        filter.addAction("audioPanelSliding");

        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(
                audioTrackBroadcastReceiver, filter);

    }

    private BroadcastReceiver audioTrackBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            if (action.equals("queueUpdated")) {
                if (playerQueue.size() > 0) {
                    Track track = playerQueue.peekFirst();
                    renderTrackInfo(track);
                }

            } else if (action.equals("audioTrackBroadcast")) {

                final Track track = intent.getParcelableExtra("track");

                renderTrackInfo(track);

                if (playerQueue.size() > 0) {
                    noTracksQueuedTextView.setVisibility(View.GONE);
                    adapter.notifyDataSetChanged();
                } else {
                    noTracksQueuedTextView.setVisibility(View.VISIBLE);
                }

            } else if (action.equals("updateControls")) {
                boolean isPaused = intent.getBooleanExtra("is_paused", false);
                boolean hasStopped = intent.getBooleanExtra("has_stopped", false);

                if (hasStopped) {

                }

                if (!isPaused) {
                    playButtonImageView.setVisibility(View.GONE);
                    playButtonHeaderImageView.setVisibility(View.GONE);
                    pauseButtonImageView.setVisibility(View.VISIBLE);
                    pauseButtonHeaderImageView.setVisibility(View.VISIBLE);
                } else {
                    playButtonImageView.setVisibility(View.VISIBLE);
                    playButtonHeaderImageView.setVisibility(View.VISIBLE);
                    pauseButtonImageView.setVisibility(View.GONE);
                    pauseButtonHeaderImageView.setVisibility(View.GONE);
                }


            } else if (action.equals("currentTrackPosition")) {
                final double position = intent.getDoubleExtra("position", -1);
                final double progress = intent.getDoubleExtra("progress", -1);

                progressBar.setProgress((int) progress);
                positionTextView.setText(StringUtil.getTimeString((long) position));

            } else if (action.equals("audioPanelExpanded")) {
                toggleQueueImageView.setVisibility(View.VISIBLE);

            } else if (action.equals("audioPanelCollapsed")) {
                audioPlayerHeaderLayout.setAlpha(1.0f);

                if (playerState.getCurrentState().equals(AudioPlayerState.AUDIO_PLAYING)) {
                    playButtonHeaderImageView.setVisibility(View.GONE);
                    pauseButtonHeaderImageView.setVisibility(View.VISIBLE);
                } else {
                    playButtonHeaderImageView.setVisibility(View.VISIBLE);
                    pauseButtonHeaderImageView.setVisibility(View.GONE);
                }

                toggleQueueImageView.setVisibility(View.GONE);
                queueContainer.setVisibility(View.GONE);
                showQueue = false;

            } else if (action.equals("audioPanelSliding")) {
                final float coefficient = intent.getFloatExtra("coefficient", -1);
                audioPlayerHeaderLayout.setAlpha(coefficient + 0.4f);
            }
        }
    };

    @Click(R.id.play_button_iv)
    public void playButtonClickHandler() {
        AudioService_.intent(getActivity()).action(AudioService.ACTION_PLAY).start();
    }

    @Click(R.id.play_button_header_iv)
    public void headerPlayButtonClickHandler() {
        AudioService_.intent(getActivity()).action(AudioService.ACTION_PLAY).start();
    }

    @Click(R.id.pause_button_iv)
    public void pauseButtonClickHandler() {
        AudioService_.intent(getActivity()).action(AudioService.ACTION_PAUSE).start();
    }

    @Click(R.id.pause_button_header_iv)
    public void headerPauseButtonClickHandler() {
        AudioService_.intent(getActivity()).action(AudioService.ACTION_PAUSE).start();
    }

    @Click(R.id.rewind_button_iv)
    public void rewindButtonClickHandler() {
        Logger.i(TAG, "rewind");
        AudioService_.intent(getActivity()).action(AudioService.ACTION_REWIND).start();
    }

    @Click(R.id.forward_button_iv)
    public void forwardButtonClickHandler() {
        Logger.i(TAG, "forward");
        AudioService_.intent(getActivity()).action(AudioService.ACTION_SKIP).start();
    }

    @Click(R.id.toggle_list)
    public void toggleList() {
        if (!showQueue) {
            queueContainer.setVisibility(View.VISIBLE);
            if (playerQueue.size() > 0) {
                noTracksQueuedTextView.setVisibility(View.GONE);
                queueListView.setVisibility(View.VISIBLE);
                adapter.notifyDataSetChanged();
            } else {
                noTracksQueuedTextView.setVisibility(View.VISIBLE);
                queueListView.setVisibility(View.GONE);
            }
            showQueue = true;
        } else {
            queueContainer.setVisibility(View.GONE);
            showQueue = false;
        }
    }

    private void renderTrackInfo(Track track) {
        Picasso.with(getActivity()).load(track.getAlbumArtUri())
                .placeholder(R.drawable.album_placeholder)
                .into(smallImageView);

        Picasso.with(getActivity()).load(track.getAlbumArtUri())
                .fit().centerCrop()
                .into(largeImageView);

        titleTextView.setText(track.getTitle());
        artistTextView.setText(track.getArtist());
        durationTextView.setText(StringUtil.getTimeString(track.getDuration()));
    }

    public interface QueueItemListener {
        public void onDeleteButtonClicked(int position);
    }
}
