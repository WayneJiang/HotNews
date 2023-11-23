package com.wayne.hotnews.repository.jsonObject

import com.squareup.moshi.Json

/*
 * Copyright (c) 2023 GoMore Inc. All rights reserved.
 *
 * Created by Wayne Jiang on 2023/11/23
 */
data class NewsSourceJson(
    @Json(name = "id")
    val id: String? = null,

    @Json(name = "name")
    val name: String? = null
)
