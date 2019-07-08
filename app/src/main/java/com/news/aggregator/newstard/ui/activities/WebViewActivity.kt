package com.news.aggregator.newstard.ui.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.news.aggregator.newstard.R
import kotlinx.android.synthetic.main.activity_webview.*


class WebViewActivity : AppCompatActivity(){

    companion object{
        val EXTRA_URL = "EXTRA_URL"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview)

        val url = intent.getStringExtra(EXTRA_URL)
        webView.loadUrl(url)
    }
}
