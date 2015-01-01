package com.sbhachu.audioplayer.presentation.view;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sbhachu.audioplayer.R;
import com.sbhachu.audioplayer.presentation.fragment.PlaylistTracksFragment;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

/**
 * Created by sbhachu on 28/12/2014.
 */
@EViewGroup(R.layout.view_playlist_info_header)
public class PlaylistTracksInfoHeaderView extends LinearLayout {

    @ViewById(R.id.playlist_title_tv)
    public TextView playlistTitleTextView;

    @ViewById(R.id.playlist_count_tv)
    public TextView playlistCountTextView;

    private PlaylistTracksFragment.PlaylistEditModeListener listener;

    public PlaylistTracksFragment.PlaylistEditModeListener getListener() {
        return listener;
    }

    public void setListener(PlaylistTracksFragment.PlaylistEditModeListener listener) {
        this.listener = listener;
    }

    public PlaylistTracksInfoHeaderView(Context context) {
        super(context);
    }

    public void bindData(String playlist, int count) {
        playlistTitleTextView.setText(playlist);
        playlistCountTextView.setText(count + " " + (count == 1 ? "song" : "songs"));

        if (listener != null) {
            this.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onToggleEdit();
                }
            });
        }
    }
}
