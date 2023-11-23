package com.wayne.hotnews.repository

import com.wayne.hotnews.repository.dataObject.NewsData
import com.wayne.hotnews.repository.dataObject.NewsSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import javax.inject.Singleton

/*
 * Copyright (c) 2023 GoMore Inc. All rights reserved.
 *
 * Created by Wayne Jiang on 2023/11/23
 */
@Module
@InstallIn(SingletonComponent::class)
object RealmModule {
    @Singleton
    @Provides
    fun realm() =
        Realm.open(
            RealmConfiguration.Builder(setOf(NewsData::class, NewsSource::class))
                .name("REALM_DB")
                .schemaVersion(1)
                .build()
        )
}