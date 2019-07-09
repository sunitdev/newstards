package com.news.aggregator.newstard.repositories.reddit

data class RedditPost(var id: String,
                      var title: String,
                      var link: String,
                      var author: String,
                      var time_ago: Long)
