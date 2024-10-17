package com.maelsilva96.opensms.services

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.maelsilva96.opensms.constants.Sms
import com.maelsilva96.opensms.models.MessageGroup
import com.maelsilva96.opensms.models.MessageMap
import com.maelsilva96.opensms.services.abstraction.AbstractMessageService
import com.maelsilva96.opensms.services.concret.MmsMessageService
import com.maelsilva96.opensms.services.concret.SmsMessageService

class MessageService(private val ctx: Context, private val act: Activity) {
    private var messageMap: MessageMap? = null

    private val services: List<AbstractMessageService> = listOf(
        SmsMessageService(ctx), MmsMessageService(ctx)
    )

    private fun hasPermission(): Boolean {
        return ContextCompat
            .checkSelfPermission(ctx, Manifest.permission.READ_SMS) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestPermission() {
        ActivityCompat
            .requestPermissions(act, arrayOf(Manifest.permission.READ_SMS), Sms.SMS_PERMISSION_CODE)
    }

    private fun readAllMessages(): MessageMap {
        return services.fold(MessageMap()) { messageMap, service ->
            service.readAllMessages(messageMap)
            messageMap
        }
    }

    fun successRequestPermission(requestCode: Int, grantResults: IntArray): Boolean {
        return requestCode == Sms.SMS_PERMISSION_CODE && grantResults.isNotEmpty()
                && grantResults[0] == PackageManager.PERMISSION_GRANTED
    }

    fun load() {
        if (hasPermission()) {
            messageMap = readAllMessages()
        } else {
            requestPermission()
        }
    }

    fun getMessageGroup(): List<MessageGroup>? {
        return messageMap?.getMessageGroup()
    }
}