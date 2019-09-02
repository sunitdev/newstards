package com.news.aggregator.newstard.ui.pagination.reddit

import com.news.aggregator.newstard.repositories.reddit.RedditPost
import com.news.aggregator.newstard.repositories.reddit.RedditRepository
import com.news.aggregator.newstard.ui.pagination.base.ServiceDataSourceFactory
import javax.inject.Inject

class RedditPostDataSourceFactory
    @Inject constructor(private val _redditRepository: RedditRepository):
        ServiceDataSourceFactory<String, RedditPost, RedditPostDataSource>() {

    override fun getDataSource() = RedditPostDataSource(_redditRepository)

}
