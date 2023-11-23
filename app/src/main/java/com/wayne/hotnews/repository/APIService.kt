package com.wayne.hotnews.repository

import android.net.Uri
import android.util.Log
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.wayne.hotnews.repository.dataObject.NewsData
import com.wayne.hotnews.repository.dataObject.NewsSource
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/*
 * Copyright (c) 2023 GoMore Inc. All rights reserved.
 *
 * Created by Wayne Jiang on 2023/11/23
 */
class APIService @Inject constructor() {
    private val mOkHttpClient by lazy {
        OkHttpClient.Builder().apply {
            retryOnConnectionFailure(true)
            connectTimeout(300, TimeUnit.SECONDS)
            writeTimeout(300, TimeUnit.SECONDS)
            readTimeout(300, TimeUnit.SECONDS)
            addInterceptor(HttpLoggingInterceptor(HttpLogger())
                .apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
        }.build()
    }

    private class HttpLogger : HttpLoggingInterceptor.Logger {
        override fun log(message: String) {
            val maxLength = 2000
            var start = 0
            var end = maxLength
            val logLength = message.length
            for (index in 0 until 100) {
                if (logLength > end) {
                    Log.d(
                        "Wayne",
                        Uri.decode(message.substring(start, end))
                    )
                    start = end
                    end += maxLength
                } else {
                    Log.d(
                        "Wayne",
                        Uri.decode(message.substring(start, logLength))
                    )
                    break
                }
            }
        }
    }

    private val mAPIInterface by lazy {
        Retrofit.Builder()
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(
                MoshiConverterFactory.create(
                    Moshi.Builder()
                        .add(KotlinJsonAdapterFactory())
                        .build()
                )
            )
            .baseUrl("https://newsapi.org/")
            .client(mOkHttpClient)
            .build()
            .create(APIInterface::class.java)
    }

    suspend fun getTopHeadlineAsync(): List<NewsData> {
        try {
            val response =
                mAPIInterface.getTopHeadlines(
                    "us",
                    "60968c33c91b4dbc8f9635c39fd48d57"
                ).await()

            if (response.isSuccessful) {
                response.body()?.let {
                    if (it.status == "ok") {
                        return it.articles.map { article ->
                            NewsData().apply {
                                source =
                                    NewsSource().apply {
                                        id = article.sourceJson.id ?: ""
                                        name = article.sourceJson.name ?: ""
                                    }
                                author = article.author ?: ""
                                title = article.title ?: ""
                                description = article.description ?: ""
                                url = article.url ?: ""
                                urlToImage = article.urlToImage ?: ""
                                publishedAt = article.publishedAt ?: ""
                                content = article.content ?: ""
                            }
                        }
                    }
                }
            }

            return emptyList()
        } catch (throwable: Throwable) {
            Log.d("Wayne", Log.getStackTraceString(throwable))

            return emptyList()
        }
    }
}