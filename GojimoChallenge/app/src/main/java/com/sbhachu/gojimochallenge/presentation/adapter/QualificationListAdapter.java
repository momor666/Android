package com.sbhachu.gojimochallenge.presentation.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.sbhachu.gojimochallenge.data.model.Qualification;
import com.sbhachu.gojimochallenge.presentation.view.QualificationListAdapterView;
import com.sbhachu.gojimochallenge.presentation.view.QualificationListAdapterView_;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.List;

/**
 * QualificationListAdapter.class
 * Qualification list adapter, manages views in list recycling and instantiating as needed.
 */
@EBean
public class QualificationListAdapter extends BaseAdapter {

    @RootContext
    public Context context;

    private List<Qualification> qualificationList;

    public List<Qualification> getQualificationList() {
        return qualificationList;
    }

    public void setQualificationList(List<Qualification> qualificationList) {
        this.qualificationList = qualificationList;
    }

    @Override
    public int getCount() {
        return qualificationList.size();
    }

    @Override
    public Object getItem(int position) {
        return qualificationList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        QualificationListAdapterView adapterView;

        if (convertView == null) {
            adapterView = QualificationListAdapterView_.build(context);
        } else {
            adapterView = (QualificationListAdapterView) convertView;
        }

        adapterView.bind(qualificationList.get(position));

        return adapterView;
    }
}
