package com.news.aggregator.newstard.di.modules.services

import com.news.aggregator.newstard.repositories.hackernews.HackerNewsRepository
import com.news.aggregator.newstard.repositories.hackernews.HackerNewsRepositoryImp
import com.news.aggregator.newstard.services.apis.hackernews.HackerNewsApi
import com.news.aggregator.newstard.services.apis.hackernews.HackerNewsService
import com.news.aggregator.newstard.services.apis.hackernews.HackerNewsServiceImp
import com.news.aggregator.newstard.ui.pagination.hackernews.HackerNewsDataSource
import com.news.aggregator.newstard.ui.pagination.hackernews.HackerNewsDataSourceFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class HackerNewsModule {

    /**
     * Provides Retrofit builder for hacker news service
     */
    @Provides
    @Singleton
    fun provideHackerNewsApiService(): HackerNewsApi{
        return Retrofit.Builder()
            .baseUrl("https://hacker-news.firebaseio.com")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(HackerNewsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideHackerNewsService(hackerNewsApi: HackerNewsApi): HackerNewsService = HackerNewsServiceImp(hackerNewsApi)

    @Provides
    @Singleton
    fun provideHackerNewsRepository(hackerNewsService: HackerNewsService): HackerNewsRepository = HackerNewsRepositoryImp(hackerNewsService)

    @Provides
    @Singleton
    fun provideHackerNewsDataSource(hackerNewsRepository: HackerNewsRepository): HackerNewsDataSource = HackerNewsDataSource(hackerNewsRepository)

    @Provides
    @Singleton
    fun provideHackerNewDataSourceFactory(hackerNewsRepository: HackerNewsRepository): HackerNewsDataSourceFactory = HackerNewsDataSourceFactory(hackerNewsRepository)
}
