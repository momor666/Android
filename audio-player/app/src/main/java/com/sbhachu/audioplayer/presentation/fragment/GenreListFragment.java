package com.sbhachu.audioplayer.presentation.fragment;


import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.sbhachu.audioplayer.R;
import com.sbhachu.audioplayer.data.AudioContentManagerImpl;
import com.sbhachu.audioplayer.data.model.media.Genre;
import com.sbhachu.audioplayer.listener.GlobalFragmentInteractionListener;
import com.sbhachu.audioplayer.presentation.activity.MainActivity;
import com.sbhachu.audioplayer.presentation.adapter.GenreListAdapter;
import com.sbhachu.audioplayer.util.Logger;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import java.util.List;

@EFragment(R.layout.fragment_genre_list)
public class GenreListFragment extends Fragment {

    public static final String TAG = GenreListFragment.class.getSimpleName();

    @ViewById(R.id.genre_list_view)
    public ListView genreListView;

    @ViewById(R.id.genre_progressbar)
    public ProgressBar progressBar;

    @Bean
    public AudioContentManagerImpl audioContentManager;

    @Bean
    public GenreListAdapter adapter;

    private List<Genre> genres;

    private GlobalFragmentInteractionListener listener;

    public GenreListFragment() {
        // Required empty public constructor
    }

    @AfterViews
    public void afterViews() {
        listener = (MainActivity) getActivity();

        loadGenreData();
    }

    @Background
    public void loadGenreData() {
        genres = audioContentManager.loadAllGenres();
        adapter.setGenres(genres);
        updateList();
    }

    @UiThread(delay = 750)
    public void updateList() {
        if (genres != null) {
            genreListView.setAdapter(adapter);
            progressBar.setVisibility(View.GONE);
        }
    }

    @ItemClick(R.id.genre_list_view)
    public void genreSelected(Genre genre) {
        listener.onGenreSelected(genre);
    }

}
