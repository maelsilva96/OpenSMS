package com.maelsilva96.opensms.decorators.messages

import com.maelsilva96.opensms.models.MessageGroup
import com.maelsilva96.opensms.models.MessageMap

open class MessageSourceDecorator(protected open val wrapper: MessageSource): MessageSource {

    override fun readAllMessages(messageMap: MessageMap) {
        wrapper.readAllMessages(messageMap)
    }

    override fun getMessageGroup(): List<MessageGroup>? {
        return wrapper.getMessageGroup()
    }
}