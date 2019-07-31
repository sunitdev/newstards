package com.news.aggregator.newstard.ui.activities

import android.os.Bundle
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.news.aggregator.newstard.R
import com.news.aggregator.newstard.databinding.ActivityWebviewBinding


class WebViewActivity : AppCompatActivity(){

    private lateinit var layoutBinding: ActivityWebviewBinding

    companion object{
        const val EXTRA_URL = "EXTRA_URL"
        const val EXTRA_TITLE = "EXTRA_TITLE"
        const val EXTRA_HEADER_BACKGROUND = "EXTRA_HEADER_BACKGROUND"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initLayout()

        initToolBar()

        initEventHandlers()

        initWebView()
    }

    private fun initLayout(){
        layoutBinding = DataBindingUtil.setContentView(this, R.layout.activity_webview)

        with(layoutBinding){
            title = getTitleFromIntent()
            headerBackgroundColorResource = getHeaderBackgroundFromIntent()
        }
    }

    private fun initToolBar(){
        setSupportActionBar(layoutBinding.webViewActivityToolbar)
    }

    private fun initEventHandlers(){
        // Close button event handler
        layoutBinding.webViewActivityCloseIcon.setOnClickListener { finish() }
    }

    private fun initWebView(){

        // Webview settings
        with(layoutBinding.webViewActivityWebView.settings){
            javaScriptEnabled = true
        }

        // Init Webivew
        with(layoutBinding.webViewActivityWebView){
            webViewClient = WebViewClient()

            loadUrl(getUrlFromIntent())
        }
    }

    private fun getUrlFromIntent() = intent.getStringExtra(EXTRA_URL)

    private fun getTitleFromIntent() = intent.getStringExtra(EXTRA_TITLE)

    private fun getHeaderBackgroundFromIntent(): Int {
        val colorResouce = intent.getIntExtra(EXTRA_HEADER_BACKGROUND, -1)
        return if(colorResouce!=-1) colorResouce else R.color.activityBackground
    }
}
