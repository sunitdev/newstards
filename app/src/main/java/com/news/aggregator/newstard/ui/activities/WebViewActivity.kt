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
    }

    private fun initToolBar(){
        setSupportActionBar(layoutBinding.webViewActivityToolbar)

        setToolbarTitle()

        setToolbarBackgroud()
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


    private fun setToolbarTitle(){
        getTitleFromIntent()?.let { layoutBinding.webViewActivityTitle.text = it }
    }

    private fun setToolbarBackgroud() {
        val headerBackgroundColor = getHeaderBackgroundFromIntent()
        if(headerBackgroundColor != -1) {
            layoutBinding.webViewActivityToolbar.setBackgroundColor(headerBackgroundColor)
        }
    }

    private fun getUrlFromIntent() = intent.getStringExtra(EXTRA_URL)

    private fun getTitleFromIntent() = intent.getStringExtra(EXTRA_TITLE)

    private fun getHeaderBackgroundFromIntent() = intent.getIntExtra(EXTRA_HEADER_BACKGROUND, -1)
}
