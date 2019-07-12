package com.news.aggregator.newstard.ui.databinding

import android.text.format.DateUtils
import android.widget.TextView
import androidx.databinding.BindingAdapter
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.log
import kotlin.math.pow


@BindingAdapter("setTimeAgo")
fun setTimeAgo(view: TextView, dateTime: Calendar){

    val now = Calendar.getInstance()

    val differenceInHours = ((now.timeInMillis - dateTime.timeInMillis) / (1000 * 60 * 60)).toInt()

    val  timeAgoText = if (differenceInHours <= 12) {
        DateUtils.getRelativeTimeSpanString(dateTime.timeInMillis, now.timeInMillis, 0L, DateUtils.FORMAT_ABBREV_ALL).toString()
    }else {
        SimpleDateFormat("MMM d", Locale.getDefault()).format(dateTime.time)
    }

    view.text = timeAgoText
}

@BindingAdapter("setHumanizeNumber")
fun setHumanizeNumber(view: TextView, value: Long){

    val text = if(value > 1000){
        val exp = (log(value.toDouble(), 10.toDouble()) / log(1000.toDouble(), 10.toDouble())).toInt()
        val suffix = "kMGTPE"[exp-1].toString()
        val number = value / 1000F.pow(exp).toInt()

        "$number$suffix"
    }else{
        "$value"
    }

    view.text = text
}


