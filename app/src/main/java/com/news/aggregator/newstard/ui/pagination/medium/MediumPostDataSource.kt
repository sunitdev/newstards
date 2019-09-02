package com.news.aggregator.newstard.ui.pagination.medium

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.news.aggregator.newstard.network.NetworkState
import com.news.aggregator.newstard.repositories.medium.MediumPost
import com.news.aggregator.newstard.repositories.medium.MediumRepository
import com.news.aggregator.newstard.ui.pagination.base.ServiceDataSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MediumPostDataSource
    @Inject constructor(private val _mediumRepository: MediumRepository):
        ServiceDataSource, PageKeyedDataSource<String, MediumPost>(){

    companion object{
        const val PAGE_SIZE = 25
    }

    private val initialLoadStateLiveData = MutableLiveData<NetworkState>()
    private val paginationLoadStateLiveData = MutableLiveData<NetworkState>()
    private val compositeDisposable = CompositeDisposable()

    override fun getPaginationLoadStateLiveData() = paginationLoadStateLiveData

    override fun getInitialLoadStateLiveData() = initialLoadStateLiveData

    override fun clear() {
        compositeDisposable.clear()
    }

    override fun loadInitial(params: LoadInitialParams<String>, callback: LoadInitialCallback<String, MediumPost>) {
        initialLoadStateLiveData.postValue(NetworkState.LOADING)

        val postListDisposable = _mediumRepository.getPopularPost(PAGE_SIZE, null)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                initialLoadStateLiveData.postValue(NetworkState.SUCCESS)

                callback.onResult(it.posts, null, it.nextId)
            }, {
                initialLoadStateLiveData.postValue(NetworkState.ERROR)
            })

        compositeDisposable.add(postListDisposable)
    }

    override fun loadAfter(params: LoadParams<String>, callback: LoadCallback<String, MediumPost>) {

        paginationLoadStateLiveData.postValue(NetworkState.LOADING)

        val postListDisposable =_mediumRepository.getPopularPost(PAGE_SIZE, params.key)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                paginationLoadStateLiveData.postValue(NetworkState.SUCCESS)

                callback.onResult(it.posts, it.nextId)
            }, {
                paginationLoadStateLiveData.postValue(NetworkState.ERROR)
            })

        compositeDisposable.add(postListDisposable)
    }

    override fun loadBefore(params: LoadParams<String>, callback: LoadCallback<String, MediumPost>) {
        // Previous data will be already present.
    }
}