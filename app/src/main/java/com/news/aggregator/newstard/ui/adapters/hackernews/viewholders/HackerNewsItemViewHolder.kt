package com.news.aggregator.newstard.ui.adapters.hackernews.viewholders

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.news.aggregator.newstard.R
import com.news.aggregator.newstard.databinding.FragmentHackerNewsListItemLayoutBinding
import com.news.aggregator.newstard.repositories.hackernews.HackerNewsPost
import com.news.aggregator.newstard.ui.activities.WebViewActivity
import com.news.aggregator.newstard.utils.PackageUtils

class HackerNewsItemViewHolder(private val _layoutBinding: FragmentHackerNewsListItemLayoutBinding,
                               private val _context: Context):
    RecyclerView.ViewHolder(_layoutBinding.root){

    fun bindPost(post: HackerNewsPost) {
        _layoutBinding.post = post
        _layoutBinding.root.setOnClickListener {

            if(post.link == null) {
                return@setOnClickListener
            }

            when {
                PackageUtils.isChromeInstalled(_context) ->
                    PackageUtils.getChromeTabIntent(_context, R.color.hackerNewsWebviewColor).launchUrl(_context, Uri.parse(post.link))
                else -> {
                    _context.startActivity(PackageUtils.getWebViewIntent(_context, post.title, post.link!!, R.color.hackerNewsWebviewColor))
                }
            }
        }
    }


}