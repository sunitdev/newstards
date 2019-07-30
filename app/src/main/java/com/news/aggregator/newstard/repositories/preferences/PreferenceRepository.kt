package com.news.aggregator.newstard.repositories.preferences

import com.news.aggregator.newstard.services.preferences.AppPreference

interface PreferenceRepository {
    fun getAppPreference(): AppPreference
}

class PreferenceRepositoryImp: PreferenceRepository {

    override fun getAppPreference(): AppPreference {
        return AppPreference
    }
}
