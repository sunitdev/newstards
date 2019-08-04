package com.news.aggregator.newstard.ui.pagination.medium

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.news.aggregator.newstard.network.NetworkState
import com.news.aggregator.newstard.repositories.medium.MediumPost
import com.news.aggregator.newstard.repositories.medium.MediumRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MediumPostDataSource
    @Inject constructor(private val _mediumRepository: MediumRepository): PageKeyedDataSource<String, MediumPost>(){

    companion object{
        const val PAGE_SIZE = 25
    }

    private val initialLoadStateLiveData = MutableLiveData<NetworkState>()
    private val paginationLoadStateLiveData = MutableLiveData<NetworkState>()

    fun getPaginationLoadStateLiveData() = paginationLoadStateLiveData

    fun getInitialLoadStateLiveData() = initialLoadStateLiveData

    override fun loadInitial(params: LoadInitialParams<String>, callback: LoadInitialCallback<String, MediumPost>) {
        initialLoadStateLiveData.postValue(NetworkState.LOADING)

        _mediumRepository.getPopularPost(PAGE_SIZE, null)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                initialLoadStateLiveData.postValue(NetworkState.SUCCESS)

                callback.onResult(it.posts, null, it.nextId)
            }, {
                initialLoadStateLiveData.postValue(NetworkState.ERROR)
            })
    }

    override fun loadAfter(params: LoadParams<String>, callback: LoadCallback<String, MediumPost>) {

        paginationLoadStateLiveData.postValue(NetworkState.LOADING)

        _mediumRepository.getPopularPost(PAGE_SIZE, params.key)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                paginationLoadStateLiveData.postValue(NetworkState.SUCCESS)

                callback.onResult(it.posts, it.nextId)
            }, {
                paginationLoadStateLiveData.postValue(NetworkState.ERROR)
            })
    }

    override fun loadBefore(params: LoadParams<String>, callback: LoadCallback<String, MediumPost>) {
        // Previous data will be already present.
    }
}