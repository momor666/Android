package com.sbhachu.audioplayer.presentation.view;

import android.content.Context;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sbhachu.audioplayer.R;
import com.sbhachu.audioplayer.data.model.media.Genre;
import com.squareup.picasso.Picasso;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

/**
 * Created by sbhachu on 28/12/2014.
 */
@EViewGroup(R.layout.view_genre_parallax)
public class GenreParallaxHeaderView extends FrameLayout {

    @ViewById(R.id.genre_name_large_tv)
    public TextView genreNameLargeTextView;

    public GenreParallaxHeaderView(Context context) {
        super(context);
    }

    public void bindData(Genre genre) {
        genreNameLargeTextView.setText(genre.getName());
    }
}
