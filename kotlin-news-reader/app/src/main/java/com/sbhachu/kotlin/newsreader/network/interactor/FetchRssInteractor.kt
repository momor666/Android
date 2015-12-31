package com.sbhachu.kotlin.newsreader.network.interactor

import com.sbhachu.kotlin.newsreader.domain.model.Channel
import com.sbhachu.kotlin.newsreader.domain.model.RSS
import com.sbhachu.kotlin.newsreader.network.ApiController
import com.sbhachu.kotlin.newsreader.network.IApiClient
import rx.Observable

public class FetchRssInteractor {

    public val client : IApiClient = ApiController.client

    public fun getTopStories() : Observable<RSS> = client.getTopStories();
}


