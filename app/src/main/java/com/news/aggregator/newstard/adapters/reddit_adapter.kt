//package com.news.aggregator.newstard.adapters
//
//import android.content.Context
//import android.support.v7.widget.RecyclerView
//
//
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import kotlinx.android.synthetic.main.reddit_post.view.*
//import android.content.Intent
//import android.net.Uri
//import android.os.Bundle
//import com.news.aggregator.newstard.R
//import com.news.aggregator.newstard.services.apis.reddit.RedditJsonResponseModel.RedditPost
//
////
////class RedditAdapter(val context: Context, private val news: List<RedditPost>) : RecyclerView.Adapter<RedditAdapter.MyViewHolder>() {
////
////    companion object {
////        val TAG: String = RedditAdapter::class.java.simpleName
////    }
////
////    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
////        val view = LayoutInflater.from(context).inflate(R.layout.reddit_post, parent, false)
////        return MyViewHolder(view)
////    }
////
////    override fun getItemCount(): Int {
////        return news.size
////    }
////
////    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
////        val news = news[position]
////        holder.setData(news, position)
////    }
////
////    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
////
////        var redditPost: RedditPost? = null
////        var currentPosition: Int = 0
////
////        init {
////
////            itemView.postTitle.setOnClickListener {
////                redditPost?.let {
////                    openNewTabWindow(redditPost!!.link, context)
////                }
////            }
////
////            itemView.imgShare.setOnClickListener{
////                redditPost?.let {
////                    val message: String = "Newstard link : \n" + redditPost!!.link
////
////                    val intent = Intent()
////                    intent.action = Intent.ACTION_SEND
////                    intent.putExtra(Intent.EXTRA_TEXT, message)
////                    intent.type = "text/plain"
////
////                    context.startActivity(Intent.createChooser(intent, "Please select app: "))
////                }
////            }
////        }
////
////        fun setData(post: RedditPost?, pos: Int) {
////            post?.let {
////                itemView.postTitle.text = post.title
////                itemView.postUpvotes.text = post.upvotes.toString()
////            }
////
////            this.redditPost = post
////            this.currentPosition = pos
////        }
////
////        fun openNewTabWindow(urls: String, context : Context) {
////            val uris = Uri.parse(urls)
////            val intents = Intent(Intent.ACTION_VIEW, uris)
////            val b = Bundle()
////            b.putBoolean("new_window", true)
////            intents.putExtras(b)
////            context.startActivity(intents)
////        }
////    }
////}
