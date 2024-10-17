package com.maelsilva96.opensms.decorators.messages

import com.maelsilva96.opensms.models.MessageGroup
import com.maelsilva96.opensms.models.MessageMap

class MessageDecorator : MessageSource {
    private var messageMap: MessageMap? = null

    override fun readAllMessages(messageMap: MessageMap) {
        this.messageMap = messageMap
    }

    override fun getMessageGroup(): List<MessageGroup>? {
        return this.messageMap?.getMessageGroup()
    }
}