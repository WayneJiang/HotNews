package com.wayne.hotnews.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wayne.hotnews.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/*
 * Copyright (c) 2023 GoMore Inc. All rights reserved.
 *
 * Created by Wayne Jiang on 2023/11/23
 */
@HiltViewModel
class ActivityViewModel
@Inject constructor(
    private val repository: Repository
) : ViewModel() {

    val allNewsData = repository.retrieveNewsFromDB().asFlow()

    fun retrieveNews() {
        viewModelScope.launch {
            repository.retrieveTopHeadlineFromAPI()
        }
    }

    override fun onCleared() {
        super.onCleared()

        repository.closeDB()
    }
}