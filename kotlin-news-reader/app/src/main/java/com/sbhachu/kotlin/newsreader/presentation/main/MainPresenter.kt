package com.sbhachu.kotlin.newsreader.presentation.main

import android.util.Log
import com.sbhachu.kotlin.newsreader.domain.model.Item
import com.sbhachu.kotlin.newsreader.network.interactor.FetchRssInteractor
import com.sbhachu.kotlin.newsreader.presentation.common.BasePresenter
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

public class MainPresenter(listener: IMainViewContract) : BasePresenter<IMainViewContract>(listener) {

    private val TAG: String = "MainPresenter"

    private val interactor: FetchRssInteractor = FetchRssInteractor()

    override fun onResume() {
        interactor.getTopStories()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            listener?.updateArticleList(it.channel.items)
                        },
                        {
                            listener?.fetchDataError(it.toString())
                        })
    }

    override fun onPause() {

    }


}
