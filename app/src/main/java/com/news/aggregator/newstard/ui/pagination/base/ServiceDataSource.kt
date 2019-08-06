package com.news.aggregator.newstard.ui.pagination.base

import androidx.lifecycle.LiveData
import com.news.aggregator.newstard.network.NetworkState

interface ServiceDataSource {

    fun getInitialLoadStateLiveData(): LiveData<NetworkState>
    fun getPaginationLoadStateLiveData(): LiveData<NetworkState>

    fun clear()
}