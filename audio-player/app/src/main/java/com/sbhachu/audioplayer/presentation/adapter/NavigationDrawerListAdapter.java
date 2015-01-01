package com.sbhachu.audioplayer.presentation.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.sbhachu.audioplayer.data.model.NavigationItem;
import com.sbhachu.audioplayer.presentation.view.NavigationDrawerListItemAdapterView;
import com.sbhachu.audioplayer.presentation.view.NavigationDrawerListItemAdapterView_;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.List;

/**
 * Created by sbhachu on 29/10/2014.
 */
@EBean
public class NavigationDrawerListAdapter extends BaseAdapter {

    @RootContext
    public Context context;

    private List<NavigationItem> navigationDrawerItems;

    public List<NavigationItem> getNavigationDrawerItems() {
        return navigationDrawerItems;
    }

    public void setNavigationDrawerItems(List<NavigationItem> navigationDrawerItems) {
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

        adapterView.bind(navigationDrawerItems.get(position));

        return adapterView;
    }
}
