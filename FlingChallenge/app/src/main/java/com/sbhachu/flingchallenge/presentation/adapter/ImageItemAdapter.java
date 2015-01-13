package com.sbhachu.flingchallenge.presentation.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.sbhachu.flingchallenge.data.model.ImageItem;
import com.sbhachu.flingchallenge.presentation.view.ImageItemAdapterView;
import com.sbhachu.flingchallenge.presentation.view.ImageItemAdapterView_;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.List;

/**
 * Image List Adapter
 */
@EBean
public class ImageItemAdapter extends BaseAdapter {

    @RootContext
    public Context context;

    private List<ImageItem> imageItems;

    public List<ImageItem> getImageItems() {
        return imageItems;
    }

    public void setImageItems(List<ImageItem> imageItems) {
        this.imageItems = imageItems;
    }

    @Override
    public int getCount() {
        return imageItems.size();
    }

    @Override
    public Object getItem(int position) {
        return imageItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ImageItemAdapterView adapterView;

        if (convertView == null) {
            adapterView = ImageItemAdapterView_.build(context.getApplicationContext());
        } else {
            adapterView = (ImageItemAdapterView) convertView;
        }

        adapterView.bind(imageItems.get(position));

        return adapterView;
    }
}
