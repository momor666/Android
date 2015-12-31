package com.sbhachu.kotlin.newsreader.presentation.common

import com.sbhachu.kotlin.newsreader.network.ApiController
import com.sbhachu.kotlin.newsreader.network.IApiClient

public abstract class BasePresenter<T : IViewContract>(listener : T) {

    public var listener : T? = listener

    public fun clear() {
        listener = null
    }

    abstract fun onResume(): Unit
    abstract fun onPause():Unit

}
