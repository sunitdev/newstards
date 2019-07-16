package com.news.aggregator.newstard.ui.pagination.reddit

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.news.aggregator.newstard.repositories.reddit.RedditPost
import com.news.aggregator.newstard.repositories.reddit.RedditRepository
import javax.inject.Inject

class RedditPostDataSourceFactory
    @Inject constructor(private val _redditRepository: RedditRepository):
        DataSource.Factory<String, RedditPost>() {

    private val _dataSourceLiveData = MutableLiveData<RedditPostDataSource>()

    override fun create(): DataSource<String, RedditPost> {
        val dataSource = RedditPostDataSource(_redditRepository)
        _dataSourceLiveData.postValue(dataSource)
        return dataSource
    }

    fun getDataSourceLiveData() = _dataSourceLiveData

}