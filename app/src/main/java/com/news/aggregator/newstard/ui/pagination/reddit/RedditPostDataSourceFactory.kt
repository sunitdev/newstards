package com.news.aggregator.newstard.ui.pagination.reddit

import androidx.paging.DataSource
import com.news.aggregator.newstard.repositories.reddit.RedditPost
import javax.inject.Inject

class RedditPostDataSourceFactory
    @Inject constructor(val redditPostDataSource: RedditPostDataSource):
        DataSource.Factory<String, RedditPost>() {
    
    override fun create() = redditPostDataSource

}