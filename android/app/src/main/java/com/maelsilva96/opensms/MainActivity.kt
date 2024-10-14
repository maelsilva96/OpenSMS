package com.maelsilva96.opensms

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.maelsilva96.opensms.adapters.MessageGroupListAdapter
import com.maelsilva96.opensms.models.Contact
import com.maelsilva96.opensms.models.MessageData
import com.maelsilva96.opensms.models.MessageGroup
import java.util.Date

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_OpenSMS)

        setContentView(R.layout.activity_main)

        val messages = listOf(
            MessageData("SMS 1", Date()),
            MessageData("SMS 2", Date()),
            MessageData("SMS 3", Date()),
            MessageData("Essa é uma mensagem muito longa", Date())
        )

        val listGroup = listOf(
            MessageGroup(Contact("Phone 1", "+ 55 12 98888-8888"), messages),
            MessageGroup(Contact("Phone 2", "+ 55 12 98888-8888"), messages),
            MessageGroup(Contact("Phone 4", "+ 55 12 98888-8888"), messages),
            MessageGroup(Contact("Phone 5", "+ 55 12 98888-8888"), messages)
        )

        val adapterSmsMm = MessageGroupListAdapter(this, listGroup)
        findViewById<ListView>(R.id.message_list).adapter = adapterSmsMm
    }
}
