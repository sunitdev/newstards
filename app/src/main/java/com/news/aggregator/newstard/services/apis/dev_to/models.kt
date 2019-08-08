package com.news.aggregator.newstard.services.apis.dev_to

data class ArticleResponseData(var title: String, var published_at: String, var url: String, var comments_count: Int,
                               var user: ArticleUserResponseData)

data class ArticleUserResponseData(var name: String)