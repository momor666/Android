package com.sbhachu.audioplayer.presentation.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.sbhachu.audioplayer.data.model.media.Genre;
import com.sbhachu.audioplayer.presentation.view.GenreItemAdapterView;
import com.sbhachu.audioplayer.presentation.view.GenreItemAdapterView_;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.List;

/**
 * Created by sbhachu on 24/12/2014.
 */
@EBean
public class GenreListAdapter extends BaseAdapter {

    @RootContext
    public Context context;

    public List<Genre> genres;

    public GenreListAdapter() {
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    @Override
    public int getCount() {
        return genres.size();
    }

    @Override
    public Object getItem(int position) {
        return genres.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        GenreItemAdapterView adapterView;

        if (convertView == null) {
            adapterView = GenreItemAdapterView_.build(context);
        } else {
            adapterView = (GenreItemAdapterView) convertView;
        }

        adapterView.bind(genres.get(position));

        return adapterView;
    }
}
