package com.news.aggregator.newstard.ui.fargments

import android.view.View
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.news.aggregator.newstard.R
import com.news.aggregator.newstard.databinding.FragmentDevToLayoutBinding
import com.news.aggregator.newstard.network.NetworkState
import com.news.aggregator.newstard.repositories.dev_to.DevToPost
import com.news.aggregator.newstard.ui.adapters.dev_to.DevToRecyclerAdapter
import com.news.aggregator.newstard.ui.viewmodels.DevToFragmentViewModel

class DevToFragment: BaseFragment<DevToFragmentViewModel, FragmentDevToLayoutBinding>() {

    lateinit var devToRecyclerAdapter: DevToRecyclerAdapter

    override fun getLayoutResource() = R.layout.fragment_dev_to_layout

    override fun bindLayoutVariables() {
        super.bindLayoutVariables()

        initRecyclerAdapter()

        initRecyclerView()

        initSwipeRefreshLayout()

        addInitialLoadingStateObserver()

        addPaginationStateObserver()

    }

    private fun initRecyclerAdapter() {
        devToRecyclerAdapter = DevToRecyclerAdapter(context!!)
    }

    private fun initRecyclerView() {

        viewModel.getPagedPostData().observe(this, Observer<PagedList<DevToPost>> {
            devToRecyclerAdapter.submitList(it)
        })

        with(layoutBinding.devToLayoutRecyclerView) {
            layoutManager = LinearLayoutManager(context)
            adapter = devToRecyclerAdapter

            setHasFixedSize(true)
        }
    }

    private fun initSwipeRefreshLayout() {
        with(layoutBinding.devToLayoutSwipeRefreshLayout){
            setColorSchemeResources(R.color.devToColor)

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
                    layoutBinding.devToLayoutProgressBar.visibility = View.VISIBLE
                    layoutBinding.devToLayoutSwipeRefreshLayout.visibility = View.GONE
                    layoutBinding.devToLayoutErrorLayout.visibility = View.GONE
                }
                NetworkState.SUCCESS -> {
                    layoutBinding.devToLayoutProgressBar.visibility = View.GONE
                    layoutBinding.devToLayoutSwipeRefreshLayout.visibility = View.VISIBLE
                    layoutBinding.devToLayoutErrorLayout.visibility = View.GONE
                }
                NetworkState.ERROR -> {
                    layoutBinding.devToLayoutProgressBar.visibility = View.GONE
                    layoutBinding.devToLayoutSwipeRefreshLayout.visibility = View.GONE
                    layoutBinding.devToLayoutErrorLayout.visibility = View.VISIBLE
                }
            }
        })
    }

    private fun addPaginationStateObserver() {
        viewModel.getPaginationLoadingSate().observe(this, Observer<NetworkState> {
            devToRecyclerAdapter.setPaginationNetworkState(it)
        })
    }
}