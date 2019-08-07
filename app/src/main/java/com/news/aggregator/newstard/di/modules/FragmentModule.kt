package com.news.aggregator.newstard.di.modules

import com.news.aggregator.newstard.ui.fargments.HackerNewsFragment
import com.news.aggregator.newstard.ui.fargments.MediumFragment
import com.news.aggregator.newstard.ui.fargments.RedditFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    internal abstract fun contributeRedditFragment(): RedditFragment

    @ContributesAndroidInjector
    internal abstract fun contributeMediumFragment(): MediumFragment

    @ContributesAndroidInjector
    internal abstract fun contributeHackerNewsFragment(): HackerNewsFragment
}