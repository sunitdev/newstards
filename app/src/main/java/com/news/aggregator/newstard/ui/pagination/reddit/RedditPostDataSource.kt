package com.news.aggregator.newstard.ui.pagination.reddit

import androidx.lifecycle.MutableLiveData
import androidx.paging.ItemKeyedDataSource
import com.news.aggregator.newstard.network.NetworkState
import com.news.aggregator.newstard.repositories.reddit.RedditPost
import com.news.aggregator.newstard.repositories.reddit.RedditRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RedditPostDataSource
        @Inject constructor(private val _redditRepository: RedditRepository):
        ItemKeyedDataSource<String, RedditPost>() {

    private val initialLoadStateLiveData = MutableLiveData<NetworkState>()

    companion object{
        val PAGE_SIZE = 25
    }

    override fun loadInitial(params: LoadInitialParams<String>, callback: LoadInitialCallback<RedditPost>) {

        initialLoadStateLiveData.postValue(NetworkState.getLoadingState())

        _redditRepository.getPosts(PAGE_SIZE, null)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {

                initialLoadStateLiveData.postValue(NetworkState.getSuccessState())

                callback.onResult(it)
            }
    }

    override fun loadAfter(params: LoadParams<String>, callback: LoadCallback<RedditPost>) {
        // TODO: Add error handling and loading code
        _redditRepository.getPosts(PAGE_SIZE, params.key)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { callback.onResult(it) }
    }

    override fun loadBefore(params: LoadParams<String>, callback: LoadCallback<RedditPost>) {
        // Previous data will be already present.
    }

    override fun getKey(item: RedditPost): String = item.id

    fun getInitialLoadStateLiveData() = initialLoadStateLiveData
}