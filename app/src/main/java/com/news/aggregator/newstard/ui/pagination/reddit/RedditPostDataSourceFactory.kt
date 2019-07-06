package com.news.aggregator.newstard.ui.pagination.reddit

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.ItemKeyedDataSource
import com.news.aggregator.newstard.repositories.reddit.RedditPost
import com.news.aggregator.newstard.repositories.reddit.RedditRepository
import javax.inject.Inject

class RedditPostDataSourceFactory
    @Inject constructor(private val _redditRespository: RedditRepository):
        DataSource.Factory<String, RedditPost>() {

    override fun create(): DataSource<String, RedditPost> {
        // Not using di because new instance is required on each call
        return RedditPostDataSource(_redditRespository)
    }

}