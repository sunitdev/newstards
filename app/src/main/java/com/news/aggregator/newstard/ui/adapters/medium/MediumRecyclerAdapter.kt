package com.news.aggregator.newstard.ui.adapters.medium

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.news.aggregator.newstard.databinding.FragmentMediumListErrorLayoutBinding
import com.news.aggregator.newstard.databinding.FragmentMediumListItemLayoutBinding
import com.news.aggregator.newstard.databinding.FragmentMediumListLoadingLayoutBinding
import com.news.aggregator.newstard.repositories.medium.MediumPost
import com.news.aggregator.newstard.ui.adapters.base.ServiceRecyclerAdapter
import com.news.aggregator.newstard.ui.adapters.medium.viewholders.MediumErrorViewHolder
import com.news.aggregator.newstard.ui.adapters.medium.viewholders.MediumLoadingViewHolder
import com.news.aggregator.newstard.ui.adapters.medium.viewholders.MediumPostItemViewHolder

class MediumRecyclerAdapter constructor(context: Context):
    ServiceRecyclerAdapter<MediumPost>(context, MediumRecyclerDiff()){

    override fun getItemViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val layoutBinding = FragmentMediumListItemLayoutBinding.inflate(LayoutInflater.from(context), parent, false)
        return MediumPostItemViewHolder(layoutBinding, context)
    }

    override fun getErrorViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val layoutBinding = FragmentMediumListErrorLayoutBinding.inflate(LayoutInflater.from(context), parent, false)
        return MediumErrorViewHolder(layoutBinding)
    }

    override fun getLoadingViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val layoutBinding = FragmentMediumListLoadingLayoutBinding.inflate(LayoutInflater.from(context), parent, false)
        return MediumLoadingViewHolder(layoutBinding)
    }

    override fun bindItemViewHolder(holder: RecyclerView.ViewHolder, item: MediumPost) {
        (holder as MediumPostItemViewHolder).bindPost(item)
    }

    override fun bindErrorViewHolder(holder: RecyclerView.ViewHolder) {}
    override fun bindLoadingViewHolder(holder: RecyclerView.ViewHolder) {}
}
