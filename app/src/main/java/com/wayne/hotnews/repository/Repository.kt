package com.wayne.hotnews.repository

import com.wayne.hotnews.repository.dataObject.NewsData
import io.realm.kotlin.Realm
import io.realm.kotlin.UpdatePolicy
import javax.inject.Inject
import javax.inject.Singleton

/*
 * Copyright (c) 2023 GoMore Inc. All rights reserved.
 *
 * Created by Wayne Jiang on 2023/11/24
 */
@Singleton
class Repository @Inject constructor(
    private val apiService: APIService,
    private val realm: Realm
) {
    suspend fun retrieveTopHeadlineFromAPI() {
        val newsDataList = apiService.getTopHeadlineAsync()

        realm.write {
            newsDataList.forEach {
                copyToRealm(it, UpdatePolicy.ALL)
            }
        }
    }

    fun retrieveNewsFromDB() =
        realm.query(NewsData::class).find()

    fun closeDB() = realm.close()
}