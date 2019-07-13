package com.news.aggregator.newstard.utils

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri

class PackageManager {

    fun isPackageInstalled(content: Context, packageName: String):Boolean{
        try {
            _context.packageManager.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES)
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