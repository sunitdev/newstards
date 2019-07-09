package com.news.aggregator.newstard.ui.activities

import android.os.Bundle
import android.view.MenuItem
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.news.aggregator.newstard.R
import com.news.aggregator.newstard.databinding.ActivityWebviewBinding


class WebViewActivity : AppCompatActivity(){

    private lateinit var layoutBinding: ActivityWebviewBinding

    companion object{
        val EXTRA_URL = "EXTRA_URL"
        val EXTRA_TITLE = "EXTRA_TITLE"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _initLayout()

        _initActionBar()

        _initWebView()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        return when(item!!.itemId){
            android.R.id.home -> {
                finish()
                return true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
    private fun _initLayout(){
        layoutBinding = DataBindingUtil.setContentView(this, R.layout.activity_webview)
    }

    private fun _initActionBar(){
        val actionBarTitle = _getTitleFromIntent()
        actionBarTitle.let { title=actionBarTitle }

        with(supportActionBar!!){
            setDisplayShowTitleEnabled(true)

            setHomeAsUpIndicator(android.R.drawable.ic_menu_close_clear_cancel)
            setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun _initWebView(){

        // Webview settings
        with(layoutBinding.webViewActivityWebView.settings){
            javaScriptEnabled = true
        }

        // Init Webivew
        with(layoutBinding.webViewActivityWebView){
            webViewClient = WebViewClient()

            loadUrl(_getUrlFromIntent())
        }
    }

    private fun _getUrlFromIntent() = intent.getStringExtra(EXTRA_URL)

    private fun _getTitleFromIntent() = intent.getStringExtra(EXTRA_TITLE)
}
