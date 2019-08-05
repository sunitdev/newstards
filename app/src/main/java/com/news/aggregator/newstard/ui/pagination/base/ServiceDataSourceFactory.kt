package com.news.aggregator.newstard.ui.pagination.base

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource

abstract class ServiceDataSourceFactory<Key, PostModelClass, DataSourceClass: DataSource<Key, PostModelClass>>:
    DataSource.Factory<Key, PostModelClass>() {

    private val _dataSourceLiveData = MutableLiveData<DataSourceClass>()

    abstract fun getDataSource(): DataSourceClass

    fun getDataSourceLiveData() = _dataSourceLiveData

    override fun create(): DataSourceClass {
        val dataSource = getDataSource()
        _dataSourceLiveData.postValue(dataSource)
        return dataSource
    }

}