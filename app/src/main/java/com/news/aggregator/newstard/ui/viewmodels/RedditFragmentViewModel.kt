package com.news.aggregator.newstard.ui.viewmodels

import com.news.aggregator.newstard.repositories.reddit.RedditPost
import com.news.aggregator.newstard.ui.pagination.reddit.RedditPostDataSource
import com.news.aggregator.newstard.ui.pagination.reddit.RedditPostDataSourceFactory
import com.news.aggregator.newstard.ui.viewmodels.base.ServiceViewModel
import javax.inject.Inject

class RedditFragmentViewModel
    @Inject constructor(redditPostDataSourceFactory: RedditPostDataSourceFactory):
    ServiceViewModel<String, RedditPost, RedditPostDataSource, RedditPostDataSourceFactory>(redditPostDataSourceFactory){

    override fun getPageSize() = RedditPostDataSource.PAGE_SIZE

}