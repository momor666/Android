package com.sbhachu.audioplayer.presentation.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.GridView;
import android.widget.ProgressBar;

import com.sbhachu.audioplayer.R;
import com.sbhachu.audioplayer.data.AudioContentManagerImpl;
import com.sbhachu.audioplayer.data.model.media.Playlist;
import com.sbhachu.audioplayer.listener.GlobalFragmentInteractionListener;
import com.sbhachu.audioplayer.presentation.activity.MainActivity;
import com.sbhachu.audioplayer.presentation.adapter.PlaylistAdapter;
import com.sbhachu.audioplayer.presentation.fragment.dialog.PlaylistNameEntryDialogFragment;
import com.sbhachu.audioplayer.util.Logger;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import java.util.List;

@EFragment(R.layout.fragment_playlist)
@OptionsMenu(R.menu.menu_playlists)
public class PlaylistFragment extends Fragment implements PlaylistNameEntryDialogFragment.PlaylistNameListener {

    private static final String TAG = PlaylistFragment.class.getSimpleName();

    @ViewById(R.id.playlist_grid_view)
    public GridView playlistGridView;

    @ViewById(R.id.playlist_progressbar)
    public ProgressBar playlistProgressBar;

    @Bean
    public PlaylistAdapter adapter;

    @Bean
    public AudioContentManagerImpl audioContentManager;

    private List<Playlist> playlists;

    private GlobalFragmentInteractionListener listener;

    public PlaylistFragment() {
        // Required empty public constructor
    }

    @AfterViews
    public void afterViews() {
        this.listener = (MainActivity) getActivity();

        setHasOptionsMenu(true);

        loadPlaylistData();
    }

    @OptionsItem(R.id.action_add_playlist)
    public void onAddPlaylistSelected() {


        DialogFragment fragment = FragmentFactory.buildPlaylistNameEntryDialogFragment();
        fragment.setTargetFragment(this, 0);
        fragment.show(getFragmentManager(), "playlist_name_entry");

    }

    @Background
    public void loadPlaylistData() {
        playlists = audioContentManager.loadAllPlaylists();

        for (Playlist playlist : playlists) {
            playlist.setImages(audioContentManager.loadPlaylistImages(playlist));
        }

        adapter.setPlaylists(playlists);
        adapter.setListener(new PlaylistItemListener() {
            @Override
            public void onDeleteButtonClicked(int position) {
                final Playlist playlist = playlists.get(position);
                playlistDeleteHandler(playlist);
            }
        });
        updateGrid();
    }

    @Background
    public void addPlaylist(String playlistName) {
        Long playlistId = audioContentManager.createPlaylist(playlistName);
        Playlist playlist = audioContentManager.getPlaylist(playlistId);

        onAddCompletionHandler(playlist);
    }

    @UiThread
    public void onAddCompletionHandler(Playlist playlist) {
        if (playlist != null && !isPlaylistAdded(playlist)) {
            playlists.add(playlist);
            adapter.notifyDataSetChanged();

            listener.onPlaylistsChanged();
        }
    }

    @Background
    public void deletePlaylist(Playlist playlist) {
        boolean deleted = audioContentManager.deletePlaylist(playlist.getId());
        onDeleteCompletionHandler(playlist, deleted);
    }

    @UiThread
    public void onDeleteCompletionHandler(Playlist playlist, boolean deleted) {
        if (deleted) {
            playlists.remove(playlist);
            adapter.notifyDataSetChanged();

            listener.onPlaylistsChanged();
        }
    }

    @UiThread(delay = 750)
    public void updateGrid() {
        if (playlists != null) {
            playlistGridView.setAdapter(adapter);
            playlistProgressBar.setVisibility(View.GONE);
        }
    }


    @ItemClick(R.id.playlist_grid_view)
    public void playlistSelected(Playlist playlist) {
        listener.onPlaylistSelected(playlist);
    }


    private void playlistDeleteHandler(final Playlist playlist) {
        final AlertDialog.Builder alert = new AlertDialog.Builder(getActivity())
                .setTitle("Delete Playlist")
                .setMessage("Are you sure you want to delete:\n\n" + playlist.getName())
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        deletePlaylist(playlist);
                    }
                })
                .setNegativeButton("No", null);

        alert.show();
    }

    public interface PlaylistItemListener {
        public void onDeleteButtonClicked(int position);
    }

    @Override
    public void onNewPlaylist(String name) {
        addPlaylist(name);
    }

    /**
     * Utility
     */
    private boolean isPlaylistAdded(Playlist playlist) {
        boolean added = false;
        for (Playlist p : playlists) {
            if (p.getId().equals(playlist.getId())) {
                added = true;
                break;
            }
        }
        return added;
    }
}
