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
import com.news.aggregator.newstard.R
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

        private val packageName = _context.resources.getString(R.string.package_name_reddit)

        fun bindPost(post: RedditPost){
            itemLayoutBinding.post = post
            itemLayoutBinding.redditListPostTitle.setOnClickListener{

                val intent: Intent = if (_isRedditInstalled()){
                    _getRedditAppIntent(post.link)
                } else{
                    _getWebViewIntent(post.link)
                }

                _context.startActivity(intent)
            }
        }

        private fun _isRedditInstalled(): Boolean{
            val packageManager = _context.packageManager

            var isPackageInstalled: Boolean

            try{
                packageManager.getPackageInfo(packageName, GET_ACTIVITIES)
                isPackageInstalled = true
            }
            catch (e: PackageManager.NameNotFoundException){
                isPackageInstalled = false
            }

            return isPackageInstalled
        }

        private fun _getWebViewIntent(url:String): Intent{
            val intent =  Intent(_context, WebViewActivity::class.java)
            intent.putExtra(WebViewActivity.EXTRA_URL, url)
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            return intent
        }

        private fun _getRedditAppIntent(url:String): Intent{
            val intent = Intent(Intent.ACTION_VIEW)
            intent.setPackage(packageName)
            intent.data = Uri.parse(url)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            return intent
        }
    }
}
