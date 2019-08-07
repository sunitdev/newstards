package com.news.aggregator.newstard.ui.adapters.hackernews

import androidx.recyclerview.widget.DiffUtil
import com.news.aggregator.newstard.repositories.hackernews.HackerNewsPost

class HackerNewsRecyclerDiff: DiffUtil.ItemCallback<HackerNewsPost>() {

    override fun areContentsTheSame(oldItem: HackerNewsPost, newItem: HackerNewsPost) = oldItem.link == newItem.link

    override fun areItemsTheSame(oldItem: HackerNewsPost, newItem: HackerNewsPost) = oldItem.id == newItem.id
}