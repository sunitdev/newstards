package com.news.aggregator.newstard.di.modules.services

import android.app.Application
import com.news.aggregator.newstard.repositories.reddit.RedditRepository
import com.news.aggregator.newstard.repositories.reddit.RedditRepositoryImpl
import com.news.aggregator.newstard.services.apis.reddit.RedditApi
import com.news.aggregator.newstard.services.apis.reddit.RedditService
import com.news.aggregator.newstard.services.apis.reddit.RedditServiceImpl
import com.news.aggregator.newstard.ui.adapters.RedditRecyclerAdapter
import com.news.aggregator.newstard.ui.pagination.reddit.RedditPostDataSource
import com.news.aggregator.newstard.ui.pagination.reddit.RedditPostDataSourceFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Provides dependencies for reddit related services and repositories
 */
@Module
class RedditModule{

    /**
     * Provides Retrofit builder for reddit service
     */
    @Provides
    @Singleton
    fun provideRedditApiService(): RedditApi {
        return Retrofit.Builder()
            .baseUrl("https://www.reddit.com")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RedditApi::class.java)
    }
    
    @Provides
    @Singleton
    fun provideReditService(redditApi: RedditApi): RedditService = RedditServiceImpl(redditApi)

    @Provides
    @Singleton
    fun provideRedditRepository(redditService: RedditService): RedditRepository = RedditRepositoryImpl(redditService)

    @Provides
    @Singleton
    fun provideRedditDataSource(redditRepository: RedditRepository): RedditPostDataSource = RedditPostDataSource(redditRepository)

    @Provides
    @Singleton
    fun provideRedditDataSourceFactory(redditPostDataSource: RedditPostDataSource): RedditPostDataSourceFactory
            = RedditPostDataSourceFactory(redditPostDataSource)

    @Provides
    fun providerRecycleViewAdapter(application: Application): RedditRecyclerAdapter = RedditRecyclerAdapter(application)
}
