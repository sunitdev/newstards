package com.news.aggregator.newstard.ui.adapters

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.news.aggregator.newstard.ui.fargments.DevToFragment
import com.news.aggregator.newstard.ui.fargments.HackerNewsFragment
import com.news.aggregator.newstard.ui.fargments.MediumFragment
import com.news.aggregator.newstard.ui.fargments.RedditFragment

class MainActivityFragmentAdapter(fragmentManager: FragmentManager): FragmentPagerAdapter(fragmentManager) {

    override fun getCount() = 4

    override fun getItem(position: Int) = when(position){
        0 -> RedditFragment()
        1 -> MediumFragment()
        2 -> HackerNewsFragment()
        3 -> DevToFragment()
        else -> throw IllegalArgumentException("Invalid Position in MainActivityFragmentAdapter")
    }
}