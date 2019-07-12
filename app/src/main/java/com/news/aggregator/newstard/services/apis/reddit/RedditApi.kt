package com.news.aggregator.newstard.services.apis.reddit

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Interface for all reddit API's
 */
interface RedditApi{

    /**
     * API endpoint to get list of all reddit posts in JSON format
     */
    @GET("/.json")
    fun getPostList(@Query("limit") limit: Int,
                    @Query("after") after: String?): Observable<ResponseData>
}
