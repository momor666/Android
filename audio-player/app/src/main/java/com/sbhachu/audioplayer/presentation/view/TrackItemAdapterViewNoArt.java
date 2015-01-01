package com.sbhachu.audioplayer.presentation.view;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sbhachu.audioplayer.R;
import com.sbhachu.audioplayer.data.model.media.Track;
import com.sbhachu.audioplayer.util.StringUtil;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup(R.layout.view_track_item_no_art)
public class TrackItemAdapterViewNoArt extends LinearLayout {

    @ViewById(R.id.track_number)
    public TextView numberTextView;

    @ViewById(R.id.track_title_tv)
    public TextView titleTextView;

    @ViewById(R.id.track_artist_tv)
    public TextView artistTextView;

    @ViewById(R.id.duration_tv)
    public TextView durationTextView;

    public TrackItemAdapterViewNoArt(Context context) {
        super(context);
    }

    public void bind(int position, Track track) {
        numberTextView.setText("" + ++position);
        titleTextView.setText(track.getTitle());
        artistTextView.setText(track.getArtist());
//        albumTextView.setText(track.getAlbum());
        durationTextView.setText(StringUtil.getTimeString(track.getDuration()));

    }
}
