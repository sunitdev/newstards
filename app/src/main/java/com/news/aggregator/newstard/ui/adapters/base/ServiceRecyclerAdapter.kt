package com.news.aggregator.newstard.ui.adapters.base

import android.content.Context
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.news.aggregator.newstard.network.NetworkState

abstract class ServiceRecyclerAdapter<PostModelClass>
    (protected val context: Context, diffItemCallback: DiffUtil.ItemCallback<PostModelClass>):
    PagedListAdapter<PostModelClass, RecyclerView.ViewHolder>(diffItemCallback) {

    private enum class ViewType(val value: Int){
        ITEM(1),
        ERROR(2),
        LOADING(3);

        companion object {
            private val map = values().associateBy(ViewType::value)
            fun fromInt(type: Int) = map[type]
        }
    }

    private var _currentPaginationState: NetworkState? = null
    private var _isExtraRowAdded = false

    abstract fun getItemViewHolder(parent: ViewGroup): RecyclerView.ViewHolder
    abstract fun getErrorViewHolder(parent: ViewGroup): RecyclerView.ViewHolder
    abstract fun getLoadingViewHolder(parent: ViewGroup): RecyclerView.ViewHolder

    abstract fun bindItemViewHolder(holder: RecyclerView.ViewHolder, item: PostModelClass)
    abstract fun bindErrorViewHolder(holder: RecyclerView.ViewHolder)
    abstract fun bindLoadingViewHolder(holder: RecyclerView.ViewHolder)

    fun setPaginationNetworkState(networkState: NetworkState) {
        _currentPaginationState = networkState

        // Check if to add loading/error item at last or remove item
        if (_currentPaginationState == NetworkState.SUCCESS && _isExtraRowAdded) {

            _isExtraRowAdded = false
            notifyItemRemoved(itemCount - 1)

        } else {
            if (_isExtraRowAdded) {
                notifyItemChanged(itemCount - 1)
            } else {
                _isExtraRowAdded = true
                notifyItemInserted(itemCount)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(ViewType.fromInt(viewType)){
            ViewType.ERROR -> getErrorViewHolder(parent)
            ViewType.LOADING -> getLoadingViewHolder(parent)
            else -> getItemViewHolder(parent)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (ViewType.fromInt(getItemViewType(position))){
            ViewType.ERROR -> bindErrorViewHolder(holder)
            ViewType.LOADING -> bindLoadingViewHolder(holder)
            else -> bindItemViewHolder(holder, (getItem(position) as PostModelClass))
        }
    }

    override fun getItemViewType(position: Int): Int {
        // Return view type based on position and network state
        if (_isExtraRowAdded && position == itemCount - 1) {
            if (_currentPaginationState == NetworkState.LOADING) {
                return ViewType.LOADING.value
            } else {
                return ViewType.ERROR.value
            }
        } else {
            return ViewType.ITEM.value
        }
    }

}