package com.sbhachu.kotlin.newsreader.presentation.main.adapter

import android.content.Context
import android.view.ViewGroup
import com.sbhachu.kotlin.newsreader.domain.model.Item
import com.sbhachu.kotlin.newsreader.presentation.common.recycler.BaseRecyclerAdapter
import com.sbhachu.kotlin.newsreader.presentation.common.recycler.BaseRecyclerView
import com.sbhachu.kotlin.newsreader.presentation.main.view.ArticleItemView

public class ArticlesAdapter(context : Context?) : BaseRecyclerAdapter<Item, ArticleItemView>() {

    private var context : Context? = context

    override fun onCreateItemView(parent: ViewGroup?, viewType: Int): ArticleItemView {
        return ArticleItemView(context)
    }

    override fun onBindViewHolder(holder: BaseRecyclerView<ArticleItemView>, position: Int) {
        val view : ArticleItemView = holder.itemView as ArticleItemView
        val article : Item = getItems()[position]

        view?.bindView(article, getItemListener())
    }

}


