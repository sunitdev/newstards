package com.news.aggregator.newstard.ui.adapters.medium.viewholders

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.news.aggregator.newstard.R
import com.news.aggregator.newstard.databinding.FragmentMediumListItemLayoutBinding
import com.news.aggregator.newstard.repositories.medium.MediumPost
import com.news.aggregator.newstard.ui.activities.WebViewActivity
import com.news.aggregator.newstard.utils.PackageUtils

class MediumPostItemViewHolder(
    private val _layoutBinding: FragmentMediumListItemLayoutBinding,
    private val _context: Context):RecyclerView.ViewHolder(_layoutBinding.root){

    private val mediumPackageName = "com.medium.reader"

    fun bindPost(post: MediumPost){
        _layoutBinding.post = post

        _layoutBinding.root.setOnClickListener {

            when {
                PackageUtils.isPackageInstalled(_context, mediumPackageName) -> {
                    val intent = PackageUtils.getPackageIntent(mediumPackageName, post.link)
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

    private fun getWebViewIntent(post: MediumPost): Intent {
        val intent = Intent(_context, WebViewActivity::class.java)

        with(intent) {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK

            putExtra(WebViewActivity.EXTRA_URL, post.link)
            putExtra(WebViewActivity.EXTRA_TITLE, post.title)
            putExtra(
                WebViewActivity.EXTRA_HEADER_BACKGROUND,
                ContextCompat.getColor(_context, R.color.mediumWebviewColor)
            )
        }

        return intent
    }

    private fun startCustomTabActivity(context: Context, url: String) {
        val builder = CustomTabsIntent.Builder()
            .setToolbarColor(ContextCompat.getColor(context, R.color.mediumWebviewColor))
            .addDefaultShareMenuItem()
            .setShowTitle(true)
            .setStartAnimations(context, android.R.anim.slide_in_left, android.R.anim.slide_in_left)
            .setExitAnimations(context, android.R.anim.slide_out_right, android.R.anim.slide_out_right)

        val customTabsIntent = builder.build()

        customTabsIntent.intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        customTabsIntent.launchUrl(context, Uri.parse(url))
    }
}