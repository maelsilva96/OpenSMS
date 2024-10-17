package com.maelsilva96.opensms.services.abstraction

import android.content.Context
import com.maelsilva96.opensms.models.MessageMap

abstract class AbstractMessageService(protected open val ctx: Context) {
    abstract fun readAllMessages(messageMap: MessageMap)
}