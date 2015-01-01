package com.sbhachu.audioplayer.presentation.fragment;


import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ProgressBar;

import com.nirhart.parallaxscroll.views.ParallaxListView;
import com.sbhachu.audioplayer.R;
import com.sbhachu.audioplayer.data.AudioContentManagerImpl;
import com.sbhachu.audioplayer.data.PlayerQueue;
import com.sbhachu.audioplayer.data.model.media.Genre;
import com.sbhachu.audioplayer.data.model.media.Track;
import com.sbhachu.audioplayer.listener.GlobalFragmentInteractionListener;
import com.sbhachu.audioplayer.presentation.activity.MainActivity;
import com.sbhachu.audioplayer.presentation.adapter.TrackListAdapter;
import com.sbhachu.audioplayer.presentation.view.GenreParallaxHeaderView;
import com.sbhachu.audioplayer.presentation.view.GenreParallaxHeaderView_;
import com.sbhachu.audioplayer.presentation.view.GenreTracksInfoHeaderView;
import com.sbhachu.audioplayer.presentation.view.GenreTracksInfoHeaderView_;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import java.util.List;

@EFragment(R.layout.fragment_genre_tracks)
public class GenreTracksFragment extends Fragment {

    public static final String TAG = GenreTracksFragment.class.getSimpleName();

    public static final String GENRE_EXTRA = "genre";

    @FragmentArg(GENRE_EXTRA)
    public Genre genre;

    @ViewById(R.id.genre_track_list)
    public ParallaxListView listView;

    @ViewById(R.id.genre_tracks_progressbar)
    public ProgressBar progressBar;

    @Bean
    public AudioContentManagerImpl audioContentManager;

    @Bean
    public TrackListAdapter adapter;

    @Bean
    public PlayerQueue queue;

    private GenreParallaxHeaderView headerView;

    private GenreTracksInfoHeaderView headerInfoView;

    private List<Track> tracks;

    private GlobalFragmentInteractionListener listener;

    public GenreTracksFragment() {
        // Required empty public constructor
    }

    @AfterViews
    public void afterViews() {

        listener = (MainActivity) getActivity();

        if (genre != null) {
            headerView = GenreParallaxHeaderView_.build(getActivity());
            listView.addParallaxedHeaderView(headerView);

            headerInfoView = GenreTracksInfoHeaderView_.build(getActivity());
            listView.addHeaderView(headerInfoView);
        }

        loadTrackData();
    }

    @Background
    public void loadTrackData() {
        tracks = audioContentManager.loadTracksByGenre(genre);
        adapter.setTracks(tracks);
        updateList();
    }

    @UiThread(delay = 750)
    public void updateList() {
        if (tracks != null) {
            headerView.bindData(genre);
            headerInfoView.bindData(genre.getName(), tracks.size());
            listView.setAdapter(adapter);
            progressBar.setVisibility(View.GONE);
        }
    }

    @ItemClick(R.id.genre_track_list)
    public void trackSelected(Track track) {
        int selectedTrackIndex = tracks.indexOf(track);
        if (selectedTrackIndex > -1) {
            List<Track> queue = tracks.subList(selectedTrackIndex, tracks.size());
            listener.onTrackSelected(queue);
        }
    }
}