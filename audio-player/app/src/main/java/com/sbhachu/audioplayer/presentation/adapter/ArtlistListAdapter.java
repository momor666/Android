package com.sbhachu.audioplayer.presentation.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.sbhachu.audioplayer.data.model.media.Artist;
import com.sbhachu.audioplayer.presentation.view.ArtistListAdapterView;
import com.sbhachu.audioplayer.presentation.view.ArtistListAdapterView_;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.List;

/**
 * Created by sbhachu on 24/12/2014.
 */
@EBean
public class ArtlistListAdapter extends BaseAdapter {

    @RootContext
    public Context context;

    private List<Artist> artists;

    public ArtlistListAdapter() {
    }

    public List<Artist> getArtists() {
        return artists;
    }

    public void setArtists(List<Artist> artists) {
        this.artists = artists;
    }

    @Override
    public int getCount() {
        return artists.size();
    }

    @Override
    public Object getItem(int position) {
        return artists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ArtistListAdapterView adapterView;

        if (convertView == null) {
            adapterView = ArtistListAdapterView_.build(context);
        } else {
            adapterView = (ArtistListAdapterView) convertView;
        }

        adapterView.bind(artists.get(position));

        return adapterView;
    }
}
