package com.news.aggregator.newstard

import android.app.Activity
import android.app.Application
import androidx.fragment.app.Fragment
import com.news.aggregator.newstard.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject


class App: Application() , HasActivityInjector, HasSupportFragmentInjector  {

    @Inject
    lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    @Inject
    lateinit var supportFragmentInjector: DispatchingAndroidInjector<Fragment>

    override fun activityInjector(): AndroidInjector<Activity> = activityDispatchingAndroidInjector

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = supportFragmentInjector

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
