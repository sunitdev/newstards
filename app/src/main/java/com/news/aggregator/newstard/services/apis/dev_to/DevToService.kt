package com.news.aggregator.newstard.services.apis.dev_to

import com.news.aggregator.newstard.repositories.dev_to.DevToPost
import io.reactivex.Observable
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

interface DevToService {
    fun getTopArticles(page: Int): Observable<List<DevToPost>>
}

class DevToServiceImp
    @Inject constructor(private val _devToApi: DevToApi): DevToService {

    companion object{
        private const val PUBLISHED_DATETIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSX"
    }

    override fun getTopArticles(page: Int): Observable<List<DevToPost>> {
        return _devToApi.getTopArticles(page).map(this::convertResponseToModel)
    }


    private fun convertResponseToModel(listOfArticles : List<ArticleResponseData>): List<DevToPost>{
        return listOfArticles.map {
            DevToPost(
                title=it.title,
                link=it.url,
                authorName=it.user.name,
                createdAt=convertPublishedAtToCalender(it.published_at),
                numberOfComments=it.comments_count
            )
        }
    }

   private fun convertPublishedAtToCalender(publishedAt: String): Calendar {
       val simpleDateFormat = SimpleDateFormat(PUBLISHED_DATETIME_FORMAT, Locale.getDefault())
       simpleDateFormat.parse(publishedAt)
       return simpleDateFormat.calendar
   }
}