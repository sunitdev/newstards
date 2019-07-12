package com.news.aggregator.newstard.ui.adapters.reddit

import androidx.recyclerview.widget.DiffUtil
import com.news.aggregator.newstard.repositories.reddit.RedditPost

class RedditRecyclerDiff: DiffUtil.ItemCallback<RedditPost>() {

    override fun areContentsTheSame(oldItem: RedditPost, newItem: RedditPost) = (oldItem.id == newItem.id)

    override fun areItemsTheSame(oldItem: RedditPost, newItem: RedditPost) = (oldItem == newItem)

}