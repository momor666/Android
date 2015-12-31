package com.sbhachu.kotlin.newsreader.network

import com.sbhachu.kotlin.newsreader.domain.Constants
import com.squareup.okhttp.OkHttpClient
import com.squareup.okhttp.logging.HttpLoggingInterceptor
import retrofit.Retrofit
import retrofit.RxJavaCallAdapterFactory
import retrofit.SimpleXmlConverterFactory

object ApiController {

    public val client: IApiClient = createAdapter().create(IApiClient::class.java)

    fun createAdapter(): Retrofit {
        val logger: HttpLoggingInterceptor = HttpLoggingInterceptor()
        logger.setLevel(HttpLoggingInterceptor.Level.BASIC)

        val okHttpClient: OkHttpClient = OkHttpClient()
        okHttpClient.interceptors().add(logger)

        val adapter = Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()

        return adapter
    }
}


