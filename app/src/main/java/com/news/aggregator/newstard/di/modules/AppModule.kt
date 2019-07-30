package com.news.aggregator.newstard.di.modules

import com.news.aggregator.newstard.repositories.preferences.PreferenceRepository
import com.news.aggregator.newstard.repositories.preferences.PreferenceRepositoryImp
import com.news.aggregator.newstard.repositories.services.ServiceRepository
import com.news.aggregator.newstard.repositories.services.ServiceRepositoryImp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideServiceRepository(): ServiceRepository {
        return ServiceRepositoryImp()
    }

    @Provides
    @Singleton
    fun providesPreferenceRepository(): PreferenceRepository {
        return PreferenceRepositoryImp()
    }
}
