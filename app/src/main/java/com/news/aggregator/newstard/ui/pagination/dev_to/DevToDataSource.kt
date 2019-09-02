package com.news.aggregator.newstard.ui.pagination.dev_to

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.news.aggregator.newstard.network.NetworkState
import com.news.aggregator.newstard.repositories.dev_to.DevToPost
import com.news.aggregator.newstard.repositories.dev_to.DevToRepository
import com.news.aggregator.newstard.ui.pagination.base.ServiceDataSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class DevToDataSource
    @Inject constructor(private val _devToRepository: DevToRepository):
        ServiceDataSource, PageKeyedDataSource<Int, DevToPost>(){

    companion object{
        const val PAGE_SIZE = 30
    }

    private val initialLoadStateLiveData = MutableLiveData<NetworkState>()
    private val paginationLoadStateLiveData = MutableLiveData<NetworkState>()
    private val compositeDisposable = CompositeDisposable()

    override fun getPaginationLoadStateLiveData() = paginationLoadStateLiveData

    override fun getInitialLoadStateLiveData() = initialLoadStateLiveData

    override fun clear() {
        compositeDisposable.clear()
    }

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, DevToPost>) {
        initialLoadStateLiveData.postValue(NetworkState.LOADING)

        val postListDisposable = _devToRepository.getTopArticles(0)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                initialLoadStateLiveData.postValue(NetworkState.SUCCESS)

                callback.onResult(it, null, 2)
            }, {
                initialLoadStateLiveData.postValue(NetworkState.ERROR)
            })

        compositeDisposable.add(postListDisposable)
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, DevToPost>) {
        paginationLoadStateLiveData.postValue(NetworkState.LOADING)

        val postListDisposable = _devToRepository.getTopArticles(params.key)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                paginationLoadStateLiveData.postValue(NetworkState.SUCCESS)

                callback.onResult(it, params.key + 1)
            }, {
                paginationLoadStateLiveData.postValue(NetworkState.ERROR)
            })

        compositeDisposable.add(postListDisposable)
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, DevToPost>) {}

}