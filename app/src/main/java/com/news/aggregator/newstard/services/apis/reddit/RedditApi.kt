package com.news.aggregator.newstard.services.apis.reddit

import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

/**
 * Interface for all reddit API's
 */
interface RedditApi{

    /**
     * API endpoint to get list of all reddit posts in JSON format
     */
    @GET("/.json")
    fun getPostList(): Observable<ResponseData>
}
