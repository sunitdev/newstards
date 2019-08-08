package com.news.aggregator.newstard.ui.pagination.dev_to

import com.news.aggregator.newstard.repositories.dev_to.DevToPost
import com.news.aggregator.newstard.repositories.dev_to.DevToRepository
import com.news.aggregator.newstard.ui.pagination.base.ServiceDataSourceFactory
import javax.inject.Inject

class DevToDataSourceFactory
    @Inject constructor(private val _devToRepository: DevToRepository):
        ServiceDataSourceFactory<Int, DevToPost, DevToDataSource>(){

    override fun getDataSource() = DevToDataSource(_devToRepository)

}