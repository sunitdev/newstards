package com.news.aggregator.newstard.ui.adapters.dev_to

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.news.aggregator.newstard.databinding.FragmentDevToListErrorLayoutBinding
import com.news.aggregator.newstard.databinding.FragmentDevToListItemLayoutBinding
import com.news.aggregator.newstard.databinding.FragmentDevToListLoadingLayoutBinding
import com.news.aggregator.newstard.repositories.dev_to.DevToPost
import com.news.aggregator.newstard.ui.adapters.base.ServiceRecyclerAdapter
import com.news.aggregator.newstard.ui.adapters.dev_to.viewholders.DevToErrorViewHolder
import com.news.aggregator.newstard.ui.adapters.dev_to.viewholders.DevToLoadingViewHolder
import com.news.aggregator.newstard.ui.adapters.dev_to.viewholders.DevToPostViewHolder

class DevToRecyclerAdapter(private val context: Context):
    ServiceRecyclerAdapter<DevToPost>(DevToRecyclerDiff()){

    override fun getItemViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val layoutBinding = FragmentDevToListItemLayoutBinding.inflate(LayoutInflater.from(context), parent, false)
        return DevToPostViewHolder(layoutBinding, context)
    }

    override fun getLoadingViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val layoutBinding = FragmentDevToListLoadingLayoutBinding.inflate(LayoutInflater.from(context), parent, false)
        return DevToLoadingViewHolder(layoutBinding)
    }

    override fun getErrorViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val layoutBinding = FragmentDevToListErrorLayoutBinding.inflate(LayoutInflater.from(context), parent, false)
        return DevToErrorViewHolder(layoutBinding)
    }

    override fun bindItemViewHolder(holder: RecyclerView.ViewHolder, item: DevToPost) {
        (holder as DevToPostViewHolder).bindPost(item)
    }

    override fun bindLoadingViewHolder(holder: RecyclerView.ViewHolder) {}
    override fun bindErrorViewHolder(holder: RecyclerView.ViewHolder) {}

}