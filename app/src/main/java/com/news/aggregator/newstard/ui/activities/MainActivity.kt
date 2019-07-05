package com.news.aggregator.newstard.ui.activities

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.news.aggregator.newstard.R
import com.news.aggregator.newstard.databinding.ActivityMainBinding
import com.news.aggregator.newstard.repositories.reddit.RedditRepository
import com.news.aggregator.newstard.ui.viewmodels.MainActivityViewModel

import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject
    lateinit var redditRepositiry: RedditRepository

    // View Model instance
    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViewModel()

        bindLayoutVariables()
    }

    /**
     * Initialize viewmodel instance
     */
    private fun initViewModel(){
        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
    }

    /**
     * Set layout databinding variables
     */
    private fun bindLayoutVariables() {
        // Get binding
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // Set binding variables
        binding.services = viewModel.getServices()
    }


}
