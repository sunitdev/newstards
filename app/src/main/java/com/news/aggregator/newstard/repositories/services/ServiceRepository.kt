package com.news.aggregator.newstard.repositories.services

import com.news.aggregator.newstard.R

interface ServiceRepository {

    fun getServices(): List<NewsService>
}

class ServiceRepositoryImp: ServiceRepository {

    // Constant services list
    private val _services = listOf(
        NewsService(1, "Reddit", R.drawable.reddit_icon)
    )

    /**
     * Return services as observable
     */
    override fun getServices() = _services

}