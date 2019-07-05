package com.news.aggregator.newstard.services.apis.reddit

/**
 * Data classes to Represent API response of Reddit
 */

data class ResponseData(var data: ChildrenList)
data class ChildrenList(var children: List<ChildrenData>)
data class ChildrenData(var data: PostData)
data class PostData(var title: String, var url: String, var ups: Int)
