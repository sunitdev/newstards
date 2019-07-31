package com.news.aggregator.newstard.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.news.aggregator.newstard.repositories.preferences.PreferenceRepository
import javax.inject.Inject

class SettingsActivityViewModel
    @Inject constructor(private val preferenceRepository: PreferenceRepository) : ViewModel() {
}
