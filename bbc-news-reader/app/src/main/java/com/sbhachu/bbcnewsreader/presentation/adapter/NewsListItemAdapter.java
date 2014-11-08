package com.sbhachu.bbcnewsreader.presentation.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.sbhachu.bbcnewsreader.R;
import com.sbhachu.bbcnewsreader.data.model.Item;
import com.sbhachu.bbcnewsreader.data.model.Thumbnail;
import com.sbhachu.bbcnewsreader.presentation.view.CustomTextView;
import com.sbhachu.bbcnewsreader.presentation.view.NewsListItemAdapterView;
import com.sbhachu.bbcnewsreader.presentation.view.NewsListItemAdapterView_;
import com.squareup.picasso.Picasso;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sbhachu on 29/10/2014.
 */
@EBean
public class NewsListItemAdapter extends BaseAdapter {

    @RootContext
    public Context context;

    private List<Item> items = new ArrayList<Item>();

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        NewsListItemAdapterView adapterView;

        if (convertView == null) {
            adapterView = NewsListItemAdapterView_.build(context);
        } else {
            adapterView = (NewsListItemAdapterView) convertView;
        }

        adapterView.bind(items.get(position));

        return adapterView;
    }
}
