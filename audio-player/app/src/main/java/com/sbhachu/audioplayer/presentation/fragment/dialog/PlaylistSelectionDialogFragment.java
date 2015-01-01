package com.sbhachu.audioplayer.presentation.fragment.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.sbhachu.audioplayer.R;
import com.sbhachu.audioplayer.data.AudioContentManagerImpl;
import com.sbhachu.audioplayer.data.model.media.Playlist;
import com.sbhachu.audioplayer.data.model.media.Track;
import com.sbhachu.audioplayer.listener.GlobalFragmentInteractionListener;
import com.sbhachu.audioplayer.presentation.activity.MainActivity;
import com.sbhachu.audioplayer.presentation.adapter.PlaylistSelectionListAdapter;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.SystemService;
import org.androidannotations.annotations.UiThread;

import java.util.List;

/**
 * Created by sbhachu on 29/12/2014.
 */
@EFragment
public class PlaylistSelectionDialogFragment extends DialogFragment {

    @FragmentArg
    public Track track;

    @Bean
    public AudioContentManagerImpl audioContentManager;

    @Bean
    public PlaylistSelectionListAdapter adapter;

    @SystemService
    public LayoutInflater inflater;

    public ListView listView;

    private List<Playlist> playlists;

    private GlobalFragmentInteractionListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        //LayoutInflater inflater = LayoutInflater.from(getActivity());
        final View view = inflater.inflate(R.layout.fragment_playlist_selection_dialog, null);

        listView = (ListView) view.findViewById(R.id.playlist_selection_lv);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Playlist selectedPlaylist = playlists.get(position);
                listener.onPlaylistDialogSelection(selectedPlaylist, track);
                dismissAllowingStateLoss();
            }
        });

        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity())
                .setTitle("Add Song to Playlist")
                .setCancelable(true)
                .setView(view)
                .setNegativeButton("Cancel",
                                   new DialogInterface.OnClickListener() {
                                       public void onClick(DialogInterface dialog, int whichButton) {
                                           dismiss();
                                       }
                                   }
                );

        listener = (MainActivity) getActivity();

        loadPlaylists();

        return alertDialogBuilder.create();
    }

    @Background
    public void loadPlaylists() {
        playlists = audioContentManager.loadAllPlaylists();
        adapter.setPlaylists(playlists);
        updateList();
    }

    @UiThread
    public void updateList() {
        if (playlists != null) {
            listView.setAdapter(adapter);
        }
    }
}
