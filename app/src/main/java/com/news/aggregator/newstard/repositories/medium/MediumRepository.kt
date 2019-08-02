package com.news.aggregator.newstard.repositories.medium

import com.news.aggregator.newstard.services.apis.medium.MediumService
import io.reactivex.Observable
import javax.inject.Inject

interface MediumRepository {
    fun getPopularPost(limit: Int, after: String?): Observable<MediumPostBatch>
}

class MediumRepositoryImp
    @Inject constructor(private val _mediumService: MediumService) : MediumRepository {

    override fun getPopularPost(limit: Int, after: String?) = _mediumService.getPopularPost(limit, after)

}