package com.sbhachu.gojimochallenge.presentation.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.sbhachu.gojimochallenge.data.model.Subject;
import com.sbhachu.gojimochallenge.presentation.view.SubjectListAdapterView;
import com.sbhachu.gojimochallenge.presentation.view.SubjectListAdapterView_;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.List;

/**
 * SubjectListAdapter.class
 * Subject list adapter, manages views in list recycling and instantiating as needed.
 */
@EBean
public class SubjectListAdapter extends BaseAdapter {

    @RootContext
    public Context context;

    private List<Subject> subjects;

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    @Override
    public int getCount() {
        return subjects.size();
    }

    @Override
    public Object getItem(int position) {
        return subjects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        SubjectListAdapterView adapterView;

        if (convertView == null) {
            adapterView = SubjectListAdapterView_.build(context);
        } else {
            adapterView = (SubjectListAdapterView) convertView;
        }

        adapterView.bind(subjects.get(position));

        return adapterView;
    }
}
