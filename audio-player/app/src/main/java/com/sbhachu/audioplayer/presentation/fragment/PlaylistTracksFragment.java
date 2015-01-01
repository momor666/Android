package com.sbhachu.audioplayer.presentation.fragment;


import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ProgressBar;

import com.nirhart.parallaxscroll.views.ParallaxListView;
import com.sbhachu.audioplayer.R;
import com.sbhachu.audioplayer.data.AudioContentManagerImpl;
import com.sbhachu.audioplayer.data.PlayerQueue;
import com.sbhachu.audioplayer.data.model.media.Playlist;
import com.sbhachu.audioplayer.data.model.media.Track;
import com.sbhachu.audioplayer.listener.GlobalFragmentInteractionListener;
import com.sbhachu.audioplayer.presentation.activity.MainActivity;
import com.sbhachu.audioplayer.presentation.adapter.PlaylistTracksAdapter;
import com.sbhachu.audioplayer.presentation.view.PlaylistParallaxHeaderView;
import com.sbhachu.audioplayer.presentation.view.PlaylistParallaxHeaderView_;
import com.sbhachu.audioplayer.presentation.view.PlaylistTracksInfoHeaderView;
import com.sbhachu.audioplayer.presentation.view.PlaylistTracksInfoHeaderView_;
import com.sbhachu.audioplayer.util.Logger;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import java.util.List;

@EFragment(R.layout.fragment_playlist_tracks)
public class PlaylistTracksFragment extends Fragment {

    public static final String TAG = PlaylistTracksFragment.class.getSimpleName();

    public static final String PLAYLIST_EXTRA = "playlist";

    @FragmentArg(PLAYLIST_EXTRA)
    public Playlist playlist;

    @ViewById(R.id.playlist_track_list)
    public ParallaxListView listView;

    @ViewById(R.id.playlist_tracks_progressbar)
    public ProgressBar progressBar;

    @Bean
    public AudioContentManagerImpl audioContentManager;

    @Bean
    public PlaylistTracksAdapter adapter;

    @Bean
    public PlayerQueue queue;

    private PlaylistParallaxHeaderView headerView;

    private PlaylistTracksInfoHeaderView headerInfoView;

    private List<Track> tracks;

    private GlobalFragmentInteractionListener listener;

    private boolean isEditable = false;

    public PlaylistTracksFragment() {
        // Required empty public constructor
    }

    @AfterViews
    public void afterViews() {

        listener = (MainActivity) getActivity();

        if (playlist != null) {
            headerView = PlaylistParallaxHeaderView_.build(getActivity());
            listView.addParallaxedHeaderView(headerView);

            headerInfoView = PlaylistTracksInfoHeaderView_.build(getActivity());
            headerInfoView.setListener(new PlaylistEditModeListener() {
                @Override
                public void onToggleEdit() {
                    isEditable = !isEditable;
                    adapter.setEditable(isEditable);
                    adapter.notifyDataSetChanged();
                }
            });
            listView.addHeaderView(headerInfoView);
        }

        loadAlbumData();
    }

    @Background
    public void loadAlbumData() {
        tracks = audioContentManager.loadPlaylistTracks(playlist);
        adapter.setTracks(tracks);
        adapter.setListener(new PlaylistItemListener() {
            @Override
            public void onDeleteButtonClicked(int position) {
                final Track track = tracks.get(position);

                boolean removed = audioContentManager.removeTrackFromPlaylist(playlist,
                                                                              track.getId());

                if (removed) {
                    tracks.remove(track);
                    adapter.notifyDataSetChanged();

                    // update header count
                    headerInfoView.bindData(playlist.getName(), tracks.size());
                }
            }
        });
        updateList();
    }

    @UiThread(delay = 750)
    public void updateList() {
        if (tracks != null) {
            headerView.bindData(playlist);
            headerInfoView.bindData(playlist.getName(), tracks.size());
            listView.setAdapter(adapter);
            progressBar.setVisibility(View.GONE);
        }
    }

    @ItemClick(R.id.playlist_track_list)
    public void trackSelected(Track track) {
        int selectedTrackIndex = tracks.indexOf(track);
        if (selectedTrackIndex > -1) {
            List<Track> queue = tracks.subList(selectedTrackIndex, tracks.size());
            listener.onTrackSelected(queue);
        }
    }

    public interface PlaylistItemListener {
        public void onDeleteButtonClicked(int position);
    }

    public interface PlaylistEditModeListener {
        public void onToggleEdit();
    }

}