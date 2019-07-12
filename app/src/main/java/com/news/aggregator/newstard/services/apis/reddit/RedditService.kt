package com.news.aggregator.newstard.services.apis.reddit

import com.news.aggregator.newstard.repositories.reddit.RedditPost
import io.reactivex.Observable
import java.util.*
import javax.inject.Inject


interface RedditService{
    fun fetchPosts(limit: Int, afterID: String?) : Observable<List<RedditPost>>
}


class RedditServiceImpl
    @Inject constructor(private val _redditApiService: RedditApi):
        RedditService {

    override fun fetchPosts(limit: Int, afterID: String?) : Observable<List<RedditPost>> {
        return _redditApiService.getPostList(limit, afterID ).map(this::convertApiToModel)
    }

    private fun convertApiToModel(class_object : ResponseData): List<RedditPost>{

        return class_object.data.children.map {
            RedditPost(id=it.data.name,
                title=it.data.title,
                link="https://reddit.com${it.data.permalink}",
                authorName = it.data.author,
                createdAt = convertCreatedAtToLocal(it.data.created_utc),
                numberOfComments = it.data.num_comments)
        }
    }

    private fun convertCreatedAtToLocal(utcTimeStamp: Long): Calendar {
        val dateTime = Calendar.getInstance()
        // API returns timestamp in seconds.
        dateTime.timeInMillis = utcTimeStamp * 1000L
        return dateTime
    }

}
