package com.news.aggregator.newstard.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.news.aggregator.newstard.repositories.services.NewsService
import com.news.aggregator.newstard.repositories.services.ServiceRepository
import io.reactivex.BackpressureStrategy
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
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
    fun getServices(): LiveData<NewsService>{

        val serviceFlowable = serviceRepository.getServices()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .toFlowable(BackpressureStrategy.LATEST)

        return LiveDataReactiveStreams.fromPublisher(serviceFlowable)
    }
}
