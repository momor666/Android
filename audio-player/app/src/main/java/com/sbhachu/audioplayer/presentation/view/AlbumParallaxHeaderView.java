package com.sbhachu.audioplayer.presentation.view;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.sbhachu.audioplayer.R;
import com.squareup.picasso.Picasso;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

/**
 * Created by sbhachu on 28/12/2014.
 */
@EViewGroup(R.layout.view_album_parallax)
public class AlbumParallaxHeaderView extends LinearLayout {

    @ViewById(R.id.large_album_art)
    public ImageView albumArtImageView;


    public AlbumParallaxHeaderView(Context context) {
        super(context);
    }

    public void bindData(Uri imageUri) {
        Picasso.with(getContext()).load(imageUri).placeholder(
                R.drawable.album_placeholder).into(albumArtImageView);
    }
}
