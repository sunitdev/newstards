package com.news.aggregator.newstard.repositories.dev_to

import java.util.Calendar

data class DevToPost(var title: String,
                     var link: String,
                     var authorName: String,
                     var createdAt: Calendar,
                     var numberOfComments: Int)
