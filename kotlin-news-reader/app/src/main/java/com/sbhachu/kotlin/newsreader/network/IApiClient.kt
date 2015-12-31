package com.sbhachu.kotlin.newsreader.network

import com.sbhachu.kotlin.newsreader.domain.model.RSS
import retrofit.http.GET
import rx.Observable


public interface IApiClient {

    @GET("/news/rss.xml")
    fun getTopStories(): Observable<RSS>

    @GET("/news/world/rss.xml")
    fun getWorldNewsStories(): Observable<RSS>

    @GET("/news/uk/rss.xml")
    fun getUKNewsStories(): Observable<RSS>

    @GET("/news/business/rss.xml")
    fun getBusinessNewsStories(): Observable<RSS>

    @GET("/news/politics/rss.xml")
    fun getPoliticsNewsStories(): Observable<RSS>

    @GET("/news/health/rss.xml")
    fun getHealthNewsStories(): Observable<RSS>

    @GET("/news/education/rss.xml")
    fun getEducationNewsStories(): Observable<RSS>

    @GET("/news/science_and_environment/rss.xml")
    fun getScienceAndEnvironmentNewsStories(): Observable<RSS>

    @GET("/news/technology/rss.xml")
    fun getTechnologyNewsStories(): Observable<RSS>

    @GET("/news/entertainment_and_arts/rss.xml")
    fun getEntertainmentAndArtsNewsStories(): Observable<RSS>

}