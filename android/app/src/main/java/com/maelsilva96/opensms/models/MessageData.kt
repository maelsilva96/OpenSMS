package com.maelsilva96.opensms.models

import com.maelsilva96.opensms.constants.Datetime
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

data class MessageData(val message: String, val createdAt: Date) {
    fun getShortMessage(maxChars: Int): String {
        return if (message.length > maxChars) {
            "${message.take(maxChars)}..."
        } else {
            message
        }
    }

    fun getShortDate(): String {
        val calendar = Calendar.getInstance()
        calendar.time = createdAt
        val now = Calendar.getInstance()

        val daysBetween = (now.timeInMillis - createdAt.time) / Datetime.DAY_MILLISECONDS

        return when {
            daysBetween == 0L -> SimpleDateFormat(Datetime.FORMAT_ONLY_HOUR, Locale.getDefault()).format(createdAt)
            daysBetween < 30L && calendar.get(Calendar.MONTH) == now.get(Calendar.MONTH) -> {
                SimpleDateFormat(Datetime.FORMAT_DAY_AND_MONTH, Locale.getDefault()).format(createdAt)
            }
            else -> {
                SimpleDateFormat(Datetime.FORMAT_MONTH_AND_YEAR, Locale.getDefault()).format(createdAt)
            }
        }
    }
}