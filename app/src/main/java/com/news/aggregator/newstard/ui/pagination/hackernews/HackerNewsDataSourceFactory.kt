package com.news.aggregator.newstard.ui.pagination.hackernews

import com.news.aggregator.newstard.repositories.hackernews.HackerNewsPost
import com.news.aggregator.newstard.repositories.hackernews.HackerNewsRepository
import com.news.aggregator.newstard.ui.pagination.base.ServiceDataSourceFactory
import javax.inject.Inject

class HackerNewsDataSourceFactory
    @Inject constructor(private val _hackerNewsRepository: HackerNewsRepository):
        ServiceDataSourceFactory<Int, HackerNewsPost, HackerNewsDataSource>() {

    override fun getDataSource() = HackerNewsDataSource(_hackerNewsRepository)

}