package com.sbhachu.audioplayer.presentation.view;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sbhachu.audioplayer.R;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;
import org.apache.commons.lang3.text.WordUtils;

/**
 * Created by sbhachu on 28/12/2014.
 */
@EViewGroup(R.layout.view_genre_info_header)
public class GenreTracksInfoHeaderView extends LinearLayout {

    @ViewById(R.id.genre_title_tv)
    public TextView genreNameTextView;

    @ViewById(R.id.genre_count_tv)
    public TextView genreCountTextView;

    public GenreTracksInfoHeaderView(Context context) {
        super(context);
    }

    public void bindData(String genre, int count) {
        genreNameTextView.setText(WordUtils.capitalize(genre));
        genreCountTextView.setText(count + " " + (count == 1 ? "song" : "songs"));
    }
}
