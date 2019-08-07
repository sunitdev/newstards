package com.news.aggregator.newstard.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatDelegate
import androidx.viewpager.widget.ViewPager
import com.news.aggregator.newstard.R
import com.news.aggregator.newstard.databinding.ActivityMainBinding
import com.news.aggregator.newstard.repositories.services.NewsService
import com.news.aggregator.newstard.services.preferences.AppPreference
import com.news.aggregator.newstard.ui.activities.base.BaseActivity
import com.news.aggregator.newstard.ui.adapters.MainActivityFragmentAdapter
import com.news.aggregator.newstard.ui.viewmodels.MainActivityViewModel

class MainActivity : BaseActivity<MainActivityViewModel, ActivityMainBinding>() {

    private var _lastDayNightMode = AppCompatDelegate.getDefaultNightMode()

    override fun getLayoutResource() = R.layout.activity_main

    override fun handleOnCreate() {
        super.handleOnCreate()

        setupViewPager()

        setUpIconBar()

        setUpEventHandlers()
    }

    override fun bindLayoutVariables() {
        super.bindLayoutVariables()

        layoutBinding.services = viewModel.getServices()
    }

    override fun onRestart() {
        super.onRestart()

        // If night mode has changed then recreate the activity
        if(AppCompatDelegate.getDefaultNightMode() != _lastDayNightMode){
            recreate()
        }
    }

    private fun setupViewPager(){
        layoutBinding.mainActivityViewPage.adapter = MainActivityFragmentAdapter(supportFragmentManager)

        layoutBinding.mainActivityViewPage.addOnPageChangeListener(object: ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {}
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

            override fun onPageSelected(position: Int) {
                layoutBinding.mainActivityIconBar.selectedNewsService = viewModel.getServices()[position]
            }
        })
    }

    private fun setUpIconBar(){
        // Set selected icon for iconbar
        val services = viewModel.getServices()
        // Assuming services are arranged ascending according to the ids.
        layoutBinding.mainActivityIconBar.selectedNewsService = services[AppPreference.lastActiveServiceID - 1]
        layoutBinding.mainActivityViewPage.currentItem = AppPreference.lastActiveServiceID - 1

        layoutBinding.mainActivityIconBar.setOnServiceIconClickListener{ handelOnServiceIconClicked(it) }

    }

    private fun setUpEventHandlers(){

        // Settings Button on click
        layoutBinding.mainActivityButtonSettings.setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
        }
    }

    private fun handelOnServiceIconClicked(service: NewsService){
        // Update viewpager
        layoutBinding.mainActivityViewPage.currentItem = service.id - 1

        // Save in preference
        AppPreference.lastActiveServiceID = service.id
    }

}

