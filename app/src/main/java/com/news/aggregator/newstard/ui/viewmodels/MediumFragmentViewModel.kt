package com.news.aggregator.newstard.ui.viewmodels

import com.news.aggregator.newstard.repositories.medium.MediumPost
import com.news.aggregator.newstard.ui.pagination.medium.MediumPostDataSource
import com.news.aggregator.newstard.ui.pagination.medium.MediumPostDataSourceFactory
import com.news.aggregator.newstard.ui.viewmodels.base.ServiceViewModel
import javax.inject.Inject


class MediumFragmentViewModel
    @Inject constructor(mediumPostDataSourceFactory: MediumPostDataSourceFactory):
        ServiceViewModel<String, MediumPost, MediumPostDataSource, MediumPostDataSourceFactory>(mediumPostDataSourceFactory){

    override fun getPageSize() = MediumPostDataSource.PAGE_SIZE

}
