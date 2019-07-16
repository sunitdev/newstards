package com.news.aggregator.newstard.utils.extensions

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.news.aggregator.newstard.network.NetworkState

fun LiveData<NetworkState>.observerTillSuccess(lifeCycleOwner: LifecycleOwner, observer: Observer<NetworkState>){

    observe(lifeCycleOwner, object:Observer<NetworkState>{
        override fun onChanged(state: NetworkState?) {
            observer.onChanged(state)
            // If state is success then stop observing
            if(state == NetworkState.SUCCESS) removeObserver(this)
        }
    })

}