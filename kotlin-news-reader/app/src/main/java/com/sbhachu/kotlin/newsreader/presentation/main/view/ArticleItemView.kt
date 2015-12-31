package com.sbhachu.kotlin.newsreader.presentation.main.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.sbhachu.kotlin.newsreader.R
import com.sbhachu.kotlin.newsreader.domain.model.Item
import com.sbhachu.kotlin.newsreader.domain.model.Thumbnail
import com.sbhachu.kotlin.newsreader.presentation.common.recycler.BaseRecyclerAdapter
import com.squareup.picasso.Picasso

public class ArticleItemView : LinearLayout {

    constructor(context: Context?) : super(context) {
        initialiseView()
    }

    val imageView : ImageView by lazy { findViewById(R.id.item_image_view) as ImageView }
    val titleTextView : TextView by lazy { findViewById(R.id.news_item_title) as TextView }
    val descriptionTextView : TextView by lazy { findViewById(R.id.news_item_description) as TextView }

    private fun initialiseView() {
        val root : View = LayoutInflater.from(context).inflate(R.layout.view_article_item, this)

    }

    public fun bindView(item: Item, listener: BaseRecyclerAdapter.ItemListener<Item>?) {
        Picasso.with(context).cancelRequest(imageView)

        if (item.thumbnails != null && item.thumbnails.count() > 1) {
            Picasso.with(context).load(item.thumbnails[1].url).resize(350, 350).centerCrop().into(imageView)
        } else {
            imageView.setImageBitmap(null);
        }

        titleTextView.text = item.title
        descriptionTextView.text = item.description

        this.setOnClickListener({ view -> listener?.onItemClicked(view, item) })
    }
}

