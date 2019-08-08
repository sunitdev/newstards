package com.news.aggregator.newstard.di.modules.services

import com.news.aggregator.newstard.repositories.dev_to.DevToRepository
import com.news.aggregator.newstard.repositories.dev_to.DevToRepositoryImp
import com.news.aggregator.newstard.services.apis.dev_to.DevToApi
import com.news.aggregator.newstard.services.apis.dev_to.DevToService
import com.news.aggregator.newstard.services.apis.dev_to.DevToServiceImp
import com.news.aggregator.newstard.ui.pagination.dev_to.DevToDataSource
import com.news.aggregator.newstard.ui.pagination.dev_to.DevToDataSourceFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class DevToModule {

    @Provides
    @Singleton
    fun provideDevToApiService(): DevToApi {
        return Retrofit.Builder()
            .baseUrl("https://dev.to")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DevToApi::class.java)
    }

    @Provides
    @Singleton
    fun provideDevToService(devToApi: DevToApi): DevToService = DevToServiceImp(devToApi)

    @Provides
    @Singleton
    fun provideDevToRepository(devToService: DevToService): DevToRepository = DevToRepositoryImp(devToService)

    @Provides
    @Singleton
    fun provideDevToDataSource(devToRepository: DevToRepository): DevToDataSource = DevToDataSource(devToRepository)

    @Provides
    @Singleton
    fun provideDevToDataSourceFactory(devToRepository: DevToRepository): DevToDataSourceFactory = DevToDataSourceFactory(devToRepository)
}
