package com.sbhachu.kotlin.newsreader.presentation.main

import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import com.sbhachu.kotlin.newsreader.R
import com.sbhachu.kotlin.newsreader.domain.model.Item
import com.sbhachu.kotlin.newsreader.presentation.common.BaseActivity
import com.sbhachu.kotlin.newsreader.presentation.common.recycler.BaseRecyclerAdapter
import com.sbhachu.kotlin.newsreader.presentation.common.recycler.LineSeparatorDecorator
import com.sbhachu.kotlin.newsreader.presentation.main.adapter.ArticlesAdapter

class MainActivity : BaseActivity<MainPresenter>(), IMainViewContract, BaseRecyclerAdapter.ItemListener<Item> {

    private val TAG: String = "MainActivity"

    override val layoutId: Int = R.layout.activity_main

    private val recycler: RecyclerView by lazy { findViewById(R.id.article_recycler) as RecyclerView }

    private var adapter: ArticlesAdapter? = null

    override fun initialisePresenter(): MainPresenter {
        return MainPresenter(this);
    }

    override fun initialiseViews() {
        title = "Top Stories"

        // setup recycler view
        var linearLayourManager: LinearLayoutManager = LinearLayoutManager(this)
        linearLayourManager.orientation = LinearLayoutManager.VERTICAL

        recycler.setHasFixedSize(true)
        recycler.layoutManager = linearLayourManager
        recycler.itemAnimator = DefaultItemAnimator()
//        recycler.addItemDecoration(LineSeparatorDecorator(this))

        // setup adapter
        adapter = ArticlesAdapter(this);
        adapter?.setItemListener(this)

        recycler.adapter = adapter
    }

    override fun initialiseListeners() {

    }

    override fun onResume() {
        super.onResume()
        presenter.onResume()
    }

    override fun onPause() {
        super.onPause()
        presenter.onPause()
    }

    override fun updateArticleList(articles: List<Item>) {
        Log.e(TAG, articles.toString())
        adapter?.setItems(articles)
        adapter?.notifyDataSetChanged()
    }

    override fun fetchDataError(error: String) {
        Log.e(TAG, error)
    }

    override fun onItemClicked(view: View, item: Item) {
        Log.e(TAG, item.toString())
    }
}
