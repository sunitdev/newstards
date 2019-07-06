package com.news.aggregator.newstard.ui.activities

import androidx.viewpager.widget.ViewPager
import com.news.aggregator.newstard.R
import com.news.aggregator.newstard.databinding.ActivityMainBinding
import com.news.aggregator.newstard.ui.adapters.MainActivityFragmentAdapter
import com.news.aggregator.newstard.ui.viewmodels.MainActivityViewModel
import com.news.aggregator.newstard.ui.views.iconbarview.IconBarView

class MainActivity : BaseActivity<MainActivityViewModel, ActivityMainBinding>() {

    override fun getLayoutResource(): Int {
        return R.layout.activity_main
    }

    override fun bindLayoutVariables() {
        super.bindLayoutVariables()

        val services = viewModel.getServices()

        layoutBinding.services = services

        _setupViewPager()

        // Set selected icon for iconbar
        // TODO: Replace this with adapter
        findViewById<IconBarView>(R.id.mainActivityIconBar).selectedNewsService = services[0]
    }

    private fun _setupViewPager(){
        val viewPager = layoutBinding.root.findViewById<ViewPager>(R.id.mainActivityViewPage)
        viewPager.adapter = MainActivityFragmentAdapter(supportFragmentManager)

    }

}

