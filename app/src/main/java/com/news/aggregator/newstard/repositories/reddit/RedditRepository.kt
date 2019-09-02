package com.news.aggregator.newstard.repositories.reddit

import com.news.aggregator.newstard.services.apis.reddit.RedditService
import io.reactivex.Single
import javax.inject.Inject

/**
 * Provides data for Reddit Activity
 */

interface RedditRepository{

    fun getPosts(limit: Int, afterId: String?): Single<List<RedditPost>>
}


class RedditRepositoryImpl
    @Inject constructor(private val _redditService: RedditService): RedditRepository{

    /**
     * Returns all reddit posts
     */
    override fun getPosts(limit: Int, afterId: String?) = _redditService.fetchPosts(limit, afterId)

}
