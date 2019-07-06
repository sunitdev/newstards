package com.news.aggregator.newstard.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.news.aggregator.newstard.databinding.FragmentRedditListItemLayoutBinding
import com.news.aggregator.newstard.repositories.reddit.RedditPost
import javax.inject.Inject

class RedditRecyclerAdapter
    @Inject constructor(private val _context: Context):
    PagedListAdapter<RedditPost, RedditRecyclerAdapter.PostViewHolder>(_diffCallback) {

    companion object {
        private val _diffCallback = object:DiffUtil.ItemCallback<RedditPost>() {

            override fun areContentsTheSame(oldItem: RedditPost, newItem: RedditPost): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areItemsTheSame(oldItem: RedditPost, newItem: RedditPost): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val layoutBinding = FragmentRedditListItemLayoutBinding.inflate(LayoutInflater.from(_context), parent,false)

        return PostViewHolder(layoutBinding)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = getItem(position)
        // TODO: Handle null value and show loading layout
        post?.let { holder.bindPost(it) }
    }


    inner class PostViewHolder(private val itemLayoutBinding: FragmentRedditListItemLayoutBinding):
        RecyclerView.ViewHolder(itemLayoutBinding.root) {

        fun bindPost(post: RedditPost){
            itemLayoutBinding.post = post
        }
    }
}