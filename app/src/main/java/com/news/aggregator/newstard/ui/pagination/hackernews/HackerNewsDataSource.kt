package com.news.aggregator.newstard.ui.pagination.hackernews

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.news.aggregator.newstard.network.NetworkState
import com.news.aggregator.newstard.repositories.hackernews.HackerNewsPost
import com.news.aggregator.newstard.repositories.hackernews.HackerNewsRepository
import com.news.aggregator.newstard.ui.pagination.base.ServiceDataSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class HackerNewsDataSource
@Inject constructor(private val _hackerNewsRepository: HackerNewsRepository) :
    ServiceDataSource, PageKeyedDataSource<Int, HackerNewsPost>() {

    companion object {
        const val PAGE_SIZE = 10
    }

    private val initialLoadStateLiveData = MutableLiveData<NetworkState>()
    private val paginationLoadStateLiveData = MutableLiveData<NetworkState>()
    private val compositeDisposable = CompositeDisposable()

    private lateinit var latestPostIds: List<Long>
    private var nextPostIdIndex = 0

    override fun getPaginationLoadStateLiveData() = paginationLoadStateLiveData

    override fun getInitialLoadStateLiveData() = initialLoadStateLiveData

    override fun clear() {
        compositeDisposable.clear()
    }

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, HackerNewsPost>) {
        initialLoadStateLiveData.postValue(NetworkState.LOADING)

        val postListDisposable = _hackerNewsRepository.getLatestPostIds()
            .flatMap {
                latestPostIds = it
                nextPostIdIndex = PAGE_SIZE
                _hackerNewsRepository.getPostsByIds(it.subList(0, PAGE_SIZE - 1))
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                initialLoadStateLiveData.postValue(NetworkState.SUCCESS)

                callback.onResult(it, null, nextPostIdIndex)
            }, {
                initialLoadStateLiveData.postValue(NetworkState.ERROR)
            })

        compositeDisposable.add(postListDisposable)
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, HackerNewsPost>) {

        if(nextPostIdIndex >=latestPostIds.size){
            callback.onResult(emptyList(), null)
            return
        }

        paginationLoadStateLiveData.postValue(NetworkState.LOADING)

        val postListDisposable =_hackerNewsRepository.getPostsByIds(latestPostIds.subList(nextPostIdIndex, nextPostIdIndex+ PAGE_SIZE - 1))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                paginationLoadStateLiveData.postValue(NetworkState.SUCCESS)
                nextPostIdIndex += PAGE_SIZE

                callback.onResult(it, nextPostIdIndex)
            }, {
                paginationLoadStateLiveData.postValue(NetworkState.ERROR)
            })

        compositeDisposable.add(postListDisposable)
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, HackerNewsPost>) {}
}