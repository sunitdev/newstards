package com.news.aggregator.newstard.ui.fargments

import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.news.aggregator.newstard.R
import com.news.aggregator.newstard.databinding.FragmentRedditLayoutBinding
import com.news.aggregator.newstard.repositories.reddit.RedditPost
import com.news.aggregator.newstard.ui.adapters.RedditRecyclerAdapter
import com.news.aggregator.newstard.ui.viewmodels.RedditFragmentViewModel
import javax.inject.Inject

class RedditFragment: BaseFragment<RedditFragmentViewModel, FragmentRedditLayoutBinding>() {

    @Inject
    lateinit var redditRecyclerAdapter: RedditRecyclerAdapter

    override fun getLayoutResource(): Int {
        return R.layout.fragment_reddit_layout
    }

    override fun bindLayoutVariables() {
        super.bindLayoutVariables()

        _initRecyclerView()
    }

    private fun _initRecyclerView(){

        viewModel.getPagedPostData().observe(this, object:Observer<PagedList<RedditPost>>{
            override fun onChanged(posts: PagedList<RedditPost>?) {
                redditRecyclerAdapter.submitList(posts)
            }
        })

        with(layoutBinding.redditLayoutRecyclerView){
            layoutManager = LinearLayoutManager(context)
            adapter = redditRecyclerAdapter

            setHasFixedSize(true)
        }

    }
}