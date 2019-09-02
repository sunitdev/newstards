package com.news.aggregator.newstard.services.apis.hackernews

import android.util.Log
import com.news.aggregator.newstard.repositories.hackernews.HackerNewsPost
import io.reactivex.Observable
import io.reactivex.Single
import java.util.*
import javax.inject.Inject

interface HackerNewsService {

    fun getLatestPostIds(): Single<List<Long>>

    fun getPostsByIds(ids: List<Long>): Single<List<HackerNewsPost>>

}

class HackerNewsServiceImp
    @Inject constructor(private val _hackerNewsApi: HackerNewsApi): HackerNewsService {

    override fun getLatestPostIds() = _hackerNewsApi.getLatestPostIds()

    override fun getPostsByIds(ids: List<Long>): Single<List<HackerNewsPost>> {
        val postDataRequests = ids.map { _hackerNewsApi.getPostDetail(it).toObservable() }

        return Observable.merge(postDataRequests)
            .map{ HackerNewsPost(
                id=it.id,
                title=it.title,
                link=it.url,
                authorName=it.by,
                createdAt=convertCreatedAtToLocal(it.time),
                numberOfComments=it.kids?.size ?: 0
            )}
            .toList()
    }

    /**
     * Convert UTC timestamp in seconds to calender instance
     */
    private fun convertCreatedAtToLocal(utcTimeStamp: Long): Calendar {
        val dateTime = Calendar.getInstance()
        dateTime.timeInMillis = utcTimeStamp * 1000L
        return dateTime
    }
}