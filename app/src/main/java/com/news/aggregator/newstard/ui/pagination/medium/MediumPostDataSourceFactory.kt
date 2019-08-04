package com.news.aggregator.newstard.ui.pagination.medium

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.news.aggregator.newstard.repositories.medium.MediumPost
import com.news.aggregator.newstard.repositories.medium.MediumRepository
import javax.inject.Inject

class MediumPostDataSourceFactory
    @Inject constructor(private val _mediumRepository: MediumRepository): DataSource.Factory<String, MediumPost>(){

    private val _dataSourceLiveData = MutableLiveData<MediumPostDataSource>()

    fun getDataSourceLiveData() = _dataSourceLiveData

    override fun create(): DataSource<String, MediumPost> {
        val dataSource = MediumPostDataSource(_mediumRepository)
        _dataSourceLiveData.postValue(dataSource)
        return dataSource
    }

}