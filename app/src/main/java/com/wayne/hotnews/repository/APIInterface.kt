package com.wayne.hotnews.repository

import com.wayne.hotnews.repository.jsonObject.NewsJson
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/*
 * Copyright (c) 2023 GoMore Inc. All rights reserved.
 *
 * Created by Wayne Jiang on 2023/11/23
 */
interface APIInterface {
    @GET("v2/top-headlines")
    fun getTopHeadlines(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String
    ): Deferred<Response<NewsJson>>
}