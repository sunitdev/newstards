package com.news.aggregator.newstard.ui.activities

import android.os.Bundle
import android.view.MenuItem
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.PreferenceFragmentCompat
import com.news.aggregator.newstard.R
import com.news.aggregator.newstard.databinding.ActivitySettingsBinding
import com.news.aggregator.newstard.ui.activities.base.BaseActivity
import com.news.aggregator.newstard.ui.viewmodels.SettingsActivityViewModel

class SettingsActivity: BaseActivity<SettingsActivityViewModel, ActivitySettingsBinding>() {

    override fun getLayoutResource() = R.layout.activity_settings

    override fun handleOnCreate() {
        super.handleOnCreate()

        setUpToolbar()

        setUpSettingFragment()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        return when(item!!.itemId){
            android.R.id.home -> { finish(); true }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setUpToolbar(){

        setSupportActionBar(layoutBinding.settingsActivityToolbar)

        with(supportActionBar!!){
            title = getString(R.string.preference_app_activity_title)

            setDisplayHomeAsUpEnabled(true)
        }

    }

    private fun setUpSettingFragment(){
        supportFragmentManager
            .beginTransaction()
            .replace(layoutBinding.settingsActivityContainer.id, MainPreferenceFragment())
            .commit()
    }

    class MainPreferenceFragment: PreferenceFragmentCompat() {

        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setupPreferenceResource(rootKey)

            setupEventHandlers()
        }

        private fun setupPreferenceResource(rootKey: String?){
            preferenceManager.sharedPreferencesName = getString(R.string.preference_app_filename)

            setPreferencesFromResource(R.xml.pref_app, rootKey)
        }

        private fun setupEventHandlers(){

            // Dark theme settings
            findPreference(getString(R.string.preference_app_key_is_dark_theme))
                .setOnPreferenceChangeListener { _, newValue ->
                    if(newValue as Boolean){
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    }else{
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                    }

                    (activity as AppCompatActivity).delegate.applyDayNight()

                    true
                }
        }
    }
}