package com.sbhachu.audioplayer.presentation.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.sbhachu.audioplayer.data.model.media.Album;
import com.sbhachu.audioplayer.presentation.view.AlbumItemAdapterView;
import com.sbhachu.audioplayer.presentation.view.AlbumItemAdapterView_;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.List;

/**
 * Created by sbhachu on 23/12/2014.
 */
@EBean
public class AlbumListAdapter extends BaseAdapter {

    @RootContext
    public Context context;

    private List<Album> albums;

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }

    @Override
    public int getCount() {
        return albums.size();
    }

    @Override
    public Object getItem(int position) {
        return albums.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)  {

        AlbumItemAdapterView adapterView;

        if (convertView == null) {
            adapterView = AlbumItemAdapterView_.build(context);
        } else {
            adapterView = (AlbumItemAdapterView) convertView;
        }

        adapterView.bind(albums.get(position));

        return adapterView;
    }
}
