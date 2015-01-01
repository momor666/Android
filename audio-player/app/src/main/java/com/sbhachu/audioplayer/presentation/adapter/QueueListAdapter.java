package com.sbhachu.audioplayer.presentation.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.sbhachu.audioplayer.data.model.media.Track;
import com.sbhachu.audioplayer.presentation.fragment.AudioPlayerFragment;
import com.sbhachu.audioplayer.presentation.view.QueueItemAdapterView;
import com.sbhachu.audioplayer.presentation.view.QueueItemAdapterView_;
import com.sbhachu.audioplayer.presentation.view.TrackItemAdapterView;
import com.sbhachu.audioplayer.presentation.view.TrackItemAdapterView_;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.List;

/**
 * Created by sbhachu on 22/12/2014.
 */
@EBean
public class QueueListAdapter extends BaseAdapter {

    @RootContext
    public Context context;

    private List<Track> tracks;

    private AudioPlayerFragment.QueueItemListener listener;

    public List<Track> getTracks() {
        return tracks;
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }

    public AudioPlayerFragment.QueueItemListener getListener() {
        return listener;
    }

    public void setListener(AudioPlayerFragment.QueueItemListener listener) {
        this.listener = listener;
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
    public View getView(final int position, View convertView, ViewGroup parent)  {

        QueueItemAdapterView adapterView;

        if (convertView == null) {
            adapterView = QueueItemAdapterView_.build(context);
        } else {
            adapterView = (QueueItemAdapterView) convertView;
        }

        adapterView.bind(tracks.get(position));

        adapterView.deleteImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null)
                    listener.onDeleteButtonClicked(position);
            }
        });

        return adapterView;
    }
}
