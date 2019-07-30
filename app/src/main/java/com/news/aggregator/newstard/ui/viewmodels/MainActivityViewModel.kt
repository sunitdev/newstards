package com.news.aggregator.newstard.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.news.aggregator.newstard.repositories.preferences.PreferenceRepository
import com.news.aggregator.newstard.repositories.services.ServiceRepository
import javax.inject.Inject

class MainActivityViewModel
    @Inject constructor(private val serviceRepository: ServiceRepository,
                        private val preferenceRepository: PreferenceRepository): ViewModel() {

    /**
     * Return currently active services
     */
    fun getServices() = serviceRepository.getServices()

    /**
     * Return App Preference
     */
    fun getAppPreference() = preferenceRepository.getAppPreference()
}
