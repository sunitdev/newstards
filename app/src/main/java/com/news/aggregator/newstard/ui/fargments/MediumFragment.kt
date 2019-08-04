package com.news.aggregator.newstard.ui.fargments

import android.view.View
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.news.aggregator.newstard.R
import com.news.aggregator.newstard.databinding.FragmentMediumLayoutBinding
import com.news.aggregator.newstard.network.NetworkState
import com.news.aggregator.newstard.repositories.medium.MediumPost
import com.news.aggregator.newstard.ui.adapters.medium.MediumRecyclerAdapter
import com.news.aggregator.newstard.ui.viewmodels.MediumFragmentViewModel

class MediumFragment: BaseFragment<MediumFragmentViewModel, FragmentMediumLayoutBinding>() {

    private lateinit var mediumRecyclerAdapter: MediumRecyclerAdapter

    override fun getLayoutResource() = R.layout.fragment_medium_layout

    override fun bindLayoutVariables() {
        super.bindLayoutVariables()

        initRecyclerAdapter()

        initRecyclerView()

        initSwipeRefreshLayout()

        addInitialLoadingStateObserver()

        addPaginationStateObserver()
    }

    private fun initRecyclerAdapter(){
        mediumRecyclerAdapter = MediumRecyclerAdapter(context!!)
    }

    private fun initRecyclerView() {

        viewModel.getPagedPostData().observe(this, Observer<PagedList<MediumPost>> {
            mediumRecyclerAdapter.submitList(it)
        })

        with(layoutBinding.mediumLayoutRecyclerView) {
            layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context)
            adapter = mediumRecyclerAdapter

            setHasFixedSize(true)
        }
    }

    private fun initSwipeRefreshLayout() {
        with(layoutBinding.mediumLayoutSwipeRefreshLayout){
            setColorSchemeResources(R.color.mediumColor)

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
                    layoutBinding.mediumLayoutProgressBar.visibility = View.VISIBLE
                    layoutBinding.mediumLayoutSwipeRefreshLayout.visibility = View.GONE
                    layoutBinding.mediumLayoutErrorLayout.visibility = View.GONE
                }
                NetworkState.SUCCESS -> {
                    layoutBinding.mediumLayoutProgressBar.visibility = View.GONE
                    layoutBinding.mediumLayoutSwipeRefreshLayout.visibility = View.VISIBLE
                    layoutBinding.mediumLayoutErrorLayout.visibility = View.GONE
                }
                NetworkState.ERROR -> {
                    layoutBinding.mediumLayoutProgressBar.visibility = View.GONE
                    layoutBinding.mediumLayoutSwipeRefreshLayout.visibility = View.GONE
                    layoutBinding.mediumLayoutErrorLayout.visibility = View.VISIBLE
                }
            }
        })
    }

    private fun addPaginationStateObserver() {
        viewModel.getPaginationLoadingSate().observe(this, Observer<NetworkState> {
            mediumRecyclerAdapter.setPaginationNetworkState(it)
        })
    }
}