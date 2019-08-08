package com.news.aggregator.newstard.ui.adapters.dev_to

import androidx.recyclerview.widget.DiffUtil
import com.news.aggregator.newstard.repositories.dev_to.DevToPost

class DevToRecyclerDiff: DiffUtil.ItemCallback<DevToPost>() {

    override fun areContentsTheSame(oldItem: DevToPost, newItem: DevToPost) = oldItem.link == newItem.link

    override fun areItemsTheSame(oldItem: DevToPost, newItem: DevToPost) = oldItem.link == newItem.link
}