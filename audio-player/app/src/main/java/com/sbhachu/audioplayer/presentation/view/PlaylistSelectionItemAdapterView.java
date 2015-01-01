package com.sbhachu.audioplayer.presentation.view;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sbhachu.audioplayer.R;
import com.sbhachu.audioplayer.data.model.media.Genre;
import com.sbhachu.audioplayer.data.model.media.Playlist;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;
import org.apache.commons.lang3.text.WordUtils;

/**
 * Created by sbhachu on 24/12/2014.
 */
@EViewGroup(R.layout.view_playlist_selection_item)
public class PlaylistSelectionItemAdapterView extends LinearLayout {

    @ViewById(R.id.playlist_selection_item_tv)
    public TextView playlistTextView;

    public PlaylistSelectionItemAdapterView(Context context) {
        super(context);
    }

    public void bind(Playlist playlist) {
        playlistTextView.setText(WordUtils.capitalize(playlist.getName()));
    }
}
