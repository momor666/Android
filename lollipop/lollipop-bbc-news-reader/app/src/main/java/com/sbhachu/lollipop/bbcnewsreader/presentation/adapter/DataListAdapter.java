package com.sbhachu.lollipop.bbcnewsreader.presentation.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sbhachu.lollipop.bbcnewsreader.R;
import com.sbhachu.lollipop.bbcnewsreader.data.model.Item;
import com.sbhachu.lollipop.bbcnewsreader.data.model.Thumbnail;
import com.sbhachu.lollipop.bbcnewsreader.presentation.view.DataListItemView;
import com.sbhachu.lollipop.bbcnewsreader.util.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sbhachu on 11/11/2014.
 */
public class DataListAdapter extends RecyclerView.Adapter<DataListItemView> {

    private static final String TAG = DataListAdapter.class.getSimpleName();

    private Context context;

    private List<Item> items = new ArrayList<Item>();

    private Item item;

    private ItemClickListener listener;

    public DataListAdapter(Context context) {
        this.context = context;
    }

    @Override
    public DataListItemView onCreateViewHolder(ViewGroup parent, int i) {
        View itemLayoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_data_list_item, null);

        return new DataListItemView(context, itemLayoutView, listener);
    }

    @Override
    public void onBindViewHolder(DataListItemView view, int position) {
        item = items.get(position);

        if (item.getThumbnails() != null && item.getThumbnails().size() > 1) {

            Thumbnail thumbnail = item.getThumbnails().get(1);
            view.setImageViewPath(thumbnail.getUrl());
        }

        view.setTitleTextViewLabel(item.getTitle());
        view.setSubtitleTextView(item.getDescription());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public static interface ItemClickListener {
        public void onClick(DataListItemView view, int position);
    }

    public void setItemClickListener(final ItemClickListener listener) {
        this.listener = listener;
    }

//    private DataListItemView.ItemClickListener clickListener = new DataListItemView.ItemClickListener() {
//
//        @Override
//        public void onClick(DataListItemView view, int position) {
//
//            Item i = items.get(position);
//            Logger.i(TAG, i.toString());
//
//        }
//
//    };
}
