package com.news.aggregator.newstard.ui.adapters.hackernews

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.news.aggregator.newstard.databinding.FragmentHackerNewsListErrorLayoutBinding
import com.news.aggregator.newstard.databinding.FragmentHackerNewsListItemLayoutBinding
import com.news.aggregator.newstard.databinding.FragmentHackerNewsListLoadingLayoutBinding
import com.news.aggregator.newstard.repositories.hackernews.HackerNewsPost
import com.news.aggregator.newstard.ui.adapters.base.ServiceRecyclerAdapter
import com.news.aggregator.newstard.ui.adapters.hackernews.viewholders.HackerNewsErrorViewHolder
import com.news.aggregator.newstard.ui.adapters.hackernews.viewholders.HackerNewsItemViewHolder
import com.news.aggregator.newstard.ui.adapters.hackernews.viewholders.HackerNewsLoadingViewHolder

class HackerNewsRecyclerAdapter(private val context: Context):
    ServiceRecyclerAdapter<HackerNewsPost>(HackerNewsRecyclerDiff()) {

    override fun getItemViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val layoutBinding = FragmentHackerNewsListItemLayoutBinding.inflate(LayoutInflater.from(context), parent, false)
        return HackerNewsItemViewHolder(layoutBinding, context)
    }

    override fun getLoadingViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val layoutBinding = FragmentHackerNewsListLoadingLayoutBinding.inflate(LayoutInflater.from(context), parent, false)
        return HackerNewsLoadingViewHolder(layoutBinding)
    }

    override fun getErrorViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val layoutBinding = FragmentHackerNewsListErrorLayoutBinding.inflate(LayoutInflater.from(context), parent, false)
        return HackerNewsErrorViewHolder(layoutBinding)
    }

    override fun bindItemViewHolder(holder: RecyclerView.ViewHolder, item: HackerNewsPost) {
        (holder as HackerNewsItemViewHolder).bindPost(item)
    }

    override fun bindLoadingViewHolder(holder: RecyclerView.ViewHolder) {}
    override fun bindErrorViewHolder(holder: RecyclerView.ViewHolder) {}
}