package com.news.aggregator.newstard

import android.app.Application
import dagger.android.HasActivityInjector
import android.app.Activity
import com.news.aggregator.newstard.di.DaggerAppComponent
import dagger.android.DispatchingAndroidInjector
import javax.inject.Inject
import dagger.android.AndroidInjector


class App: Application() , HasActivityInjector  {

    @Inject
    lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): AndroidInjector<Activity> {
        return this.activityDispatchingAndroidInjector
    }

    override fun onCreate() {
        super.onCreate()

        _initDagger()
    }

    private fun _initDagger() {
        DaggerAppComponent.builder()
            .bindApplication(this)
            .build()
            .inject(this)
    }
}
