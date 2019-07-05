package com.news.aggregator.newstard.services.apis.reddit

import retrofit2.http.Field

/**
 * Data classes to Represent API response of Reddit
 */

data class ResponseData(var data: ChildrenList)
data class ChildrenList(var children: List<ChildrenData>)
data class ChildrenData(var data: RedditPost)

data class RedditPost(var title: String,
                             @Field("url")var link: String,
                             @Field("ups")var upvotes: Int)
