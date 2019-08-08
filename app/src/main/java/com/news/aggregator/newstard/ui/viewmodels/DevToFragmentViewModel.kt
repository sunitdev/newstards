package com.news.aggregator.newstard.ui.viewmodels

import com.news.aggregator.newstard.repositories.dev_to.DevToPost
import com.news.aggregator.newstard.ui.pagination.dev_to.DevToDataSource
import com.news.aggregator.newstard.ui.pagination.dev_to.DevToDataSourceFactory
import com.news.aggregator.newstard.ui.viewmodels.base.ServiceViewModel
import javax.inject.Inject

class DevToFragmentViewModel
    @Inject constructor(devToDataSourceFactory: DevToDataSourceFactory):
        ServiceViewModel<Int, DevToPost, DevToDataSource, DevToDataSourceFactory>(devToDataSourceFactory) {

    override fun getPageSize() = DevToDataSource.PAGE_SIZE

}