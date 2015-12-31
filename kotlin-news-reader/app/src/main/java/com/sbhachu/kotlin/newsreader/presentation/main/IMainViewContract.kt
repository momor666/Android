package com.sbhachu.kotlin.newsreader.presentation.main

import com.sbhachu.kotlin.newsreader.domain.model.Item
import com.sbhachu.kotlin.newsreader.presentation.common.IViewContract

interface IMainViewContract : IViewContract {

    fun updateArticleList(articles : List<Item>)
    fun fetchDataError(error : String)
}


