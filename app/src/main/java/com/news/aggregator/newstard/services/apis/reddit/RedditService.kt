package com.news.aggregator.newstard.services.apis.reddit

import io.reactivex.Observable

class RedditService{
    fun fetchPosts() : Observable<List<RedditPost>> {
        return redditApiService().getPostList().map(this::convertApiToModel)
    }

    private fun convertApiToModel(class_object : ResponseData): List<RedditPost>{
        return class_object.data.children.map { it.data }
    }
}
