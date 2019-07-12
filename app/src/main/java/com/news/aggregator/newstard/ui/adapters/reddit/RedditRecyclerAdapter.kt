package com.news.aggregator.newstard.ui.adapters.reddit

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.news.aggregator.newstard.R
import com.news.aggregator.newstard.databinding.FragmentRedditListErrorLayoutBinding
import com.news.aggregator.newstard.databinding.FragmentRedditListItemLayoutBinding
import com.news.aggregator.newstard.databinding.FragmentRedditListLoadingLayoutBinding
import com.news.aggregator.newstard.network.NetworkState
import com.news.aggregator.newstard.repositories.reddit.RedditPost
import com.news.aggregator.newstard.ui.adapters.reddit.viewholders.RedditErrorViewHolder
import com.news.aggregator.newstard.ui.adapters.reddit.viewholders.RedditLoadingViewHolder
import com.news.aggregator.newstard.ui.adapters.reddit.viewholders.RedditPostItemViewHolder
import javax.inject.Inject


class RedditRecyclerAdapter @Inject constructor(private val _context: Context) :
    PagedListAdapter<RedditPost, RecyclerView.ViewHolder>(RedditRecyclerDiff()) {

    private var currentPaginationState: NetworkState? = null
    private var isExtraRowAdded = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return getViewHolderFromViewType(LayoutInflater.from(_context), parent, viewType)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (getItemViewType(position)) {
            R.layout.fragment_reddit_list_item_layout -> {
                val post = getItem(position)
                post?.let { (holder as RedditPostItemViewHolder).bindPost(it) }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        // Return view type based on position and network state
        if (isExtraRowAdded && position == itemCount - 1) {
            if (currentPaginationState == NetworkState.LOADING) {
                return R.layout.fragment_reddit_list_loading_layout
            } else {
                return R.layout.fragment_reddit_list_error_layout
            }
        } else {
            return R.layout.fragment_reddit_list_item_layout
        }
    }

    fun setPaginationNetworkState(networkState: NetworkState) {
        currentPaginationState = networkState

        // Check if to add loading/error item at last or remove item
        if (currentPaginationState == NetworkState.SUCCESS && isExtraRowAdded) {

            isExtraRowAdded = false
            notifyItemRemoved(itemCount - 1)

        } else {
            if (isExtraRowAdded) {
                notifyItemChanged(itemCount - 1)
            } else {
                isExtraRowAdded = true
                notifyItemInserted(itemCount)
            }
        }
    }

    private fun getViewHolderFromViewType(inflater: LayoutInflater, parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            R.layout.fragment_reddit_list_loading_layout -> {
                val layoutBinding = FragmentRedditListLoadingLayoutBinding.inflate(inflater, parent, false)
                RedditLoadingViewHolder(layoutBinding)
            }
            R.layout.fragment_reddit_list_error_layout -> {
                val layoutBinding = FragmentRedditListErrorLayoutBinding.inflate(inflater, parent, false)
                RedditErrorViewHolder(layoutBinding)
            }
            else -> {
                val layoutBinding = FragmentRedditListItemLayoutBinding.inflate(inflater, parent, false)
                RedditPostItemViewHolder(layoutBinding, _context)
            }
        }
    }

}

