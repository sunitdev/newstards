package com.news.aggregator.newstard.services.preferences

import com.chibatching.kotpref.KotprefModel

object AppPerference: KotprefModel() {
    var isDarkTheme by booleanPref(default = false)
}
