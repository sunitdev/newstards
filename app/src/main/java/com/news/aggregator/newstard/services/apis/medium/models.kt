package com.news.aggregator.newstard.services.apis.medium

import com.google.gson.annotations.Expose

// GraphQL model
// Note: Using this hack as the schema for medium is not available
class PopularPostsGraphQlQuery(limit: Int = 25, after: String=""){

    val operationName = "PopularTopicsList"

    val variables = FeedPagingOptionsGraphQlQuery(limit, after)

    val query = """
            query PopularTopicsList(${'$'}feedPagingOptions: PagingOptions) {
                topic(slug: "popular") {
                    latestPosts(paging: ${'$'}feedPagingOptions) {
                        postPreviews {
                            ...PostListingItemFeed_postPreview
                        }
                        pagingInfo {
                            next {
                                limit
                                to
                            }
                        }
                    }
                }
            }
            
            fragment PostListingItemFeed_postPreview on PostPreview {
                post {
                    mediumUrl
                    title
                    clapCount
                    creator {
                        name
                    }
                    firstPublishedAt
                }
            }
        """.trimIndent()
}

class FeedPagingOptionsGraphQlQuery(limit: Int, after: String){

    val feedPagingOptions = PagingOptionsGraphQlQuery(limit, after)
}

data class PagingOptionsGraphQlQuery(val limit: Int, val to: String)

// Response Model
data class PopularPostResponse(val data: PopularPostResponseData)

data class PopularPostResponseData(val topic: PopularPostResponseTopic)

data class PopularPostResponseTopic(val latestPosts: PopularPostResponseLatestPosts)

data class PopularPostResponseLatestPosts(val postPreviews: List<PopularPostResponsePostItem>,
                                          val pagingInfo: PopularPostResponsePagingInfo)

data class PopularPostResponsePagingInfo(val next: PopularPostResponsePagingNext)

data class PopularPostResponsePagingNext(val limit: Int, val to: String)

data class PopularPostResponsePostItem(val post: PopularPostResponsePostData)

data class PopularPostResponsePostData(val mediumUrl: String, val title: String, val clapCount: Int, val firstPublishedAt: Long,
                                       val creator: PopularPostResponsePostCreator)

data class PopularPostResponsePostCreator(val name: String)
