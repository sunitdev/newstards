package com.news.aggregator.newstard.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.news.aggregator.newstard.network.NetworkState
import com.news.aggregator.newstard.repositories.medium.MediumPost
import com.news.aggregator.newstard.ui.pagination.medium.MediumPostDataSource
import com.news.aggregator.newstard.ui.pagination.medium.MediumPostDataSourceFactory
import javax.inject.Inject

class MediumFragmentViewModel
    @Inject constructor(private val _mediumPostDataSourceFactory: MediumPostDataSourceFactory): ViewModel() {

    private var _pagedPostLiveData: LiveData<PagedList<MediumPost>>

    private var _initialLoadingStateLiveData: LiveData<NetworkState>
    private var _paginationLoadingStateLiveData: LiveData<NetworkState>

    init{
        val pageListConfig = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(MediumPostDataSource.PAGE_SIZE)
            .build()

        _pagedPostLiveData = LivePagedListBuilder(_mediumPostDataSourceFactory, pageListConfig).build()

        _initialLoadingStateLiveData = Transformations.switchMap(_mediumPostDataSourceFactory.getDataSourceLiveData(), MediumPostDataSource::getInitialLoadStateLiveData)
        _paginationLoadingStateLiveData = Transformations.switchMap(_mediumPostDataSourceFactory.getDataSourceLiveData(), MediumPostDataSource::getPaginationLoadStateLiveData)
    }

    fun getPagedPostData() = _pagedPostLiveData

    fun getPaginationLoadingSate() = _paginationLoadingStateLiveData

    fun getInitialLoadingState() = _initialLoadingStateLiveData

    fun reloadData() {
        _mediumPostDataSourceFactory.getDataSourceLiveData().value?.invalidate()
    }

    override fun onCleared() {
        super.onCleared()

        _mediumPostDataSourceFactory.getDataSourceLiveData().value?.clear()
    }

}
