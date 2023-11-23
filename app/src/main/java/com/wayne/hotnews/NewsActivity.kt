package com.wayne.hotnews

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.wayne.hotnews.databinding.ActivityNewsBinding
import com.wayne.hotnews.repository.APIService
import com.wayne.hotnews.view.NewsAdapter
import com.wayne.hotnews.viewmodel.ActivityViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject


/*
 * Copyright (c) 2023 GoMore Inc. All rights reserved.
 *
 * Created by Wayne Jiang on 2023/11/23
 */
@AndroidEntryPoint
class NewsActivity : AppCompatActivity() {
    private lateinit var mActivityNewsBinding: ActivityNewsBinding

    private val mActivityViewModel by viewModels<ActivityViewModel>()

    private val mNewAdapter by lazy {
        NewsAdapter()
    }

    @Inject
    lateinit var mAPIService: APIService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mActivityNewsBinding =
            ActivityNewsBinding.inflate(layoutInflater).apply {
                viewRecycler.apply {
                    layoutManager = LinearLayoutManager(context)

                    adapter = mNewAdapter

                    addItemDecoration(
                        DividerItemDecoration(this@NewsActivity, DividerItemDecoration.VERTICAL)
                    )
                }
            }

        setContentView(mActivityNewsBinding.root)

        lifecycleScope.launch {
            mActivityViewModel.allNewsData.collect {
                mNewAdapter.submitList(it.list)
            }
        }
    }

    override fun onResume() {
        super.onResume()

        mActivityViewModel.retrieveNews()
    }
}