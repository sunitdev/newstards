package com.news.aggregator.newstard.ui.views.iconbarview

import com.news.aggregator.newstard.repositories.services.NewsService

interface ServiceIconClickListener {
    fun onServiceIconClicked(service: NewsService)
}