package com.sbhachu.bbcnewsreader.presentation.view;

import android.content.Context;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sbhachu.bbcnewsreader.R;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

/**
 * Created by sbhachu on 29/10/2014.
 */
@EViewGroup(R.layout.navigation_drawer_list_item)
public class NavigationDrawerListItemAdapterView extends RelativeLayout {

    @ViewById(R.id.title)
    public TextView titleTextView;

    public NavigationDrawerListItemAdapterView(Context context) {
        super(context);
    }

    public void bind(String item) {
        titleTextView.setText(item);
    }
}
