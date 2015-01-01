package com.sbhachu.audioplayer.presentation.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.sbhachu.audioplayer.data.model.media.Playlist;
import com.sbhachu.audioplayer.presentation.view.PlaylistSelectionItemAdapterView;
import com.sbhachu.audioplayer.presentation.view.PlaylistSelectionItemAdapterView_;
import com.sbhachu.audioplayer.presentation.view.TrackItemAdapterView;
import com.sbhachu.audioplayer.presentation.view.TrackItemAdapterView_;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.List;

@EBean
public class PlaylistSelectionListAdapter extends BaseAdapter {

    @RootContext
    public Context context;

    private List<Playlist> playlists;

    public List<Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(List<Playlist> playlists) {
        this.playlists = playlists;
    }

    @Override
    public int getCount() {
        return playlists.size();
    }

    @Override
    public Object getItem(int position) {
        return playlists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)  {

        PlaylistSelectionItemAdapterView adapterView;

        if (convertView == null) {
            adapterView = PlaylistSelectionItemAdapterView_.build(context);
        } else {
            adapterView = (PlaylistSelectionItemAdapterView) convertView;
        }

        adapterView.bind(playlists.get(position));

        return adapterView;
    }
}
