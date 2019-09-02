package com.news.aggregator.newstard.ui.pagination.medium

import com.news.aggregator.newstard.repositories.medium.MediumPost
import com.news.aggregator.newstard.repositories.medium.MediumRepository
import com.news.aggregator.newstard.ui.pagination.base.ServiceDataSourceFactory
import javax.inject.Inject

class MediumPostDataSourceFactory
    @Inject constructor(private val _mediumRepository: MediumRepository):
        ServiceDataSourceFactory<String, MediumPost, MediumPostDataSource>(){

    override fun getDataSource() = MediumPostDataSource(_mediumRepository)

}
