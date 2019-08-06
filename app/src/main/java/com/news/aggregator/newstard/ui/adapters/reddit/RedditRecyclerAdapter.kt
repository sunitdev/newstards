package com.news.aggregator.newstard.ui.adapters.reddit

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.news.aggregator.newstard.databinding.FragmentRedditListErrorLayoutBinding
import com.news.aggregator.newstard.databinding.FragmentRedditListItemLayoutBinding
import com.news.aggregator.newstard.databinding.FragmentRedditListLoadingLayoutBinding
import com.news.aggregator.newstard.repositories.reddit.RedditPost
import com.news.aggregator.newstard.ui.adapters.base.ServiceRecyclerAdapter
import com.news.aggregator.newstard.ui.adapters.reddit.viewholders.RedditErrorViewHolder
import com.news.aggregator.newstard.ui.adapters.reddit.viewholders.RedditLoadingViewHolder
import com.news.aggregator.newstard.ui.adapters.reddit.viewholders.RedditPostItemViewHolder


class RedditRecyclerAdapter(context: Context): ServiceRecyclerAdapter<RedditPost>(context, RedditRecyclerDiff()){

    override fun getItemViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val layoutBinding = FragmentRedditListItemLayoutBinding.inflate(LayoutInflater.from(context), parent, false)
        return RedditPostItemViewHolder(layoutBinding, context)
    }

    override fun getLoadingViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val layoutBinding = FragmentRedditListLoadingLayoutBinding.inflate(LayoutInflater.from(context), parent, false)
        return RedditLoadingViewHolder(layoutBinding)
    }

    override fun getErrorViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val layoutBinding = FragmentRedditListErrorLayoutBinding.inflate(LayoutInflater.from(context), parent, false)
        return RedditErrorViewHolder(layoutBinding)
    }

    override fun bindItemViewHolder(holder: RecyclerView.ViewHolder, item: RedditPost) {
        (holder as RedditPostItemViewHolder).bindPost(item)
    }

    override fun bindLoadingViewHolder(holder: RecyclerView.ViewHolder) {}
    override fun bindErrorViewHolder(holder: RecyclerView.ViewHolder) {}
}


