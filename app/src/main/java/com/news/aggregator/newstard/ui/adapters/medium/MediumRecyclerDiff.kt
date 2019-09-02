package com.news.aggregator.newstard.ui.adapters.medium

import androidx.recyclerview.widget.DiffUtil
import com.news.aggregator.newstard.repositories.medium.MediumPost

class MediumRecyclerDiff: DiffUtil.ItemCallback<MediumPost>() {

    override fun areContentsTheSame(oldItem: MediumPost, newItem: MediumPost) = oldItem.link == newItem.link

    override fun areItemsTheSame(oldItem: MediumPost, newItem: MediumPost) = oldItem.link == newItem.link
}
