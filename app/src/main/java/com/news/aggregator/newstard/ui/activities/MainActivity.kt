package com.news.aggregator.newstard.ui.activities

import androidx.lifecycle.Observer
import com.news.aggregator.newstard.R
import com.news.aggregator.newstard.databinding.ActivityMainBinding
import com.news.aggregator.newstard.repositories.services.NewsService
import com.news.aggregator.newstard.ui.viewmodels.MainActivityViewModel

class MainActivity : BaseActivity<MainActivityViewModel, ActivityMainBinding>() {

    private val services: ArrayList<NewsService> = ArrayList()

    override fun getLayoutResource(): Int {
        return R.layout.activity_main
    }

    override fun bindLayoutVariables() {
        super.bindLayoutVariables()

        viewModel.getServices().observe(this, Observer {
            services.add(it)
            layoutBinding.invalidateAll()
        })

        layoutBinding.services = services
    }

}

