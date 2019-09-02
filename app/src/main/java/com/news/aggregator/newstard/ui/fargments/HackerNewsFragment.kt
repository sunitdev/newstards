package com.news.aggregator.newstard.ui.fargments

import android.view.View
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.news.aggregator.newstard.R
import com.news.aggregator.newstard.databinding.FragmentHackerNewsLayoutBinding
import com.news.aggregator.newstard.network.NetworkState
import com.news.aggregator.newstard.repositories.hackernews.HackerNewsPost
import com.news.aggregator.newstard.ui.adapters.hackernews.HackerNewsRecyclerAdapter
import com.news.aggregator.newstard.ui.viewmodels.HackerNewsViewModel

class HackerNewsFragment: BaseFragment<HackerNewsViewModel, FragmentHackerNewsLayoutBinding>() {

    lateinit var hackerNewsRecyclerAdapter: HackerNewsRecyclerAdapter

    override fun getLayoutResource() = R.layout.fragment_hacker_news_layout

    override fun bindLayoutVariables() {
        super.bindLayoutVariables()

        initRecyclerAdapter()

        initRecyclerView()

        initSwipeRefreshLayout()

        addInitialLoadingStateObserver()

        addPaginationStateObserver()

    }

    private fun initRecyclerAdapter() {
        hackerNewsRecyclerAdapter = HackerNewsRecyclerAdapter(context!!)
    }

    private fun initRecyclerView() {

        viewModel.getPagedPostData().observe(this, Observer<PagedList<HackerNewsPost>> {
            hackerNewsRecyclerAdapter.submitList(it)
        })

        with(layoutBinding.hackerNewsLayoutRecyclerView) {
            layoutManager = LinearLayoutManager(context)
            adapter = hackerNewsRecyclerAdapter

            setHasFixedSize(true)
        }
    }

    private fun initSwipeRefreshLayout() {
        with(layoutBinding.hackerNewsLayoutSwipeRefreshLayout){
            setColorSchemeResources(R.color.hackerNewsColor)

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
                    layoutBinding.hackerNewsLayoutProgressBar.visibility = View.VISIBLE
                    layoutBinding.hackerNewsLayoutSwipeRefreshLayout.visibility = View.GONE
                    layoutBinding.hackerNewsLayoutErrorLayout.visibility = View.GONE
                }
                NetworkState.SUCCESS -> {
                    layoutBinding.hackerNewsLayoutProgressBar.visibility = View.GONE
                    layoutBinding.hackerNewsLayoutSwipeRefreshLayout.visibility = View.VISIBLE
                    layoutBinding.hackerNewsLayoutErrorLayout.visibility = View.GONE
                }
                NetworkState.ERROR -> {
                    layoutBinding.hackerNewsLayoutProgressBar.visibility = View.GONE
                    layoutBinding.hackerNewsLayoutSwipeRefreshLayout.visibility = View.GONE
                    layoutBinding.hackerNewsLayoutErrorLayout.visibility = View.VISIBLE
                }
            }
        })
    }

    private fun addPaginationStateObserver() {
        viewModel.getPaginationLoadingSate().observe(this, Observer<NetworkState> {
            hackerNewsRecyclerAdapter.setPaginationNetworkState(it)
        })
    }
}