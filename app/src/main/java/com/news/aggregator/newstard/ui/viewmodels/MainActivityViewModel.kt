package com.news.aggregator.newstard.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.news.aggregator.newstard.repositories.services.NewsService
import com.news.aggregator.newstard.repositories.services.ServiceRepository
import javax.inject.Inject

class MainActivityViewModel: ViewModel {

    private val serviceRepository: ServiceRepository

    @Inject
    constructor(serviceRepository: ServiceRepository){
        this.serviceRepository = serviceRepository
    }

    /**
     * Return currently active services
     */
    fun getServices(): List<NewsService> = serviceRepository.getServices()
}
