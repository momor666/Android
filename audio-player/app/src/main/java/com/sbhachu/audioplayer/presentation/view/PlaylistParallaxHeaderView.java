package com.sbhachu.audioplayer.presentation.view;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.sbhachu.audioplayer.R;
import com.sbhachu.audioplayer.data.model.media.Playlist;
import com.sbhachu.audioplayer.util.ImageUtil;
import com.sbhachu.audioplayer.util.Logger;
import com.squareup.picasso.Picasso;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

/**
 * Created by sbhachu on 28/12/2014.
 */
@EViewGroup(R.layout.view_playlist_parallax)
public class PlaylistParallaxHeaderView extends LinearLayout {

    @ViewById(R.id.playlist_large_art_iv_1)
    public ImageView playlistLargeImageView1;

    @ViewById(R.id.playlist_large_art_iv_2)
    public ImageView playlistLargeImageView2;

    @ViewById(R.id.playlist_large_art_iv_3)
    public ImageView playlistLargeImageView3;

    @ViewById(R.id.playlist_large_art_iv_4)
    public ImageView playlistLargeImageView4;

    @Bean
    public ImageUtil imageUtil;

    public PlaylistParallaxHeaderView(Context context) {
        super(context);
    }

    public void bindData(Playlist playlist) {

        Uri image1 = playlist.getImages().size() > 0 ? playlist.getImages().get(0) :
                imageUtil.getPlaceholderImageUri();
        Uri image2 = playlist.getImages().size() > 1 ? playlist.getImages().get(1) :
                imageUtil.getPlaceholderImageUri();
        Uri image3 = playlist.getImages().size() > 2 ? playlist.getImages().get(2) :
                imageUtil.getPlaceholderImageUri();
        Uri image4 = playlist.getImages().size() > 3 ? playlist.getImages().get(3) :
                imageUtil.getPlaceholderImageUri();

        Picasso.with(getContext()).load(image1).into(playlistLargeImageView1);
        Picasso.with(getContext()).load(image2).into(playlistLargeImageView2);
        Picasso.with(getContext()).load(image3).into(playlistLargeImageView3);
        Picasso.with(getContext()).load(image4).into(playlistLargeImageView4);
    }
}
