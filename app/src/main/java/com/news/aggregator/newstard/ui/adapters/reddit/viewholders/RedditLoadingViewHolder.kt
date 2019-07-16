package com.news.aggregator.newstard.ui.adapters.reddit.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.news.aggregator.newstard.databinding.FragmentRedditListLoadingLayoutBinding

class RedditLoadingViewHolder(private val _layoutBinding: FragmentRedditListLoadingLayoutBinding):
    RecyclerView.ViewHolder(_layoutBinding.root) {

    fun startAnimation(){
        _layoutBinding.redditLoadingShimmerLayout.startShimmerAnimation()
    }

    fun stopAnimation() {
        _layoutBinding.redditLoadingShimmerLayout.stopShimmerAnimation()
    }
}
