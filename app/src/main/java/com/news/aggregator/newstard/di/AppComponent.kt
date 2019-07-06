package com.news.aggregator.newstard.di

import android.app.Application
import com.news.aggregator.newstard.App
import com.news.aggregator.newstard.di.modules.ActivityModule
import com.news.aggregator.newstard.di.modules.AppModule
import com.news.aggregator.newstard.di.modules.FragmentModule
import com.news.aggregator.newstard.di.modules.ServicesModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class,
                    AppModule::class,
                    ServicesModule::class,
                    ActivityModule::class,
                    FragmentModule::class,
                    ViewModelModule::class])
interface AppComponent{

    @Component.Builder
     interface Builder {
        @BindsInstance
        fun bindApplication(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: App)
}
