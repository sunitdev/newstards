package com.news.aggregator.newstard.di.modules.services

import com.google.gson.GsonBuilder
import com.news.aggregator.newstard.repositories.medium.MediumRepository
import com.news.aggregator.newstard.repositories.medium.MediumRepositoryImp
import com.news.aggregator.newstard.services.apis.medium.*
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class MediumModule {

    /**
     * Provider or Medium API
     */
    @Provides
    @Singleton
    fun provideMediumApi(): MediumApi {
        // Type adapter for graphql query to prevent order of fields
        val gson = GsonBuilder()
            .registerTypeAdapter(PopularPostsGraphQlQuery::class.java, PopularPostGraphQlJsonSerializer())
            .create()

        return Retrofit.Builder()
            .baseUrl("https://medium.com")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(MediumApi::class.java)
    }

    /**
     * Provider for medium service
     */
    @Provides
    @Singleton
    fun provideMediumService(mediumApi: MediumApi): MediumService = MediumServiceImp(mediumApi)

    /**
     * Provider for Medium Repository
     */
    @Provides
    @Singleton
    fun provideMediumRepository(mediumService: MediumService): MediumRepository = MediumRepositoryImp(mediumService)

}
