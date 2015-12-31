package com.sbhachu.kotlin.newsreader.presentation.common

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import com.sbhachu.kotlin.newsreader.R

public abstract class BaseActivity<T> : AppCompatActivity() {

    protected abstract val layoutId: Int

    protected var presenter: T

    init {
        presenter = initialisePresenter()
    }

    val toolbar: Toolbar by lazy { findViewById(R.id.toolbar) as Toolbar }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // set view content
        setContentView(layoutId)
        setSupportActionBar(toolbar)

        // initialise views
        initialiseViews()

        // initialise listeners
        initialiseListeners()
    }

    protected abstract fun initialisePresenter(): T

    protected abstract fun initialiseViews(): Unit

    protected abstract fun initialiseListeners(): Unit
}
