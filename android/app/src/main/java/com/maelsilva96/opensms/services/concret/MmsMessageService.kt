package com.maelsilva96.opensms.services.concret

import android.content.Context
import android.net.Uri
import android.provider.Telephony
import android.util.Log
import com.maelsilva96.opensms.models.MessageMap
import com.maelsilva96.opensms.services.abstraction.AbstractMessageService

class MmsMessageService(override val ctx: Context) : AbstractMessageService(ctx) {
    private val uri: Uri = Telephony.Mms.CONTENT_URI
    private val projection = arrayOf(Telephony.Mms._ID, Telephony.Mms.DATE, Telephony.Mms.SUBJECT)

    override fun readAllMessages(messageMap: MessageMap) {
        val cursor = ctx.contentResolver.query(uri, projection, null, null, null)
        cursor?.use {
            while (it.moveToNext()) {
                val id = it.getLong(it.getColumnIndexOrThrow(Telephony.Sms._ID))
                val date = it.getLong(it.getColumnIndexOrThrow(Telephony.Sms.DATE))
                val address = it.getString(it.getColumnIndexOrThrow(Telephony.Sms.ADDRESS))

                Log.d("SMS", "ID: $id, Date: $date, Address: $address, Body: ")
            }
        }
    }
}