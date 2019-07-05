package com.news.aggregator.newstard.repositories.reddit

import io.reactivex.Observable

import com.news.aggregator.newstard.services.apis.reddit.RedditPost
import com.news.aggregator.newstard.services.apis.reddit.RedditService
import javax.inject.Inject

/**
 * Provides data for Reddit Activity
 */

interface RedditRepository{

    fun getPosts(): Observable<List<RedditPost>>
}


class RedditRepositoryImpl: RedditRepository{

    private val _redditService: RedditService

    @Inject
    constructor(_redditService: RedditService){
        this._redditService = _redditService
    }

    /**
     * Returns all reddit posts
     */
    override fun getPosts(): Observable<List<RedditPost>> {
        return _redditService.fetchPosts()
    }
}
