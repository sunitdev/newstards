package com.news.aggregator.newstard.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.news.aggregator.newstard.ui.viewmodels.MainActivityViewModel
import com.news.aggregator.newstard.ui.viewmodels.RedditFragmentViewModel
import com.news.aggregator.newstard.ui.viewmodels.SettingsActivityViewModel
import com.news.aggregator.newstard.ui.viewmodels.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Model to bind viewmodel class
 */
@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainActivityViewModel::class)
    internal abstract fun bindMainActivityViewModel(mainActivityViewModel: MainActivityViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RedditFragmentViewModel::class)
    internal abstract fun bindRedditFragmentViewModel(redditFragmentViewModel: RedditFragmentViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SettingsActivityViewModel::class)
    internal abstract fun bindSettingsActivityViewModel(settingsActivityViewModel: SettingsActivityViewModel): ViewModel

}