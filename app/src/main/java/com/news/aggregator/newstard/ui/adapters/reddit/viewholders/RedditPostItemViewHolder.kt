package com.news.aggregator.newstard.ui.adapters.reddit.viewholders

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.news.aggregator.newstard.R
import com.news.aggregator.newstard.databinding.FragmentRedditListItemLayoutBinding
import com.news.aggregator.newstard.repositories.reddit.RedditPost
import com.news.aggregator.newstard.ui.activities.WebViewActivity


class RedditPostItemViewHolder(
    private val _itemLayoutBinding: FragmentRedditListItemLayoutBinding,
    private val _context: Context) :
    RecyclerView.ViewHolder(_itemLayoutBinding.root) {

    private val packageName = "com.reddit.frontpage"

    fun bindPost(post: RedditPost) {
        _itemLayoutBinding.post = post
        _itemLayoutBinding.root.setOnClickListener {

            val intent: Intent = if (isRedditInstalled()) {
                getRedditAppIntent(post)
            } else {
                getWebViewIntent(post)
            }

            _context.startActivity(intent)
        }
    }

    private fun isRedditInstalled(): Boolean {
        try {
            _context.packageManager.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES)
            return true
        } catch (e: PackageManager.NameNotFoundException) {
            return false
        }
    }

    private fun getWebViewIntent(post: RedditPost): Intent {
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

    private fun getRedditAppIntent(post: RedditPost): Intent {
        val intent = Intent(Intent.ACTION_VIEW)

        with(intent) {
            data = Uri.parse(post.link)
            flags = Intent.FLAG_ACTIVITY_NEW_TASK

            setPackage(packageName)

        }
        return intent
    }
}