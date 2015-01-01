package com.sbhachu.audioplayer.presentation.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sbhachu.audioplayer.R;
import com.sbhachu.audioplayer.data.model.media.Album;
import com.squareup.picasso.Picasso;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup(R.layout.view_album_item)
public class AlbumItemAdapterView extends LinearLayout {

    @ViewById(R.id.album_art_iv)
    public ImageView albumArtImageView;

    @ViewById(R.id.album_title_tv)
    public TextView albumTitleTextView;

    @ViewById(R.id.album_artist_tv)
    public TextView albumArtistTextView;

    @ViewById(R.id.album_count_tv)
    public TextView albumTrackCountTextView;

    public AlbumItemAdapterView(Context context) {
        super(context);
    }

    public void bind(Album album) {

        albumTitleTextView.setText(album.getName());
        albumArtistTextView.setText(album.getArtist());

        String song = album.getSongCount() == 1 ? " song" : " songs";
        albumTrackCountTextView.setText(album.getSongCount() + song);

        Picasso.with(getContext()).load(album.getAlbumArtUri()).placeholder(R.drawable.album_placeholder).into(albumArtImageView);
    }
}
