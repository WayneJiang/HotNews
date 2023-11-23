package com.wayne.hotnews.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView
import com.wayne.hotnews.R
import com.wayne.hotnews.databinding.LayoutNewsItemBinding
import com.wayne.hotnews.repository.dataObject.NewsData

/*
 * Copyright (c) 2023 GoMore Inc. All rights reserved.
 *
 * Created by Wayne Jiang on 2023/04/21
 */
class NewsAdapter : ListAdapter<NewsData, NewsAdapter.NewsViewHolder>(ExerciseDiff()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        NewsViewHolder(
            LayoutNewsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onViewRecycled(holder: NewsViewHolder) {
        super.onViewRecycled(holder)

        Glide.with(holder.itemView)
            .clear(holder.itemView.findViewById<ShapeableImageView>(R.id.view_img))
    }

    inner class NewsViewHolder(private val layoutNewsItemBinding: LayoutNewsItemBinding) :
        RecyclerView.ViewHolder(layoutNewsItemBinding.root) {

        fun bind(newsData: NewsData) = with(layoutNewsItemBinding) {
            Glide.with(root)
                .load(newsData.urlToImage)
                .centerCrop()
                .error(android.R.drawable.ic_menu_help)
                .into(viewImg)

            viewText.text = newsData.title
        }
    }

    class ExerciseDiff : DiffUtil.ItemCallback<NewsData>() {
        override fun areItemsTheSame(oldItem: NewsData, newItem: NewsData) =
            (oldItem == newItem)

        override fun areContentsTheSame(oldItem: NewsData, newItem: NewsData) =
            (oldItem.source?.id == newItem.source?.id)
    }
}