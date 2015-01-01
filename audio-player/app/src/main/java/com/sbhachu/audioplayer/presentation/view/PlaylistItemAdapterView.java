package com.sbhachu.audioplayer.presentation.view;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sbhachu.audioplayer.R;
import com.sbhachu.audioplayer.data.model.media.Playlist;
import com.sbhachu.audioplayer.util.ImageUtil;
import com.squareup.picasso.Picasso;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

/**
 * Created by sbhachu on 24/12/2014.
 */
@EViewGroup(R.layout.view_playlist_item)
public class PlaylistItemAdapterView extends LinearLayout {
    @ViewById(R.id.playlist_art_iv_1)
    public ImageView playlistArtImageView1;

    @ViewById(R.id.playlist_art_iv_2)
    public ImageView playlistArtImageView2;

    @ViewById(R.id.playlist_art_iv_3)
    public ImageView playlistArtImageView3;

    @ViewById(R.id.playlist_art_iv_4)
    public ImageView playlistArtImageView4;

    @ViewById(R.id.playlist_title_tv)
    public TextView playlistTitleTextView;

    @ViewById(R.id.playlist_delete_iv)
    public ImageView playlistDeleteImageView;

    @Bean
    public ImageUtil imageUtil;

    public PlaylistItemAdapterView(Context context) {
        super(context);
    }

    public void bind(Playlist playlist) {

        playlistTitleTextView.setText(playlist.getName());

        Uri image1 = playlist.getImages().size() > 0 ? playlist.getImages().get(0) :
                imageUtil.getPlaceholderImageUri();
        Uri image2 = playlist.getImages().size() > 1 ? playlist.getImages().get(1) :
                imageUtil.getPlaceholderImageUri();
        Uri image3 = playlist.getImages().size() > 2 ? playlist.getImages().get(2) :
                imageUtil.getPlaceholderImageUri();
        Uri image4 = playlist.getImages().size() > 3 ? playlist.getImages().get(3) :
                imageUtil.getPlaceholderImageUri();

        Picasso.with(getContext()).load(image1).into(playlistArtImageView1);
        Picasso.with(getContext()).load(image2).into(playlistArtImageView2);
        Picasso.with(getContext()).load(image3).into(playlistArtImageView3);
        Picasso.with(getContext()).load(image4).into(playlistArtImageView4);
    }
}
