package com.news.aggregator.newstard.ui.fargments

import android.view.View
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.news.aggregator.newstard.R
import com.news.aggregator.newstard.databinding.FragmentRedditLayoutBinding
import com.news.aggregator.newstard.network.NetworkState
import com.news.aggregator.newstard.repositories.reddit.RedditPost
import com.news.aggregator.newstard.ui.adapters.reddit.RedditRecyclerAdapter
import com.news.aggregator.newstard.ui.viewmodels.RedditFragmentViewModel
import javax.inject.Inject

class RedditFragment : BaseFragment<RedditFragmentViewModel, FragmentRedditLayoutBinding>() {

    @Inject
    lateinit var redditRecyclerAdapter: RedditRecyclerAdapter

    override fun getLayoutResource(): Int {
        return R.layout.fragment_reddit_layout
    }

    override fun bindLayoutVariables() {
        super.bindLayoutVariables()

        initRecyclerView()

        initSwipeRefreshLayout()

        addInitialLoadingStateObserver()

        addPaginationStateObserver()

    }

    private fun initRecyclerView() {

        viewModel.getPagedPostData().observe(this, Observer<PagedList<RedditPost>> {
            redditRecyclerAdapter.submitList(it)
        })

        with(layoutBinding.redditLayoutRecyclerView) {
            layoutManager = LinearLayoutManager(context)
            adapter = redditRecyclerAdapter

            setHasFixedSize(true)
        }
    }

    private fun initSwipeRefreshLayout() {
        with(layoutBinding.redditLayoutSwipeRefreshLayout){
            setColorSchemeResources(android.R.color.holo_red_light, android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark, android.R.color.holo_blue_dark)

            setOnRefreshListener {
                isRefreshing = false

                viewModel.reloadData()
            }
        }

    }

    private fun addInitialLoadingStateObserver() {
        viewModel.getInitialLoadingState().observe(this, Observer<NetworkState> {
            when (it!!) {
                NetworkState.LOADING -> {
                    layoutBinding.redditLayoutProgressBar.visibility = View.VISIBLE
                    layoutBinding.redditLayoutSwipeRefreshLayout.visibility = View.GONE
                    layoutBinding.redditLayoutErrorLayout.visibility = View.GONE
                }
                NetworkState.SUCCESS -> {
                    layoutBinding.redditLayoutProgressBar.visibility = View.GONE
                    layoutBinding.redditLayoutSwipeRefreshLayout.visibility = View.VISIBLE
                    layoutBinding.redditLayoutErrorLayout.visibility = View.GONE
                }
                NetworkState.ERROR -> {
                    layoutBinding.redditLayoutProgressBar.visibility = View.GONE
                    layoutBinding.redditLayoutSwipeRefreshLayout.visibility = View.GONE
                    layoutBinding.redditLayoutErrorLayout.visibility = View.VISIBLE
                }
            }
        })
    }

    private fun addPaginationStateObserver() {
        viewModel.getPaginationLoadingSate().observe(this, Observer<NetworkState> {
            redditRecyclerAdapter.setPaginationNetworkState(it)
        })
    }
}