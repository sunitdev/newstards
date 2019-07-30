package com.news.aggregator.newstard.ui.activities

import android.content.Intent
import com.news.aggregator.newstard.R
import com.news.aggregator.newstard.databinding.ActivityMainBinding
import com.news.aggregator.newstard.ui.activities.base.BaseActivity
import com.news.aggregator.newstard.ui.adapters.MainActivityFragmentAdapter
import com.news.aggregator.newstard.ui.viewmodels.MainActivityViewModel
import com.news.aggregator.newstard.ui.views.iconbarview.IconBarView

class MainActivity : BaseActivity<MainActivityViewModel, ActivityMainBinding>() {

    override fun getLayoutResource() = R.layout.activity_main

    override fun bindLayoutVariables() {
        super.bindLayoutVariables()

        layoutBinding.services = viewModel.getServices()
    }

    override fun handleOnCreate() {
        super.handleOnCreate()

        setupViewPager()

        setUpIconBar()

        setUpEventHandlers()

    }

    private fun setupViewPager(){
        layoutBinding.mainActivityViewPage.adapter = MainActivityFragmentAdapter(supportFragmentManager)
    }

    private fun setUpIconBar(){
        // Set selected icon for iconbar
        // TODO: Replace this with adapter
        findViewById<IconBarView>(R.id.mainActivityIconBar).selectedNewsService = viewModel.getServices()[0]
    }

    private fun setUpEventHandlers(){

        // Settings Button on click
        layoutBinding.mainActivityButtonSettings.setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
        }

    }

}

