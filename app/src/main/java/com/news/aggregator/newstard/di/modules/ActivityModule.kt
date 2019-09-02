package com.news.aggregator.newstard.di.modules

import com.news.aggregator.newstard.ui.activities.MainActivity
import com.news.aggregator.newstard.ui.activities.SettingsActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ActivityModule{

    @ContributesAndroidInjector
    fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector
    fun contributeSettingsActivity(): SettingsActivity
}
