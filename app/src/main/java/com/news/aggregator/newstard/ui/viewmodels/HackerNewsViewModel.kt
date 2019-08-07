package com.news.aggregator.newstard.ui.viewmodels

import com.news.aggregator.newstard.repositories.hackernews.HackerNewsPost
import com.news.aggregator.newstard.ui.pagination.hackernews.HackerNewsDataSource
import com.news.aggregator.newstard.ui.pagination.hackernews.HackerNewsDataSourceFactory
import com.news.aggregator.newstard.ui.viewmodels.base.ServiceViewModel
import javax.inject.Inject

class HackerNewsViewModel
    @Inject constructor(hackerNewsDataSourceFactory: HackerNewsDataSourceFactory):
    ServiceViewModel<Int, HackerNewsPost, HackerNewsDataSource, HackerNewsDataSourceFactory>(hackerNewsDataSourceFactory){

    override fun getPageSize() = HackerNewsDataSource.PAGE_SIZE
}