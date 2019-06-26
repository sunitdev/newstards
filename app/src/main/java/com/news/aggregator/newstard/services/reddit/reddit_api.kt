package com.news.aggregator.newstard.services.reddit

import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

object RedditResponseModel{
    data class ResponseData(var data: ChildrenList)
    data class ChildrenList(var children: List<ChildrenData>)
    data class ChildrenData(var data: FeedDetail)
    data class FeedDetail(var title: String, var url: String, var ups: Int)
}

interface RedditAPI{
    @GET("/.json")
    fun get_news_list(): Observable<RedditResponseModel.ResponseData>;
}

fun apiService() = Retrofit.Builder()
    .baseUrl("https://www.reddit.com")
    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    .addConverterFactory(GsonConverterFactory.create())
    .build().create(RedditAPI::class.java)
