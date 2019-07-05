package com.news.aggregator.newstard.di

import android.app.Application
import com.news.aggregator.newstard.di.modules.ActivityModule
import com.news.aggregator.newstard.di.modules.ServicesModule
import dagger.Component
import com.news.aggregator.newstard.App
import dagger.BindsInstance
import javax.inject.Singleton

@Singleton
@Component(modules = [ServicesModule::class, ActivityModule::class])
interface AppComponent{

    @Component.Builder
     interface Builder {
        @BindsInstance
        fun bindApplication(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: App)
}
