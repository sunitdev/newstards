package com.news.aggregator.newstard.ui.adapters.dev_to.viewholders

import android.content.Context
import android.net.Uri
import androidx.recyclerview.widget.RecyclerView
import com.news.aggregator.newstard.R
import com.news.aggregator.newstard.databinding.FragmentDevToListItemLayoutBinding
import com.news.aggregator.newstard.repositories.dev_to.DevToPost
import com.news.aggregator.newstard.utils.PackageUtils

class DevToPostViewHolder(private val _layoutBinding: FragmentDevToListItemLayoutBinding,
                          private val _context: Context):
        RecyclerView.ViewHolder(_layoutBinding.root){

    fun bindPost(post: DevToPost){
        _layoutBinding.post = post

        _layoutBinding.root.setOnClickListener {

            when {
                PackageUtils.isChromeInstalled(_context) ->
                    PackageUtils.getChromeTabIntent(_context, R.color.devToWebViewColor).launchUrl(_context, Uri.parse(post.link))
                else -> {
                    _context.startActivity(PackageUtils.getWebViewIntent(_context, post.title, post.link, R.color.devToWebViewColor))
                }
            }
        }
    }


}