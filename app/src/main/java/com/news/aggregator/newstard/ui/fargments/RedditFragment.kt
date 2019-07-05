package com.news.aggregator.newstard.ui.fargments

import com.news.aggregator.newstard.R
import com.news.aggregator.newstard.databinding.FragmentRedditLayoutBinding
import com.news.aggregator.newstard.ui.viewmodels.RedditFragmentViewModel

class RedditFragment: BaseFragment<RedditFragmentViewModel, FragmentRedditLayoutBinding>() {

    override fun getLayoutResource(): Int {
        return R.layout.fragment_reddit_layout
    }
}