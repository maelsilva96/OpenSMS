package com.maelsilva96.opensms.decorators.messages

import android.content.Context
import android.net.Uri
import android.provider.Telephony
import android.util.Log
import com.maelsilva96.opensms.models.Contact
import com.maelsilva96.opensms.models.MessageData
import com.maelsilva96.opensms.models.MessageMap
import java.util.Date

class SmsDecorator(private val ctx: Context, override val wrapper: MessageSource): MessageSourceDecorator(wrapper)
{
    private val uri: Uri = Telephony.Sms.CONTENT_URI
    private val projection = arrayOf(Telephony.Sms._ID, Telephony.Sms.DATE, Telephony.Sms.ADDRESS, Telephony.Sms.BODY)

    override fun readAllMessages(messageMap: MessageMap) {
        val cursor = ctx.contentResolver.query(uri, projection, null, null, null)
        cursor?.use {
            while (it.moveToNext()) {
                val id = it.getLong(it.getColumnIndexOrThrow(Telephony.Sms._ID))
                val date = it.getLong(it.getColumnIndexOrThrow(Telephony.Sms.DATE))
                val address = it.getString(it.getColumnIndexOrThrow(Telephony.Sms.ADDRESS))
                val body = it.getString(it.getColumnIndexOrThrow(Telephony.Sms.BODY))

                val contact = Contact(address, address)
                val messageData = MessageData(body, Date(date))
                if (messageMap.containsKey(contact)) {
                    messageMap[contact]?.add(messageData)
                } else {
                    messageMap.put(contact, mutableListOf(messageData))
                }
                Log.d("SMS", "ID: $id, Date: $date, Address: $address, Body: $body")
            }
        }
        super.readAllMessages(messageMap)
    }
}