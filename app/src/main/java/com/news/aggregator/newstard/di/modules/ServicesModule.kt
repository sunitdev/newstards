package com.news.aggregator.newstard.di.modules

import com.news.aggregator.newstard.di.modules.services.MediumModule
import com.news.aggregator.newstard.di.modules.services.RedditModule
import dagger.Module

@Module(includes = [RedditModule::class, MediumModule::class])
interface ServicesModule