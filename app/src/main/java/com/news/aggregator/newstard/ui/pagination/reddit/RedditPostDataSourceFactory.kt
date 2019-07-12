package com.news.aggregator.newstard.ui.pagination.reddit

import androidx.paging.DataSource
import com.news.aggregator.newstard.repositories.reddit.RedditPost
import com.news.aggregator.newstard.repositories.reddit.RedditRepository
import javax.inject.Inject

class RedditPostDataSourceFactory
    @Inject constructor(val redditPostDataSource: RedditPostDataSource):
        DataSource.Factory<String, RedditPost>() {

    override fun create(): DataSource<String, RedditPost> {
        // Not using di because new instance is required on each call
        return redditPostDataSource
    }

}