package com.news.aggregator.newstard.repositories.hackernews

import java.util.*

data class HackerNewsPost(var id: Long,
                          var title: String,
                          var link: String?,
                          var authorName: String,
                          var createdAt: Calendar,
                          var numberOfComments: Int)