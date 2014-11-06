package com.sbhachu.bbcnewsreader.presentation.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sbhachu.bbcnewsreader.R;
import com.sbhachu.bbcnewsreader.presentation.view.NavigationDrawerListItemAdapterView;
import com.sbhachu.bbcnewsreader.presentation.view.NavigationDrawerListItemAdapterView_;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;
import org.androidannotations.annotations.ViewById;

import java.util.List;

/**
 * Created by sbhachu on 29/10/2014.
 */
@EBean
public class NavigationDrawerListAdapter extends BaseAdapter {

    @RootContext
    public Context context;

    private List<String> navigationDrawerItems;

    public List<String> getNavigationDrawerItems() {
        return navigationDrawerItems;
    }

    public void setNavigationDrawerItems(List<String> navigationDrawerItems) {
        this.navigationDrawerItems = navigationDrawerItems;
    }

    @Override
    public int getCount() {
        return navigationDrawerItems.size();
    }

    @Override
    public Object getItem(int position) {
        return navigationDrawerItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        NavigationDrawerListItemAdapterView adapterView;

        if (convertView == null) {
            adapterView = NavigationDrawerListItemAdapterView_.build(context);
        } else {
            adapterView = (NavigationDrawerListItemAdapterView) convertView;
        }

        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.navigation_drawer_list_item, null);
        }

        adapterView.bind(navigationDrawerItems.get(position));

        return adapterView;
    }
}
