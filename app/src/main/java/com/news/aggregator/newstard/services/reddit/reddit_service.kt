package com.news.aggregator.newstard.services.reddit

import android.util.Log
import io.reactivex.Observable

data class RedditPost(var title: String, var link: String, var upvotes: Int)

class RedditService{

    fun get_observable() : Observable<List<RedditPost>>{
        return apiService().get_news_list().map(this::convert_api_to_model)
    }

    fun convert_api_to_model(class_object : RedditResponseModel.ResponseData): List<RedditPost>{
        val posts = mutableListOf<RedditPost>()
        
        for (child_data in class_object.data.children){
            Log.d("news_list", child_data.data.title + "" + child_data.data.url)
            posts.add(
                RedditPost(
                    child_data.data.title,
                    child_data.data.url,
                    child_data.data.ups
                )
            )
        }
        return posts
    }
}
