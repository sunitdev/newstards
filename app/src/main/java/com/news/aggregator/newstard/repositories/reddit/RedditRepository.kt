package com.news.aggregator.newstard.repositories.reddit

import io.reactivex.Observable

import com.news.aggregator.newstard.services.apis.reddit.RedditPost
import com.news.aggregator.newstard.services.apis.reddit.RedditService


class RedditRepository{

    private var _redditService: RedditService = RedditService()

    fun getPosts(): Observable<List<RedditPost>> {
        return _redditService.fetchPosts()
    }
}
