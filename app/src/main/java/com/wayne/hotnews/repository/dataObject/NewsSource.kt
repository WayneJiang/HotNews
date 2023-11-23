package com.wayne.hotnews.repository.dataObject

import io.realm.kotlin.types.RealmObject
import javax.inject.Inject

/*
 * Copyright (c) 2023 GoMore Inc. All rights reserved.
 *
 * Created by Wayne Jiang on 2023/11/23
 */
open class NewsSource @Inject constructor() : RealmObject {
    var id: String = ""

    var name: String = ""
}