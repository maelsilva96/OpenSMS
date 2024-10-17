package com.maelsilva96.opensms

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.maelsilva96.opensms.adapters.MessageGroupListAdapter
import com.maelsilva96.opensms.services.MessageService

class MainActivity : AppCompatActivity() {
    private val messageService: MessageService = MessageService(this, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_OpenSMS)
        setContentView(R.layout.activity_main)
        loadMessages()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (messageService.successRequestPermission(requestCode, grantResults)) {
            loadMessages()
        }
    }

    private fun loadMessages() {
        messageService.load()
        val messageGroup = messageService.getMessageGroup()
        val listView = findViewById<ListView>(R.id.message_list)
        val notFoundBox = findViewById<LinearLayout>(R.id.not_results)
        if (!messageGroup.isNullOrEmpty())
        {
            val adapter = MessageGroupListAdapter(this, messageGroup)
            listView.adapter = adapter
            listView.visibility = View.VISIBLE
            notFoundBox.visibility = View.GONE
        } else {
            listView.visibility = View.GONE
            notFoundBox.visibility = View.VISIBLE
        }
    }
}
