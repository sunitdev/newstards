package com.news.aggregator.newstard.services.apis.reddit

import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


interface RedditApi{
    @GET("/.json")
    fun getPostList(): Observable<ResponseData>;

}


fun redditApiService() = Retrofit.Builder()
    .baseUrl("https://www.reddit.com")
    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    .addConverterFactory(GsonConverterFactory.create())
    .build()
    .create(RedditApi::class.java)
