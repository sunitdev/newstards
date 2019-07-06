package com.news.aggregator.newstard.ui.activities

import com.news.aggregator.newstard.R
import com.news.aggregator.newstard.databinding.ActivityMainBinding
import com.news.aggregator.newstard.ui.viewmodels.MainActivityViewModel

class MainActivity : BaseActivity<MainActivityViewModel, ActivityMainBinding>() {

    override fun getLayoutResource(): Int {
        return R.layout.activity_main
    }

    override fun bindLayoutVariables() {
        super.bindLayoutVariables()

        layoutBinding.services = viewModel.getServices()
    }

}

