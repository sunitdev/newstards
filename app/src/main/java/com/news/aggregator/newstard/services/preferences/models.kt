package com.news.aggregator.newstard.services.preferences

import com.chibatching.kotpref.KotprefModel
import com.news.aggregator.newstard.R

object AppPreference: KotprefModel() {

    override val kotprefName: String = context.getString(R.string.preference_app_filename)

    var isDarkTheme by booleanPref(default = false, key = context.getString(R.string.preference_app_key_is_dark_theme))
    
}
