package com.news.aggregator.newstard.services.apis.reddit

import io.reactivex.Observable
import javax.inject.Inject

/**
 * All reddit
 */

interface RedditService{

    fun fetchPosts() : Observable<List<RedditPost>>
}


class RedditServiceImpl: RedditService{

    private val _redditApiService: RedditApi

    @Inject
    constructor(_redditApiService: RedditApi){
        this._redditApiService = _redditApiService
    }
    override fun fetchPosts() : Observable<List<RedditPost>> {
        return _redditApiService.getPostList().map(this::_convertApiToModel)
    }

    private fun _convertApiToModel(class_object : ResponseData): List<RedditPost>{
        return class_object.data.children.map { it.data }
    }
}
