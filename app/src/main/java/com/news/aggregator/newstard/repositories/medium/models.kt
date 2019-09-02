package com.news.aggregator.newstard.repositories.medium

import java.util.*

data class MediumPostBatch(var nextId: String,
                           var posts: List<MediumPost>)

data class MediumPost(var title: String,
                      var link: String,
                      var authorName: String,
                      var createdAt: Calendar,
                      var numberOfComments: Int)