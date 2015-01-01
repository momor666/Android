package com.sbhachu.audioplayer.presentation.view;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sbhachu.audioplayer.R;
import com.sbhachu.audioplayer.data.model.NavigationItem;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

/**
 * Created by sbhachu on 29/10/2014.
 */
@EViewGroup(R.layout.view_navigation_drawer_list_item)
public class NavigationDrawerListItemAdapterView extends LinearLayout {

    @ViewById(R.id.title_label)
    public TextView titleTextView;

    @ViewById(R.id.count_label)
    public TextView countTextView;

    public NavigationDrawerListItemAdapterView(Context context) {
        super(context);
    }

    public void bind(NavigationItem item) {
        titleTextView.setText(item.getLabel());
        countTextView.setText(""+item.getCount());
    }
}
