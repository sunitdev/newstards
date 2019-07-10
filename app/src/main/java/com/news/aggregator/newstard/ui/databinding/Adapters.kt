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
    val  timeAgoText: String

    val differenceInHours = ((now.timeInMillis - dateTime.timeInMillis) / (1000 * 60 * 60)).toInt()

    if(differenceInHours <= 12){
        timeAgoText = DateUtils.getRelativeTimeSpanString(dateTime.timeInMillis, now.timeInMillis, 0L, DateUtils.FORMAT_ABBREV_ALL).toString()
    }else {
        timeAgoText = SimpleDateFormat("MMM d", Locale.getDefault()).format(dateTime.time)
    }

    view.text = timeAgoText
}

@BindingAdapter("setHumanizeNumber")
fun setHumanizeNumber(view: TextView, value: Long){

    val text: String

    if(value > 1000){
        val exp = (log(value.toDouble(), 10.toDouble()) / log(1000.toDouble(), 10.toDouble())).toInt()
        val suffix = "kMGTPE"[exp-1].toString()
        val number = value / 1000F.pow(exp).toInt()
        text = "$number$suffix"
    }else{
        text = "$value"
    }

    view.text = text
}


