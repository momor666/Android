package com.sbhachu.audioplayer.presentation.view;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sbhachu.audioplayer.R;
import com.sbhachu.audioplayer.data.model.media.Artist;
import com.sbhachu.audioplayer.util.ImageUtil;
import com.squareup.picasso.Picasso;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;
import org.apache.commons.lang3.text.WordUtils;

/**
 * Created by sbhachu on 24/12/2014.
 */

@EViewGroup(R.layout.view_artist_item)
public class ArtistListAdapterView extends LinearLayout {

    @ViewById(R.id.artist_image)
    public ImageView artistImageView;

    @ViewById(R.id.artist_name_tv)
    public TextView artistNameTextView;

    @ViewById(R.id.album_count_tv)
    public TextView albumCountTextView;

    @Bean
    public ImageUtil imageUtil;

    public ArtistListAdapterView(Context context) {
        super(context);
    }

    public void bind(Artist artist) {

        Uri image = artist.getImageUri() != null ? artist.getImageUri() :
                imageUtil.getPlaceholderImageUri();

        Picasso.with(getContext()).load(image).placeholder(R.drawable.album_placeholder)
                .into(artistImageView);

        artistNameTextView.setText(WordUtils.capitalize(artist.getArtist().toLowerCase()));

        String albumCount = artist.getAlbumCount() + (artist.getAlbumCount() == 1 ? " album" : " albums");
        String songCount = artist.getTrackCount() + (artist.getTrackCount() == 1 ? " song" : " songs");

        albumCountTextView.setText(albumCount + "  /  " + songCount);
    }
}
