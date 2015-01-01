package com.sbhachu.audioplayer.presentation.fragment;


import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.GridView;
import android.widget.ProgressBar;

import com.sbhachu.audioplayer.R;
import com.sbhachu.audioplayer.data.AudioContentManagerImpl;
import com.sbhachu.audioplayer.data.model.media.Album;
import com.sbhachu.audioplayer.data.model.media.Artist;
import com.sbhachu.audioplayer.listener.GlobalFragmentInteractionListener;
import com.sbhachu.audioplayer.presentation.activity.MainActivity;
import com.sbhachu.audioplayer.presentation.adapter.AlbumListAdapter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import java.util.List;


@EFragment(R.layout.fragment_album_list)
public class AlbumListFragment extends Fragment {

    private static final String TAG = AlbumListFragment.class.getSimpleName();

    public static final String ARTIST_EXTRA = "artist";

    @FragmentArg(ARTIST_EXTRA)
    public Artist artist;

    @ViewById(R.id.album_grid_view)
    public GridView albumGridView;

    @ViewById(R.id.album_progressbar)
    public ProgressBar progressBar;

    @Bean
    public AlbumListAdapter adapter;

    @Bean
    public AudioContentManagerImpl audioContentManager;

    private List<Album> albums;

    private GlobalFragmentInteractionListener listener;

    public AlbumListFragment() {
        // Required empty public constructor
    }

    @AfterViews
    public void afterViews() {
        listener = (MainActivity) getActivity();

        loadAlbumData();
    }

    @Background
    public void loadAlbumData() {
        if (artist != null)
//            albums = audioContentManager.loadAlbumsByArtist(artist);
            albums = audioContentManager.loadAlbums(artist.getId());
        else
//            albums = audioContentManager.loadAllAlbums();
            albums = audioContentManager.loadAlbums(null);

        adapter.setAlbums(albums);
        updateGrid();
    }

    @UiThread(delay = 750)
    public void updateGrid() {
        if (albums != null) {
            albumGridView.setAdapter(adapter);
            progressBar.setVisibility(View.GONE);
        }
    }

    @ItemClick(R.id.album_grid_view)
    public void albumSelected(Album album) {
        listener.onAlbumSelected(album);
    }

}
