package com.news.aggregator.newstard.services.apis.reddit

import com.news.aggregator.newstard.repositories.reddit.RedditPost
import io.reactivex.Observable
import javax.inject.Inject


interface RedditService{
    fun fetchPosts(limit: Int, afterID: String?) : Observable<List<RedditPost>>
}


class RedditServiceImpl
    @Inject constructor(private val _redditApiService: RedditApi):
        RedditService {

    override fun fetchPosts(limit: Int, afterID: String?) : Observable<List<RedditPost>> {
        return _redditApiService.getPostList(limit, afterID ).map(this::_convertApiToModel)
    }

    private fun _convertApiToModel(class_object : ResponseData): List<RedditPost>{
        return class_object.data.children.map {
            RedditPost(id=it.data.name, title=it.data.title,
                link="https://reddit.com${it.data.permalink}")
        }
    }
}
