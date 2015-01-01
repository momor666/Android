package com.sbhachu.audioplayer.presentation.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.sbhachu.audioplayer.data.model.media.Track;
import com.sbhachu.audioplayer.presentation.view.TrackItemAdapterView;
import com.sbhachu.audioplayer.presentation.view.TrackItemAdapterViewNoArt;
import com.sbhachu.audioplayer.presentation.view.TrackItemAdapterViewNoArt_;
import com.sbhachu.audioplayer.presentation.view.TrackItemAdapterView_;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.List;

/**
 * Created by sbhachu on 22/12/2014.
 */
@EBean
public class TrackListNoArtAdapter extends BaseAdapter {

    @RootContext
    public Context context;

    private List<Track> tracks;

    public List<Track> getTracks() {
        return tracks;
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }

    @Override
    public int getCount() {
        return tracks.size();
    }

    @Override
    public Object getItem(int position) {
        return tracks.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)  {

        TrackItemAdapterViewNoArt adapterView;

        if (convertView == null) {
            adapterView = TrackItemAdapterViewNoArt_.build(context);
        } else {
            adapterView = (TrackItemAdapterViewNoArt) convertView;
        }

        adapterView.bind(position, tracks.get(position));

        return adapterView;
    }
}
