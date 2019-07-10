package com.news.aggregator.newstard.repositories.reddit

import java.util.Calendar

data class RedditPost(var id: String,
                      var title: String,
                      var link: String,
                      var authorName: String,
                      var createdAt: Calendar,
                      var numberOfComments: Int)
