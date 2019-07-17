package com.news.aggregator.newstard

import android.app.Activity
import android.app.Application
import androidx.fragment.app.Fragment
import com.news.aggregator.newstard.di.DaggerAppComponent
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.support.HasSupportFragmentInjector
import io.sentry.Sentry
import io.sentry.android.AndroidSentryClientFactory
import javax.inject.Inject


class App: Application() , HasActivityInjector, HasSupportFragmentInjector  {

    @Inject
    lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    @Inject
    lateinit var supportFragmentInjector: DispatchingAndroidInjector<Fragment>

    override fun activityInjector() = activityDispatchingAndroidInjector

    override fun supportFragmentInjector() = supportFragmentInjector

    override fun onCreate() {
        super.onCreate()
        initSentry()

        initDagger()

        setAppTheme()
    }

    private fun initDagger() {
        DaggerAppComponent.builder()
            .bindApplication(this)
            .build()
            .inject(this)
    }

    private fun initSentry(){
        Sentry.init(
            BuildConfig.SENTRY_DSN,
            AndroidSentryClientFactory(applicationContext)
        )
    }

    private fun setAppTheme() {
        setTheme(R.style.App_Theme_Dark)
    }
}
