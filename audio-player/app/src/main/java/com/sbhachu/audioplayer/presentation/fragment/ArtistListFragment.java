package com.sbhachu.audioplayer.presentation.fragment;

import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.sbhachu.audioplayer.R;
import com.sbhachu.audioplayer.data.AudioContentManagerImpl;
import com.sbhachu.audioplayer.data.model.media.Artist;
import com.sbhachu.audioplayer.listener.GlobalFragmentInteractionListener;
import com.sbhachu.audioplayer.presentation.activity.MainActivity;
import com.sbhachu.audioplayer.presentation.adapter.ArtlistListAdapter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import java.util.List;

@EFragment(R.layout.fragment_artist_list)
public class ArtistListFragment extends Fragment {

    private static final String TAG = ArtistListFragment.class.getSimpleName();

    @ViewById(R.id.artist_list_view)
    public ListView artistListView;

    @ViewById(R.id.artist_progressbar)
    public ProgressBar progressBar;

    @Bean
    public ArtlistListAdapter adapter;

    @Bean
    public AudioContentManagerImpl audioContentManager;

    private List<Artist> artists;

    private GlobalFragmentInteractionListener listener;

    public ArtistListFragment() {
        // Required empty public constructor
    }

    @AfterViews
    public void afterViews() {
        listener = (MainActivity) getActivity();

        loadArtistData();
    }

    @Background
    public void loadArtistData() {
        artists = audioContentManager.loadAllArtists();

        for (Artist artist : artists) {
            artist.setImageUri(audioContentManager.loadArtistImage(artist));
        }

        adapter.setArtists(artists);
        updateList();
    }

    @UiThread(delay = 750)
    public void updateList() {
        if (artists != null) {
            artistListView.setAdapter(adapter);
            progressBar.setVisibility(View.GONE);
        }
    }

    @ItemClick(R.id.artist_list_view)
    public void artistSelected(Artist artist) {
        listener.onArtistSelected(artist);
    }
}
