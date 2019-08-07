package com.news.aggregator.newstard.utils

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import com.news.aggregator.newstard.ui.activities.WebViewActivity

class PackageUtils {

    companion object{
        private const val _chromePackageName = "com.android.chrome"

        fun isChromeInstalled(context: Context) = isPackageInstalled(context, _chromePackageName)

        fun isPackageInstalled(context: Context, packageName: String):Boolean{
            try {
                context.packageManager.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES)
                return true
            } catch (e: PackageManager.NameNotFoundException) {
                return false
            }
        }

        fun getDefaultViewIntent(url:String): Intent{
            val intent = Intent(Intent.ACTION_VIEW)

            with(intent) {
                data = Uri.parse(url)
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }
            return intent
        }

        fun getChromeTabIntent(context: Context, titleColorResource: Int): CustomTabsIntent {
            val builder = CustomTabsIntent.Builder()
                .setToolbarColor(ContextCompat.getColor(context, titleColorResource))
                .addDefaultShareMenuItem()
                .setShowTitle(true)
                .setStartAnimations(context, android.R.anim.slide_in_left, android.R.anim.slide_in_left)
                .setExitAnimations(context, android.R.anim.slide_out_right, android.R.anim.slide_out_right)

            val customTabsIntent = builder.build()
            customTabsIntent.intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            return customTabsIntent
        }

        fun getWebViewIntent(context: Context, title: String, url: String, headerColorResource: Int): Intent{
            val intent = Intent(context, WebViewActivity::class.java)

            with(intent) {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK

                putExtra(WebViewActivity.EXTRA_URL, url)
                putExtra(WebViewActivity.EXTRA_TITLE, title)
                putExtra(
                    WebViewActivity.EXTRA_HEADER_BACKGROUND,
                    ContextCompat.getColor(context, headerColorResource)
                )
            }

            return intent
        }

    }
}
