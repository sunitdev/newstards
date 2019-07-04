package com.news.aggregator.newstard.repositories.reddit

import io.reactivex.Observable

import com.news.aggregator.newstard.services.apis.reddit.RedditPost
import com.news.aggregator.newstard.services.apis.reddit.RedditService


interface RedditRepository{

    fun getPosts(): Observable<List<RedditPost>>
}


class RedditRepositoryImpl(private val _redditService: RedditService): RedditRepository{

    override fun getPosts(): Observable<List<RedditPost>> {
        return _redditService.fetchPosts()
    }
}
