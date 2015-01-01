package com.sbhachu.audioplayer.presentation.view;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sbhachu.audioplayer.R;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

/**
 * Created by sbhachu on 28/12/2014.
 */
@EViewGroup(R.layout.view_album_info_header)
public class AlbumTracksInfoHeaderView extends LinearLayout {

    @ViewById(R.id.album_title_tv)
    public TextView albumTitleTextView;

    @ViewById(R.id.album_artist_tv)
    public TextView albumArtistTextView;

    @ViewById(R.id.album_count_tv)
    public TextView albumCountTextView;

    public AlbumTracksInfoHeaderView(Context context) {
        super(context);
    }

    public void bindData(String albumTitle, String artist, int count) {
        albumTitleTextView.setText(albumTitle);
        albumArtistTextView.setText(artist);
        albumCountTextView.setText(count + " " + (count == 1 ? "song" : "songs"));
    }
}
