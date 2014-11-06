package com.sbhachu.bbcnewsreader.presentation.view;

import android.content.Context;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.sbhachu.bbcnewsreader.R;
import com.sbhachu.bbcnewsreader.data.model.Item;
import com.sbhachu.bbcnewsreader.data.model.Thumbnail;
import com.squareup.picasso.Picasso;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

/**
 * Created by sbhachu on 29/10/2014.
 */
@EViewGroup(R.layout.news_list_item)
public class NewsListItemAdapterView extends RelativeLayout {

    @ViewById(R.id.news_item_image_view)
    public ImageView imageView;

    @ViewById(R.id.news_item_title)
    public CustomTextView titleTextView;

    @ViewById(R.id.news_item_description)
    public CustomTextView descriptionTextView;

    public NewsListItemAdapterView(Context context) {
        super(context);
    }

    public void bind(Item item) {
        Thumbnail thumbnail = item.getThumbnails().get(1);
        Picasso.with(getContext()).load(thumbnail.getUrl()).resize(250,250).centerCrop().into(imageView);

        titleTextView.setText(item.getTitle());
        descriptionTextView.setText(item.getDescription());
    }
}
