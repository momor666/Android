package com.sbhachu.lollipop.bbcnewsreader.presentation.view;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sbhachu.lollipop.bbcnewsreader.R;
import com.sbhachu.lollipop.bbcnewsreader.presentation.adapter.DataListAdapter;
import com.sbhachu.lollipop.bbcnewsreader.util.Logger;
import com.squareup.picasso.Picasso;

/**
 * Created by sbhachu on 11/11/2014.
 */
public class DataListItemView extends RecyclerView.ViewHolder implements View.OnClickListener {

    public static final String TAG = DataListItemView.class.getSimpleName();

    private Context context;
    private DataListAdapter.ItemClickListener listener;

    private CardView cardView;
    private ImageView imageView;
    private TextView titleTextView;
    private TextView subtitleTextView;

    public DataListItemView(Context context, View view, DataListAdapter.ItemClickListener listener) {
        super(view);

        this.context = context;
        this.listener = listener;

        cardView = (CardView) view.findViewById(R.id.card_view);

        imageView = (ImageView) view.findViewById(R.id.news_item_image);
        titleTextView = (TextView) view.findViewById(R.id.news_item_title);
        subtitleTextView = (TextView) view.findViewById(R.id.news_item_subtitle);

        view.setOnClickListener(this);
    }


    public void setTitleTextViewLabel(String title) {
        this.titleTextView.setText(title);
    }

    public void setSubtitleTextView(String subtitle) {
        this.subtitleTextView.setText(subtitle);
    }

    public void setImageViewPath(String imageUrl) {
        Picasso.with(context).load(imageUrl).resize(250, 250).centerCrop().into(imageView);
    }

    @Override
    public void onClick(View view) {
        if (listener != null) {
            listener.onClick(this, getPosition());
        }
    }


}
