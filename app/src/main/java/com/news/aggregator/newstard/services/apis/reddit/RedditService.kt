package com.news.aggregator.newstard.services.apis.reddit

import io.reactivex.Observable


interface RedditService{
    fun fetchPosts() : Observable<List<RedditPost>>
}


class RedditServiceImpl(private val _redditApiService: RedditApi): RedditService{
    override fun fetchPosts() : Observable<List<RedditPost>> {
        return _redditApiService.getPostList().map(this::_convertApiToModel)
    }

    private fun _convertApiToModel(class_object : ResponseData): List<RedditPost>{
        return class_object.data.children.map { it.data }
    }
}
