package com.news.aggregator.newstard.ui.adapters.reddit.viewholders

import android.content.Context
import android.net.Uri
import androidx.recyclerview.widget.RecyclerView
import com.news.aggregator.newstard.R
import com.news.aggregator.newstard.databinding.FragmentRedditListItemLayoutBinding
import com.news.aggregator.newstard.repositories.reddit.RedditPost
import com.news.aggregator.newstard.utils.PackageUtils


class RedditPostItemViewHolder(
    private val _itemLayoutBinding: FragmentRedditListItemLayoutBinding,
    private val _context: Context) :
    RecyclerView.ViewHolder(_itemLayoutBinding.root) {
    
    fun bindPost(post: RedditPost) {
        _itemLayoutBinding.post = post
        _itemLayoutBinding.root.setOnClickListener {

            when {
                PackageUtils.isChromeInstalled(_context) ->
                    PackageUtils.getChromeTabIntent(_context, R.color.redditWebviewColor).launchUrl(_context, Uri.parse(post.link))
                else -> {
                    _context.startActivity(PackageUtils.getWebViewIntent(_context, post.title, post.link, R.color.redditWebviewColor))
                }
            }
        }
    }


}
