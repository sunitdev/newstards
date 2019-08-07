package com.news.aggregator.newstard.services.apis.hackernews

data class PostResponseData(var id: Long, var by: String, var time: Long,
                            var url: String?, var title: String, var kids: List<Int>?)