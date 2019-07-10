package com.news.aggregator.newstard.ui.adapters

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.PackageManager.GET_ACTIVITIES
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.news.aggregator.newstard.databinding.FragmentRedditListItemLayoutBinding
import com.news.aggregator.newstard.repositories.reddit.RedditPost
import com.news.aggregator.newstard.ui.activities.WebViewActivity
import javax.inject.Inject


class RedditRecyclerAdapter
    @Inject constructor(private val _context: Context):
    PagedListAdapter<RedditPost, RedditRecyclerAdapter.PostViewHolder>(_diffCallback) {

    companion object {
        private val _diffCallback = object:DiffUtil.ItemCallback<RedditPost>() {

            override fun areContentsTheSame(oldItem: RedditPost, newItem: RedditPost): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areItemsTheSame(oldItem: RedditPost, newItem: RedditPost): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val layoutBinding = FragmentRedditListItemLayoutBinding.inflate(LayoutInflater.from(_context), parent,false)

        return PostViewHolder(layoutBinding)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = getItem(position)
        // TODO: Handle null value and show loading layout
        post?.let { holder.bindPost(it) }
    }


    inner class PostViewHolder(private val itemLayoutBinding: FragmentRedditListItemLayoutBinding):
        RecyclerView.ViewHolder(itemLayoutBinding.root) {

        private val packageName = "com.reddit.frontpage"

        fun bindPost(post: RedditPost){
            itemLayoutBinding.post = post
            itemLayoutBinding.root.setOnClickListener{

                val intent: Intent = if (_isRedditInstalled()){
                    _getRedditAppIntent(post)
                } else{
                    _getWebViewIntent(post)
                }

                _context.startActivity(intent)
            }
        }

        private fun _isRedditInstalled(): Boolean{
            try {
                _context.packageManager.getPackageInfo(packageName, GET_ACTIVITIES)
                return true
            }
            catch (e: PackageManager.NameNotFoundException){
                return false
            }
        }

        private fun _getWebViewIntent(post: RedditPost): Intent{
            val intent =  Intent(_context, WebViewActivity::class.java)

            with(intent){
                flags = Intent.FLAG_ACTIVITY_NEW_TASK

                putExtra(WebViewActivity.EXTRA_URL, post.link)
                putExtra(WebViewActivity.EXTRA_TITLE, post.title)
            }

            return intent
        }

        private fun _getRedditAppIntent(post: RedditPost): Intent{
            val intent = Intent(Intent.ACTION_VIEW)

            with(intent){
                data = Uri.parse(post.link)
                flags = Intent.FLAG_ACTIVITY_NEW_TASK

                setPackage(packageName)

            }
            return intent
        }
    }
}

