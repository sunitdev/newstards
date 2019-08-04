package com.news.aggregator.newstard.ui.adapters.medium

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.news.aggregator.newstard.R
import com.news.aggregator.newstard.databinding.FragmentMediumListErrorLayoutBinding
import com.news.aggregator.newstard.databinding.FragmentMediumListItemLayoutBinding
import com.news.aggregator.newstard.databinding.FragmentMediumListLoadingLayoutBinding
import com.news.aggregator.newstard.network.NetworkState
import com.news.aggregator.newstard.repositories.medium.MediumPost
import com.news.aggregator.newstard.ui.adapters.medium.viewholders.MediumErrorViewHolder
import com.news.aggregator.newstard.ui.adapters.medium.viewholders.MediumLoadingViewHolder
import com.news.aggregator.newstard.ui.adapters.medium.viewholders.MediumPostItemViewHolder
import javax.inject.Inject

class MediumRecyclerAdapter
    @Inject constructor(private val _context: Context):
    PagedListAdapter<MediumPost, RecyclerView.ViewHolder>(MediumRecyclerDiff()){

    private var currentPaginationState: NetworkState? = null
    private var isExtraRowAdded = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return getViewHolderFromViewType(LayoutInflater.from(_context), parent, viewType)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (getItemViewType(position)) {
            R.layout.fragment_medium_list_item_layout -> {
                val post = getItem(position)
                post?.let { (holder as MediumPostItemViewHolder).bindPost(it) }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        // Return view type based on position and network state
        if (isExtraRowAdded && position == itemCount - 1) {
            if (currentPaginationState == NetworkState.LOADING) {
                return R.layout.fragment_medium_list_loading_layout
            } else {
                return R.layout.fragment_medium_list_error_layout
            }
        } else {
            return R.layout.fragment_medium_list_item_layout
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
            R.layout.fragment_medium_list_loading_layout -> {
                val layoutBinding = FragmentMediumListLoadingLayoutBinding.inflate(inflater, parent, false)
                MediumLoadingViewHolder(layoutBinding)
            }
            R.layout.fragment_medium_list_error_layout -> {
                val layoutBinding = FragmentMediumListErrorLayoutBinding.inflate(inflater, parent, false)
                MediumErrorViewHolder(layoutBinding)
            }
            else -> {
                val layoutBinding = FragmentMediumListItemLayoutBinding.inflate(inflater, parent, false)
                MediumPostItemViewHolder(layoutBinding, _context)
            }
        }
    }
}