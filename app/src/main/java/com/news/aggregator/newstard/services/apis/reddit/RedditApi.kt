package com.news.aggregator.newstard.services.apis.reddit

import io.reactivex.Observable
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
