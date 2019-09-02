package com.news.aggregator.newstard.services.apis.dev_to

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface DevToApi {

    @GET("/api/articles")
    fun getTopArticles(@Query("page") page: Int = 1): Observable<List<ArticleResponseData>>

}