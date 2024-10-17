package com.maelsilva96.opensms.models

class MessageGroup(
    private val contact: Contact,
    messages: List<MessageData>
) {
    private val lastMessage: MessageData
    private val messages: List<MessageData> = messages.sortedBy { it.createdAt }

    init {
        this.lastMessage = this.messages.lastOrNull()
            ?: throw IllegalArgumentException("Message list is empty")
    }

    fun getContact(): Contact {
        return contact
    }

    fun getLastMessage(): MessageData {
        return lastMessage
    }

    fun getMessages(): List<MessageData> {
        return messages
    }
}