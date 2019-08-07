package com.news.aggregator.newstard.repositories.hackernews

import com.news.aggregator.newstard.services.apis.hackernews.HackerNewsService
import io.reactivex.Single
import javax.inject.Inject

interface HackerNewsRepository {
    fun getLatestPostIds(): Single<List<Long>>

    fun getPostsByIds(ids: List<Long>): Single<List<HackerNewsPost>>
}

class HackerNewsRepositoryImp
       @Inject constructor(private val _hackerNewsService: HackerNewsService): HackerNewsRepository{

    override fun getLatestPostIds() = _hackerNewsService.getLatestPostIds()

    override fun getPostsByIds(ids: List<Long>) = _hackerNewsService.getPostsByIds(ids)
}