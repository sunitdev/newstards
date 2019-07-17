package com.news.aggregator.newstard.ui.adapters.reddit.viewholders

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.news.aggregator.newstard.R
import com.news.aggregator.newstard.databinding.FragmentRedditListItemLayoutBinding
import com.news.aggregator.newstard.repositories.reddit.RedditPost
import com.news.aggregator.newstard.ui.activities.WebViewActivity
import com.news.aggregator.newstard.utils.PackageUtils
import androidx.browser.customtabs.CustomTabsIntent


class RedditPostItemViewHolder(
    private val _itemLayoutBinding: FragmentRedditListItemLayoutBinding,
    private val _context: Context) :
    RecyclerView.ViewHolder(_itemLayoutBinding.root) {

    private val redditPackageName = "com.reddit.frontpage"

    fun bindPost(post: RedditPost) {
        _itemLayoutBinding.post = post
        _itemLayoutBinding.root.setOnClickListener {

            when {
                PackageUtils.isPackageInstalled(_context, redditPackageName) -> {
                    val intent = PackageUtils.getPackageIntent(redditPackageName, post.link)
                    _context.startActivity(intent)
                }
                PackageUtils.isChromeInstalled(_context) -> startCustomTabActivity(_context, post.link)
                else -> {
                    val intent = getWebViewIntent(post)
                    _context.startActivity(intent)
                }
            }
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

    private fun startCustomTabActivity(context: Context, url: String) {
        val builder = CustomTabsIntent.Builder()
            .setToolbarColor(ContextCompat.getColor(context, R.color.redditSecondaryColor))
            .addDefaultShareMenuItem()
            .setShowTitle(true)
            .setStartAnimations(context, android.R.anim.slide_in_left, android.R.anim.slide_in_left)
            .setExitAnimations(context, android.R.anim.slide_out_right, android.R.anim.slide_out_right)

        val customTabsIntent = builder.build()

        customTabsIntent.intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        customTabsIntent.launchUrl(context, Uri.parse(url))
    }
}
