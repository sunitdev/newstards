package com.news.aggregator.newstard.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.news.aggregator.newstard.repositories.services.NewsService
import com.news.aggregator.newstard.repositories.services.ServiceRepository
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(private val serviceRepository: ServiceRepository):
    ViewModel() {

    /**
     * Return currently active services
     */
    fun getServices() = serviceRepository.getServices()
}
