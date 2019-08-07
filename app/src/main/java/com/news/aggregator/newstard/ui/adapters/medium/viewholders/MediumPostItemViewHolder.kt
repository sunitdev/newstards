package com.news.aggregator.newstard.ui.adapters.medium.viewholders

import android.content.Context
import android.content.Intent
import android.net.Uri
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
                PackageUtils.isPackageInstalled(_context, mediumPackageName) ->
                    _context.startActivity(PackageUtils.getDefaultViewIntent(post.link))
                PackageUtils.isChromeInstalled(_context) ->
                    PackageUtils.getChromeTabIntent(_context, R.color.mediumWebviewColor).launchUrl(_context, Uri.parse(post.link))
                else -> {
                    _context.startActivity(PackageUtils.getWebViewIntent(_context, post.title, post.link, R.color.mediumWebviewColor))
                }
            }
        }
    }

}