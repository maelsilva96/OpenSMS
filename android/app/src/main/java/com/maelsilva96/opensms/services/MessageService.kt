package com.maelsilva96.opensms.services

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.maelsilva96.opensms.constants.Sms
import com.maelsilva96.opensms.decorators.messages.MessageDecorator
import com.maelsilva96.opensms.decorators.messages.MessageSource
import com.maelsilva96.opensms.decorators.messages.MessageSourceDecorator
import com.maelsilva96.opensms.decorators.messages.MmsDecorator
import com.maelsilva96.opensms.decorators.messages.SmsDecorator
import com.maelsilva96.opensms.models.MessageGroup
import com.maelsilva96.opensms.models.MessageMap

class MessageService(private val ctx: Context, private val act: Activity) {
    private var messageSource: MessageSource = MmsDecorator(
        ctx = ctx, wrapper = SmsDecorator(
            ctx = ctx, wrapper = MessageDecorator()
        )
    )

    private fun hasPermission(): Boolean {
        return ContextCompat
            .checkSelfPermission(ctx, Manifest.permission.READ_SMS) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestPermission() {
        ActivityCompat
            .requestPermissions(act, arrayOf(Manifest.permission.READ_SMS), Sms.SMS_PERMISSION_CODE)
    }

    fun successRequestPermission(requestCode: Int, grantResults: IntArray): Boolean {
        return requestCode == Sms.SMS_PERMISSION_CODE && grantResults.isNotEmpty()
                && grantResults[0] == PackageManager.PERMISSION_GRANTED
    }

    fun load() = if (hasPermission()) this.messageSource.readAllMessages(MessageMap()) else requestPermission()

    fun getMessageGroup(): List<MessageGroup>? = this.messageSource.getMessageGroup()
}