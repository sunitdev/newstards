package com.news.aggregator.newstard.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton


/**
 * Create a generic mapping for viewmodel and provider to provide dagger inject for viewmodel
 */
@Singleton
class ViewModelFactory @Inject
    constructor(private val _viewModelProviderMapping: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>) :
    ViewModelProvider.Factory {


    /**
     * Return viewmodel instance from provider
     */
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        // Check if provider already exists
        var provider = _viewModelProviderMapping.get(modelClass)

        // Check provider from subclass
        if (provider == null) {

            for (entry in _viewModelProviderMapping.entries) {
                if (modelClass.isAssignableFrom(entry.key)) {
                    provider = entry.value
                }
            }
        }

        // If provider is not found then raise exception
        if (provider == null) {
            throw IllegalArgumentException("Invalid model class $modelClass. Did you forgot to add ViewModel binding in ViewModelModule?")
        }

        //Try creating viewmodel instance from provider
        try {
            return provider.get() as T
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}