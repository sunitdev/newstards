package com.news.aggregator.newstard.services.apis.medium

import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface MediumApi {

    /**
     * Get latest post from medium
     */
    @POST("/_/graphql")
    fun getPopularPosts(@Body popularPostsGraphQlQuery: PopularPostsGraphQlQuery): Single<PopularPostResponse>
}