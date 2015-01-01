package com.sbhachu.audioplayer.presentation.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.sbhachu.audioplayer.data.model.media.Track;
import com.sbhachu.audioplayer.presentation.fragment.PlaylistTracksFragment;
import com.sbhachu.audioplayer.presentation.view.PlaylistTrackItemAdapterView;
import com.sbhachu.audioplayer.presentation.view.PlaylistTrackItemAdapterView_;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.List;

@EBean
public class PlaylistTracksAdapter extends BaseAdapter {

    @RootContext
    public Context context;

    private List<Track> tracks;

    private PlaylistTracksFragment.PlaylistItemListener listener;

    private boolean isEditable = false;

    public List<Track> getTracks() {
        return tracks;
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }

    public PlaylistTracksFragment.PlaylistItemListener getListener() {
        return listener;
    }

    public void setListener(PlaylistTracksFragment.PlaylistItemListener listener) {
        this.listener = listener;
    }

    public boolean isEditable() {
        return isEditable;
    }

    public void setEditable(boolean isEditable) {
        this.isEditable = isEditable;
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
    public View getView(final int position, View convertView, ViewGroup parent) {

        PlaylistTrackItemAdapterView adapterView;

        if (convertView == null) {
            adapterView = PlaylistTrackItemAdapterView_.build(context);
        } else {
            adapterView = (PlaylistTrackItemAdapterView) convertView;
        }

        adapterView.bind(tracks.get(position));

        adapterView.deleteImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null)
                    listener.onDeleteButtonClicked(position);
            }
        });

        if (!isEditable) {
            adapterView.durationTextView.setVisibility(View.VISIBLE);
            adapterView.deleteImageView.setVisibility(View.GONE);
        }else {
            adapterView.durationTextView.setVisibility(View.GONE);
            adapterView.deleteImageView.setVisibility(View.VISIBLE);
        }

        return adapterView;
    }
}
