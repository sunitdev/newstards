package com.news.aggregator.newstard.di.modules

import com.news.aggregator.newstard.ui.fargments.RedditFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    internal abstract fun contributeRedditFragment(): RedditFragment
}