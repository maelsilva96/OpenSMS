package com.maelsilva96.opensms.decorators.messages

import com.maelsilva96.opensms.models.MessageGroup
import com.maelsilva96.opensms.models.MessageMap

interface MessageSource {
    fun readAllMessages(messageMap: MessageMap)
    fun getMessageGroup(): List<MessageGroup>?
}