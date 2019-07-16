package com.news.aggregator.newstard.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
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

    private var _initialLoadingStateLiveData: LiveData<NetworkState>
    private var _paginationLoadingStateLiveData: LiveData<NetworkState>

    init {
        val pageListConfig = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(RedditPostDataSource.PAGE_SIZE)
            .build()

        _pagedPostLiveData = LivePagedListBuilder(redditPostDataSourceFactory, pageListConfig).build()

        _initialLoadingStateLiveData = Transformations.switchMap(redditPostDataSourceFactory.getDataSourceLiveData(), RedditPostDataSource::getInitialLoadStateLiveData)
        _paginationLoadingStateLiveData = Transformations.switchMap(redditPostDataSourceFactory.getDataSourceLiveData(), RedditPostDataSource::getPaginationLoadStateLiveData)
    }

    override fun onCleared() {
        super.onCleared()

        redditPostDataSourceFactory.getDataSourceLiveData().value?.clear()
    }

    fun getPagedPostData() = _pagedPostLiveData

    fun getInitialLoadingState() = _initialLoadingStateLiveData

    fun getPaginationLoadingSate() = _paginationLoadingStateLiveData

    fun reloadData() {
        redditPostDataSourceFactory.getDataSourceLiveData().value?.invalidate()
    }

}
