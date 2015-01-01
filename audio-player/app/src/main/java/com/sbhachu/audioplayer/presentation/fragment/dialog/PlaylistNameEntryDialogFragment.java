package com.sbhachu.audioplayer.presentation.fragment.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.sbhachu.audioplayer.R;
import com.sbhachu.audioplayer.presentation.activity.MainActivity;
import com.sbhachu.audioplayer.presentation.fragment.PlaylistFragment;

import org.androidannotations.annotations.EFragment;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by sbhachu on 29/12/2014.
 */
@EFragment
public class PlaylistNameEntryDialogFragment extends DialogFragment {

    public EditText editText;

    private PlaylistNameListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        final View view = inflater.inflate(R.layout.fragment_playlist_name_entry_dialog, null);

        editText = (EditText) view.findViewById(R.id.playlist_name_edit_text);

        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity())
                .setTitle("New Playlist")
                .setCancelable(true)
                .setView(view)
                .setPositiveButton("Confirm",
                                   new DialogInterface.OnClickListener() {
                                       @Override
                                       public void onClick(DialogInterface dialogInterface, int i) {
                                           if (StringUtils.isNotEmpty(
                                                   editText.getText().toString()))
                                               listener.onNewPlaylist(
                                                       editText.getText().toString());
                                       }
                                   })
                .setNegativeButton("Cancel",
                                   new DialogInterface.OnClickListener() {
                                       public void onClick(DialogInterface dialog, int whichButton) {
                                           dismiss();
                                       }
                                   }
                );

        listener = (PlaylistNameListener) getTargetFragment();

        return alertDialogBuilder.create();
    }


    public interface PlaylistNameListener {
        public void onNewPlaylist(String name);
    }
}
