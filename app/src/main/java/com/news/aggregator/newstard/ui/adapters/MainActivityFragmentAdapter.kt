package com.news.aggregator.newstard.ui.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.news.aggregator.newstard.ui.fargments.RedditFragment
import java.lang.IllegalArgumentException

class MainActivityFragmentAdapter(fragmentManager: FragmentManager): FragmentPagerAdapter(fragmentManager) {

    override fun getCount(): Int = 1

    override fun getItem(position: Int): Fragment = when(position){
        0 -> RedditFragment()
        else -> throw IllegalArgumentException("Invalid Position in MainActivityFragmentAdapter")
    }
}