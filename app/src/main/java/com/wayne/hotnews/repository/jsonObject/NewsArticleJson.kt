package com.wayne.hotnews.repository.jsonObject

import com.squareup.moshi.Json

/*
 * Copyright (c) 2023 GoMore Inc. All rights reserved.
 *
 * Created by Wayne Jiang on 2023/11/23
 */
data class NewsArticleJson(
    @Json(name = "source")
    val sourceJson: NewsSourceJson = NewsSourceJson(),

    @Json(name = "author")
    val author: String? = null,

    @Json(name = "title")
    val title: String? = null,

    @Json(name = "description")
    val description: String? = null,

    @Json(name = "url")
    val url: String? = null,

    @Json(name = "urlToImage")
    val urlToImage: String? = null,

    @Json(name = "publishedAt")
    val publishedAt: String? = null,

    @Json(name = "content")
    val content: String? = null
)
