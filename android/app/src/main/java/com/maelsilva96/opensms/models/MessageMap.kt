package com.maelsilva96.opensms.models

class MessageMap(private val data: MutableMap<Contact, MutableList<MessageData>> = mutableMapOf()) : Map<Contact, List<MessageData>> {
    override val entries: Set<Map.Entry<Contact, MutableList<MessageData>>>
        get() = data.entries

    override val keys: Set<Contact>
        get() = data.keys

    override val values: Collection<MutableList<MessageData>>
        get() = data.values

    override val size: Int
        get() = data.size

    override fun containsKey(key: Contact): Boolean {
        return data.containsKey(key)
    }

    override fun containsValue(value: List<MessageData>): Boolean {
        return data.containsValue(value)
    }

    override fun get(key: Contact): MutableList<MessageData>? {
        return data[key]
    }

    fun getMessageGroup(): List<MessageGroup> {
        return data.map { (key, value) -> MessageGroup(key, value) }
            .sortedByDescending { it.getLastMessage().createdAt }
    }

    fun put(key: Contact, value: MutableList<MessageData>) {
        data[key] = value
    }

    fun remove(key: Contact) {
        data.remove(key)
    }

    override fun isEmpty(): Boolean {
        return data.isEmpty()
    }
}