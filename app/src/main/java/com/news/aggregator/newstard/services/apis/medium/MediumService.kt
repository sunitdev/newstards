package com.news.aggregator.newstard.services.apis.medium

import com.news.aggregator.newstard.repositories.medium.MediumPost
import com.news.aggregator.newstard.repositories.medium.MediumPostBatch
import io.reactivex.Observable
import io.reactivex.Single
import java.util.*
import javax.inject.Inject

interface MediumService {
    fun getPopularPost(limit: Int, after: String?): Single<MediumPostBatch>
}

class MediumServiceImp
        @Inject constructor(private val mediumApi: MediumApi) : MediumService{

    override fun getPopularPost(limit: Int, after: String?): Single<MediumPostBatch> {
        return mediumApi.getPopularPosts(PopularPostsGraphQlQuery(limit, after ?: ""))
            .map(this::convertResponseToModel)
    }

    /**
     * Convert response to model instance
     */
    private fun convertResponseToModel(response: PopularPostResponse): MediumPostBatch{
        val posts = response.data.topic.latestPosts.postPreviews.map {
            val postData = it.post

            MediumPost(
                title = postData.title,
                link = postData.mediumUrl,
                authorName = postData.creator.name,
                numberOfComments = postData.clapCount,
                createdAt = convertPublishedAtToLocal(postData.firstPublishedAt)
            )
        }

        return MediumPostBatch(
            nextId = response.data.topic.latestPosts.pagingInfo.next.to,
            posts = posts
        )
    }

    /**
     * Convert utc long to calendar instance
     */
    private fun convertPublishedAtToLocal(utcTimeStamp: Long): Calendar {
        val dateTime = Calendar.getInstance()
        dateTime.timeInMillis = utcTimeStamp
        return dateTime
    }
}
