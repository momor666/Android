package com.sbhachu.audioplayer.presentation.fragment;

import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.sbhachu.audioplayer.R;
import com.sbhachu.audioplayer.data.AudioContentManagerImpl;
import com.sbhachu.audioplayer.data.PlayerQueue;
import com.sbhachu.audioplayer.data.model.media.Track;
import com.sbhachu.audioplayer.listener.GlobalFragmentInteractionListener;
import com.sbhachu.audioplayer.presentation.activity.MainActivity;
import com.sbhachu.audioplayer.presentation.adapter.TrackListAdapter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.ItemLongClick;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import java.util.List;

@EFragment(R.layout.fragment_track_list)
public class TrackListFragment extends Fragment {

    private static final String TAG = TrackListFragment.class.getSimpleName();

    @ViewById(R.id.track_list)
    public ListView trackListView;

    @ViewById(R.id.track_progressbar)
    public ProgressBar progressBar;

    @Bean
    public TrackListAdapter adapter;

    @Bean
    public AudioContentManagerImpl audioContentManager;

    @Bean
    public PlayerQueue queue;

    private GlobalFragmentInteractionListener listener;

    private List<Track> tracks;

    public TrackListFragment() {
        // Required empty public constructor
    }

    @AfterViews
    public void afterViews() {
        listener = (MainActivity) getActivity();

        loadTrackData();
    }

    @Background
    public void loadTrackData() {
        tracks = audioContentManager.loadAllTracks();

        adapter.setTracks(tracks);
        updateList();
    }

    @UiThread(delay = 750)
    public void updateList() {
        if (tracks != null && tracks.size() > 0) {
            if (queue.size() < 1) {
                this.queue.clear();
                this.queue.addList(tracks);
            }
            trackListView.setAdapter(adapter);
        } else {
            listener.onNoMusicFound();
        }
        progressBar.setVisibility(View.GONE);
    }

    @ItemClick(R.id.track_list)
    public void trackSelected(Track track) {
        int selectedTrackIndex = tracks.indexOf(track);
        List<Track> queue = tracks.subList(selectedTrackIndex, tracks.size());

        listener.onTrackSelected(queue);
    }

    @ItemLongClick(R.id.track_list)
    public void trackItemLongClickHandler(Track track) {
        listener.onTrackLongPressed(track);
    }
}
