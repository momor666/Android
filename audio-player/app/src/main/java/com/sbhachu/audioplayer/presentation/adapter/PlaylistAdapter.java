package com.sbhachu.audioplayer.presentation.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.sbhachu.audioplayer.data.model.media.Playlist;
import com.sbhachu.audioplayer.presentation.fragment.PlaylistFragment;
import com.sbhachu.audioplayer.presentation.view.PlaylistItemAdapterView;
import com.sbhachu.audioplayer.presentation.view.PlaylistItemAdapterView_;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.List;

/**
 * Created by sbhachu on 24/12/2014.
 */
@EBean
public class PlaylistAdapter extends BaseAdapter {

    @RootContext
    public Context context;

    private List<Playlist> playlists;

    private PlaylistFragment.PlaylistItemListener listener;

    public List<Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(List<Playlist> playlists) {
        this.playlists = playlists;
    }

    public PlaylistFragment.PlaylistItemListener getListener() {
        return listener;
    }

    public void setListener(PlaylistFragment.PlaylistItemListener listener) {
        this.listener = listener;
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
    public View getView(final int position, View convertView, ViewGroup parent) {

        PlaylistItemAdapterView adapterView;

        if (convertView == null) {
            adapterView = PlaylistItemAdapterView_.build(context);
        } else {
            adapterView = (PlaylistItemAdapterView) convertView;
        }

        adapterView.bind(playlists.get(position));

        adapterView.playlistDeleteImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null)
                    listener.onDeleteButtonClicked(position);
            }
        });

        return adapterView;
    }

//    public interface PlaylistItemInterationListener {
//        public void onDeleteButtonClicked(int position);
//    }
}
