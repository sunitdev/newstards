package com.news.aggregator.newstard.services.apis.medium

// GraphQL model
// Note: Using this hack as the schema for medium is not available
class PopularPostsGraphQlQuery(limit: Int = 25, after: String=""){

    val operationName = "TopicHandler"

    val variables =  PopularPostGraphQlQueryVaraibles(limit, after)

    val query = """
            query TopicHandler(${'$'}topicSlug: ID!, ${'$'}feedPagingOptions: PagingOptions) {
          topic(slug: ${'$'}topicSlug) {
            ...TopicScreen_topic
            __typename
          }
        }

        fragment PostListingItemFeed_postPreview on PostPreview {
          post {
            ...PostListingItemPreview_post
            ...PostListingItemByline_post
            ...PostListingItemImage_post
            ...PostPresentationTracker_post
            __typename
          }
          __typename
        }

        fragment PostListingItemPreview_post on Post {
          id
          mediumUrl
          title
          clapCount
          previewContent {
            subtitle
            isFullContent
            __typename
          }
          __typename
        }

        fragment PostListingItemByline_post on Post {
          id
          creator {
            id
            username
            name
            __typename
          }
          isLocked
          readingTime
          ...BookmarkButton_post
          firstPublishedAt
          statusForCollection
          collection {
            id
            name
            __typename
          }
          __typename
        }

        fragment BookmarkButton_post on Post {
          ...SusiClickable_post
          ...WithSetReadingList_post
          __typename
        }

        fragment SusiClickable_post on Post {
          ...SusiContainer_post
          __typename
        }

        fragment SusiContainer_post on Post {
          id
          __typename
        }

        fragment WithSetReadingList_post on Post {
          ...ReadingList_post
          __typename
        }

        fragment ReadingList_post on Post {
          id
          readingList
          __typename
        }

        fragment PostListingItemImage_post on Post {
          id
          mediumUrl
          previewImage {
            id
            focusPercentX
            focusPercentY
            __typename
          }
          __typename
        }

        fragment PostPresentationTracker_post on Post {
          id
          visibility
          isLockedPreviewOnly
          previewContent {
            isFullContent
            __typename
          }
          collection {
            id
            __typename
          }
          __typename
        }

        fragment TopicScreen_topic on Topic {
          id
          ...TopicLatest_topic
          __typename
        }

        fragment TopicLatest_topic on Topic {

          latestPosts(paging: ${'$'}feedPagingOptions) {
            postPreviews {
              post {
                id
                __typename
              }
              ...PostListingItemFeed_postPreview
              __typename
            }
            pagingInfo {
              next {
                limit
                to
                __typename
              }
              __typename
            }
            __typename
          }
          __typename
        }
        """.trimIndent()
}

class PopularPostGraphQlQueryVaraibles(limit: Int, after: String){

    val topicSlug = "popular"
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
