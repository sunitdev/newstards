package com.news.aggregator.newstard.services.apis.hackernews

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path


interface HackerNewsApi {

    @GET("/v0/topstories.json")
    fun getLatestPostIds(): Single<List<Long>>

    @GET("/v0/item/{postId}.json")
    fun getPostDetail(@Path("postId") postId: Long): Single<PostResponseData>

}