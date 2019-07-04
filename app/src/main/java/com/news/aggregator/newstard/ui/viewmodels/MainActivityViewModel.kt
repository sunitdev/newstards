package com.news.aggregator.newstard.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.news.aggregator.newstard.R
import com.news.aggregator.newstard.models.NewsService

class MainActivityViewModel: ViewModel() {

    // Constant services list
    private val _services = arrayOf(
        NewsService(1, "Reddit", R.drawable.reddit_icon)
    )

    /**
     * Return currently active services
     */
    fun getServices(): Array<NewsService> {
        return _services
    }
}
