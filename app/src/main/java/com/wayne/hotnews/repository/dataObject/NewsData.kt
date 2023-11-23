package com.wayne.hotnews.repository.dataObject

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import javax.inject.Inject

/*
 * Copyright (c) 2023 GoMore Inc. All rights reserved.
 *
 * Created by Wayne Jiang on 2023/11/23
 */
open class NewsData @Inject constructor() : RealmObject {

    @Inject
    @JvmField
    var source: NewsSource? = null

    var author: String = ""

    var title: String = ""

    var description: String = ""

    var url: String = ""

    var urlToImage: String = ""

    @PrimaryKey
    var publishedAt: String = ""

    var content: String = ""
}