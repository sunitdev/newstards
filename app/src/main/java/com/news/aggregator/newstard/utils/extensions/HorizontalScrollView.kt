package com.news.aggregator.newstard.utils.extensions

import android.view.View
import android.widget.HorizontalScrollView

fun HorizontalScrollView.scrollXToViewCenter(view: View) {
    val scrollX = view.right - (width/2)
    smoothScrollTo(scrollX, 9)
}