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

class RedditFragment : BaseFragment<RedditFragmentViewModel, FragmentRedditLayoutBinding>() {

    lateinit var redditRecyclerAdapter: RedditRecyclerAdapter

    override fun getLayoutResource() = R.layout.fragment_reddit_layout

    override fun bindLayoutVariables() {
        super.bindLayoutVariables()

        initRecyclerAdapter()

        initRecyclerView()

        initSwipeRefreshLayout()

        addInitialLoadingStateObserver()

        addPaginationStateObserver()

    }

    private fun initRecyclerAdapter() {
        redditRecyclerAdapter = RedditRecyclerAdapter(context!!)
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
            setColorSchemeResources(R.color.redditColor)

            setOnRefreshListener {
                viewModel.reloadData()

                isRefreshing = false
            }
        }

    }

    private fun addInitialLoadingStateObserver() {

        viewModel.getInitialLoadingState().observe(this, Observer {
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