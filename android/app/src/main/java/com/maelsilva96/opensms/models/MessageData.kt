package com.maelsilva96.opensms.models

import java.util.Date

data class MessageData(val message: String, val createdAt: Date) {
    fun getShortMessage(maxChars: Int): String {
        return if (message.length > maxChars) {
            "${message.take(maxChars)}..."
        } else {
            message
        }
    }
}