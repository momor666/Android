package com.sbhachu.audioplayer.presentation.fragment;


import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ProgressBar;

import com.nirhart.parallaxscroll.views.ParallaxListView;
import com.sbhachu.audioplayer.R;
import com.sbhachu.audioplayer.data.AudioContentManagerImpl;
import com.sbhachu.audioplayer.data.model.media.Album;
import com.sbhachu.audioplayer.data.model.media.Track;
import com.sbhachu.audioplayer.listener.GlobalFragmentInteractionListener;
import com.sbhachu.audioplayer.presentation.activity.MainActivity;
import com.sbhachu.audioplayer.presentation.adapter.TrackListNoArtAdapter;
import com.sbhachu.audioplayer.presentation.view.AlbumParallaxHeaderView;
import com.sbhachu.audioplayer.presentation.view.AlbumParallaxHeaderView_;
import com.sbhachu.audioplayer.presentation.view.AlbumTracksInfoHeaderView;
import com.sbhachu.audioplayer.presentation.view.AlbumTracksInfoHeaderView_;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import java.util.List;

@EFragment(R.layout.fragment_album_tracks)
public class AlbumTracksFragment extends Fragment {

    public static final String TAG = AlbumTracksFragment.class.getSimpleName();

    public static final String ALBUM_EXTRA = "album";

    @FragmentArg(ALBUM_EXTRA)
    public Album album;

    @ViewById(R.id.album_track_list)
    public ParallaxListView listView;

    @ViewById(R.id.album_tracks_progressbar)
    public ProgressBar progressBar;

    @Bean
    public AudioContentManagerImpl audioContentManager;

    @Bean
    public TrackListNoArtAdapter adapter;

    private AlbumParallaxHeaderView headerView;

    private AlbumTracksInfoHeaderView headerInfoView;

    private List<Track> tracks;

    private GlobalFragmentInteractionListener listener;

    public AlbumTracksFragment() {
        // Required empty public constructor
    }

    @AfterViews
    public void afterViews() {

        listener = (MainActivity) getActivity();

        if (album != null) {
            headerView = AlbumParallaxHeaderView_.build(getActivity());
            listView.addParallaxedHeaderView(headerView);

            headerInfoView = AlbumTracksInfoHeaderView_.build(getActivity());
            listView.addHeaderView(headerInfoView);
        }

        loadAlbumData();
    }

    @Background
    public void loadAlbumData() {
        tracks = audioContentManager.loadTracksFromAlbum(album);
        adapter.setTracks(tracks);
        updateList();
    }

    @UiThread(delay = 750)
    public void updateList() {
        if (tracks != null) {
            headerView.bindData(album.getAlbumArtUri());
            headerInfoView.bindData(album.getName(), album.getArtist(), album.getSongCount());
            listView.setAdapter(adapter);
            progressBar.setVisibility(View.GONE);
        }
    }

    @ItemClick(R.id.album_track_list)
    public void trackSelected(Track track) {
        int selectedTrackIndex = tracks.indexOf(track);
        if (selectedTrackIndex > -1) {
            List<Track> queue = tracks.subList(selectedTrackIndex, tracks.size());
            listener.onTrackSelected(queue);
        }
    }
}