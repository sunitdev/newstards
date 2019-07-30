package com.news.aggregator.newstard.repositories.preferences

import com.news.aggregator.newstard.services.preferences.AppPerference

interface PreferenceRepository {
    fun getAppPreference(): AppPerference
}

class PreferenceRepositoryImp: PreferenceRepository {

    override fun getAppPreference(): AppPerference {
        return AppPerference
    }
}
