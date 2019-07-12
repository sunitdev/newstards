package com.news.aggregator.newstard.ui.pagination.reddit

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.ItemKeyedDataSource
import com.news.aggregator.newstard.network.NetworkState
import com.news.aggregator.newstard.repositories.reddit.RedditPost
import com.news.aggregator.newstard.repositories.reddit.RedditRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.disposables.Disposables
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RedditPostDataSource
        @Inject constructor(private val _redditRepository: RedditRepository):
        ItemKeyedDataSource<String, RedditPost>() {

    private val initialLoadStateLiveData = MutableLiveData<NetworkState>()

    private val compositeDisposable = CompositeDisposable()

    private lateinit var _lastPaginationParams: LoadParams<String>
    private lateinit var _lastPaginationCallback: LoadCallback<RedditPost>

    companion object{
        val PAGE_SIZE = 25
    }

    override fun loadInitial(params: LoadInitialParams<String>, callback: LoadInitialCallback<RedditPost>) {

        initialLoadStateLiveData.postValue(NetworkState.getLoadingState())

        val postListDisposable = _redditRepository.getPosts(PAGE_SIZE, null)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({

                initialLoadStateLiveData.postValue(NetworkState.getSuccessState())

                callback.onResult(it)
            }, {
                initialLoadStateLiveData.postValue(NetworkState.getErrorState())
            })

        compositeDisposable.add(postListDisposable)
    }

    override fun loadAfter(params: LoadParams<String>, callback: LoadCallback<RedditPost>) {
        _lastPaginationParams = params
        _lastPaginationCallback = callback

        val postListDisposable = _redditRepository.getPosts(PAGE_SIZE, params.key)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { callback.onResult(it) }

        compositeDisposable.add(postListDisposable)
    }

    override fun loadBefore(params: LoadParams<String>, callback: LoadCallback<RedditPost>) {
        // Previous data will be already present.
    }

    override fun getKey(item: RedditPost): String = item.id

    fun getInitialLoadStateLiveData() = initialLoadStateLiveData

    fun clear() {
        compositeDisposable.clear()
    }

}