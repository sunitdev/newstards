package com.news.aggregator.newstard.utils

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri

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

        fun getPackageIntent(packageName:String, url:String): Intent{
            val intent = Intent(Intent.ACTION_VIEW)

            with(intent) {
                data = Uri.parse(url)
                flags = Intent.FLAG_ACTIVITY_NEW_TASK

                setPackage(packageName)

            }
            return intent
        }

    }
}
