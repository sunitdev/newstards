package com.news.aggregator.newstard.repositories.services

import com.news.aggregator.newstard.R

interface ServiceRepository {

    fun getServices(): List<NewsService>
}

class ServiceRepositoryImp: ServiceRepository {

    // Constant services list
    private val _services = listOf(
        NewsService(1, "Reddit", R.drawable.reddit_icon),
        NewsService(2, "Medium", R.drawable.medium_icon),
        NewsService(3, "HackerNews", R.drawable.hacker_news_icon),
        NewsService(4, "Dev.to", R.drawable.dev_to_logo)
    )

    /**
     * Return services as observable
     */
    override fun getServices() = _services

}