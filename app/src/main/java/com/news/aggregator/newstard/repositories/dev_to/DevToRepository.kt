package com.news.aggregator.newstard.repositories.dev_to

import com.news.aggregator.newstard.services.apis.dev_to.DevToService
import io.reactivex.Observable
import javax.inject.Inject

interface DevToRepository {
    fun getTopArticles(page: Int): Observable<List<DevToPost>>
}

class DevToRepositoryImp
    @Inject constructor(private val _devToService: DevToService): DevToRepository {

    override fun getTopArticles(page: Int) = _devToService.getTopArticles(page)

}