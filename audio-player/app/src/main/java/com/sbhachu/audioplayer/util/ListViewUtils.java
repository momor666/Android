package com.sbhachu.audioplayer.util;

import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * Created by sbhachu on 24/12/2014.
 */
public class ListViewUtils {
    public static int getItemHeightofListView(ListView listView, int items) {

        ListAdapter listAdapter = listView.getAdapter();
        int listviewElementsHeight = 0;

        for (int i = 0; i < items; i++) {
            View childView = listAdapter.getView(i, null, listView);
            childView.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                              View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
            listviewElementsHeight += childView.getMeasuredHeight() + 3;
        }

        return listviewElementsHeight;

    }
}
