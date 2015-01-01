package com.sbhachu.audioplayer.presentation.view;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sbhachu.audioplayer.R;
import com.sbhachu.audioplayer.data.model.media.Genre;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;
import org.apache.commons.lang3.text.WordUtils;

/**
 * Created by sbhachu on 24/12/2014.
 */
@EViewGroup(R.layout.view_genre_item)
public class GenreItemAdapterView extends LinearLayout {

    @ViewById(R.id.genre_name_tv)
    public TextView genreNameTextView;

    public GenreItemAdapterView(Context context) {
        super(context);
    }

    public void bind(Genre genre) {
        genreNameTextView.setText(WordUtils.capitalize(genre.getName()));
    }
}
