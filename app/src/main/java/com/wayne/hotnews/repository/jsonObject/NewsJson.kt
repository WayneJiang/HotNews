package com.wayne.hotnews.repository.jsonObject

import com.squareup.moshi.Json

/*
 * Copyright (c) 2023 GoMore Inc. All rights reserved.
 *
 * Created by Wayne Jiang on 2023/11/23
 */
data class NewsJson(
    @Json(name = "status")
    val status: String? = null,

    @Json(name = "totalResults")
    val totalResults: Int = -9999,

    @Json(name = "articles")
    val articles: List<NewsArticleJson> = emptyList()
)
