package com.news.aggregator.newstard.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.news.aggregator.newstard.network.NetworkState
import com.news.aggregator.newstard.repositories.reddit.RedditPost
import com.news.aggregator.newstard.ui.pagination.reddit.RedditPostDataSource
import com.news.aggregator.newstard.ui.pagination.reddit.RedditPostDataSourceFactory
import javax.inject.Inject

class RedditFragmentViewModel
    @Inject constructor(private val redditPostDataSourceFactory: RedditPostDataSourceFactory) :
        ViewModel() {

    private var _pagedPostLiveData: LiveData<PagedList<RedditPost>>

    init {
        val pageListConfig = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(RedditPostDataSource.PAGE_SIZE)
            .build()

        _pagedPostLiveData = LivePagedListBuilder(redditPostDataSourceFactory, pageListConfig).build()
    }

    override fun onCleared() {
        super.onCleared()
        redditPostDataSourceFactory.redditPostDataSource.clear()
    }

    fun getPagedPostData() = _pagedPostLiveData

    fun getInitalLoadingState(): LiveData<NetworkState> {
        return redditPostDataSourceFactory.redditPostDataSource.getInitialLoadStateLiveData()
    }

}
