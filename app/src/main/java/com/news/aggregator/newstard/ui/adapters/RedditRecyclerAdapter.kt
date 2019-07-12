package com.news.aggregator.newstard.ui.adapters

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.PackageManager.GET_ACTIVITIES
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.news.aggregator.newstard.R
import com.news.aggregator.newstard.databinding.FragmentRedditListErrorLayoutBinding
import com.news.aggregator.newstard.databinding.FragmentRedditListItemLayoutBinding
import com.news.aggregator.newstard.databinding.FragmentRedditListLoadingLayoutBinding
import com.news.aggregator.newstard.network.NetworkState
import com.news.aggregator.newstard.repositories.reddit.RedditPost
import com.news.aggregator.newstard.ui.activities.WebViewActivity
import javax.inject.Inject


class RedditRecyclerAdapter
@Inject constructor(private val _context: Context) :
    PagedListAdapter<RedditPost, RecyclerView.ViewHolder>(_diffCallback) {

    private var currentPaginationState: NetworkState? = null
    private var isExtraRowAdded = false

    companion object {
        private val _diffCallback = object : DiffUtil.ItemCallback<RedditPost>() {

            override fun areContentsTheSame(oldItem: RedditPost, newItem: RedditPost): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areItemsTheSame(oldItem: RedditPost, newItem: RedditPost): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(_context)

        return when(viewType){
            R.layout.fragment_reddit_list_loading_layout -> {
                val layoutBinding = FragmentRedditListLoadingLayoutBinding.inflate(inflater, parent, false)
                LoadingViewHolder(layoutBinding)
            }
            R.layout.fragment_reddit_list_error_layout -> {
                val layoutBinding = FragmentRedditListErrorLayoutBinding.inflate(inflater, parent, false)
                ErrorViewHolder(layoutBinding)
            }
            else -> {
                val layoutBinding = FragmentRedditListItemLayoutBinding.inflate(inflater, parent, false)
                PostViewHolder(layoutBinding)
            }
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when(getItemViewType(position)){
            R.layout.fragment_reddit_list_item_layout -> {
                val post = getItem(position)
                post?.let { (holder as PostViewHolder).bindPost(it) }
            }
        }

    }

    fun setPaginationNetworkState(networkState: NetworkState) {
        currentPaginationState = networkState

        if (currentPaginationState!!.state == NetworkState.States.SUCCESS && isExtraRowAdded) {
            isExtraRowAdded = false
            notifyItemRemoved(itemCount - 1)
        } else {
            if (isExtraRowAdded) {
                notifyItemChanged(itemCount - 1)
            } else {
                isExtraRowAdded = true
                notifyItemInserted(itemCount)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        if(isExtraRowAdded && position == itemCount - 1){
            if(currentPaginationState!!.state == NetworkState.States.LOADING){
                return R.layout.fragment_reddit_list_loading_layout
            }else {
                return R.layout.fragment_reddit_list_error_layout
            }
        }else{
            return R.layout.fragment_reddit_list_item_layout
        }
    }


    inner class LoadingViewHolder(layoutBinding: FragmentRedditListLoadingLayoutBinding):
        RecyclerView.ViewHolder(layoutBinding.root)

    inner class ErrorViewHolder(layoutBinding: FragmentRedditListErrorLayoutBinding):
        RecyclerView.ViewHolder(layoutBinding.root)

    inner class PostViewHolder(private val itemLayoutBinding: FragmentRedditListItemLayoutBinding) :
        RecyclerView.ViewHolder(itemLayoutBinding.root) {

        private val packageName = "com.reddit.frontpage"

        fun bindPost(post: RedditPost) {
            itemLayoutBinding.post = post
            itemLayoutBinding.root.setOnClickListener {

                val intent: Intent = if (_isRedditInstalled()) {
                    _getRedditAppIntent(post)
                } else {
                    _getWebViewIntent(post)
                }

                _context.startActivity(intent)
            }
        }

        private fun _isRedditInstalled(): Boolean {
            try {
                _context.packageManager.getPackageInfo(packageName, GET_ACTIVITIES)
                return true
            } catch (e: PackageManager.NameNotFoundException) {
                return false
            }
        }

        private fun _getWebViewIntent(post: RedditPost): Intent {
            val intent = Intent(_context, WebViewActivity::class.java)

            with(intent) {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK

                putExtra(WebViewActivity.EXTRA_URL, post.link)
                putExtra(WebViewActivity.EXTRA_TITLE, post.title)
                putExtra(
                    WebViewActivity.EXTRA_HEADER_BACKGROUND,
                    ContextCompat.getColor(_context, R.color.redditSecondaryColor)
                )
            }

            return intent
        }

        private fun _getRedditAppIntent(post: RedditPost): Intent {
            val intent = Intent(Intent.ACTION_VIEW)

            with(intent) {
                data = Uri.parse(post.link)
                flags = Intent.FLAG_ACTIVITY_NEW_TASK

                setPackage(packageName)

            }
            return intent
        }
    }
}

