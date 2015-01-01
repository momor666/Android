package com.sbhachu.audioplayer.presentation.view;

import android.content.Context;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sbhachu.audioplayer.R;
import com.sbhachu.audioplayer.data.model.media.Track;
import com.sbhachu.audioplayer.util.StringUtil;
import com.squareup.picasso.Picasso;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup(R.layout.view_queue_item)
public class QueueItemAdapterView extends LinearLayout {

    @ViewById(R.id.track_title_tv)
    public TextView titleTextView;

    @ViewById(R.id.track_artist_tv)
    public TextView artistTextView;

    @ViewById(R.id.duration_tv)
    public TextView durationTextView;

    @ViewById(R.id.track_image)
    public ImageView albumArt;

    @ViewById(R.id.queue_delete_item_iv)
    public ImageView deleteImageView;

    public QueueItemAdapterView(Context context) {
        super(context);
    }

    public void bind(Track track) {
        titleTextView.setText(track.getTitle());
        artistTextView.setText(track.getArtist());
        durationTextView.setText(StringUtil.getTimeString(track.getDuration()));

        Picasso.with(getContext()).load(track.getAlbumArtUri())
                .placeholder(R.drawable.album_placeholder)
                .into(albumArt);
    }
}
