package com.news.aggregator.newstard.ui.viewmodels.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.news.aggregator.newstard.network.NetworkState
import com.news.aggregator.newstard.ui.pagination.base.ServiceDataSource
import com.news.aggregator.newstard.ui.pagination.base.ServiceDataSourceFactory

abstract class ServiceViewModel<Key, PostModelClass, DataSourceClass, DataSourceFactoryClass>
    constructor(private val _dataSourceFactory: DataSourceFactoryClass) : ViewModel()
    where DataSourceClass : ServiceDataSource,
          DataSourceClass : DataSource<Key, PostModelClass>,
          DataSourceFactoryClass : ServiceDataSourceFactory<Key, PostModelClass, DataSourceClass> {

    private var _pagedPostLiveData: LiveData<PagedList<PostModelClass>>

    private var _initialLoadingStateLiveData: LiveData<NetworkState>
    private var _paginationLoadingStateLiveData: LiveData<NetworkState>

    init {
        val pageListConfig = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(getPageSize())
            .build()
        _pagedPostLiveData = LivePagedListBuilder(_dataSourceFactory, pageListConfig).build()
        _initialLoadingStateLiveData = Transformations.switchMap(_dataSourceFactory.getDataSourceLiveData()) {
            it.getInitialLoadStateLiveData()
        }
        _paginationLoadingStateLiveData = Transformations.switchMap(_dataSourceFactory.getDataSourceLiveData()) {
            it.getPaginationLoadStateLiveData()
        }
    }

    abstract fun getPageSize(): Int

    fun getPagedPostData() = _pagedPostLiveData

    fun getPaginationLoadingSate() = _paginationLoadingStateLiveData

    fun getInitialLoadingState() = _initialLoadingStateLiveData

    fun reloadData() {
        _dataSourceFactory.getDataSourceLiveData().value?.invalidate()
    }

    override fun onCleared() {
        super.onCleared()

        _dataSourceFactory.getDataSourceLiveData().value?.clear()
    }


}
