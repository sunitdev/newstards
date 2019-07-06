package com.news.aggregator.newstard.repositories.services

import com.news.aggregator.newstard.R
import io.reactivex.Observable
import io.reactivex.rxkotlin.toObservable

interface ServiceRepository {

    fun getServices(): Observable<NewsService>
}

class ServiceRepositoryImp: ServiceRepository {

    // Constant services list
    private val _services = listOf(
        NewsService(1, "Reddit", R.drawable.reddit_icon)
    )

    /**
     * Return services as observable
     */
    override fun getServices(): Observable<NewsService>{
        return _services.toObservable()
    }
}