package com.maelsilva96.opensms.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.maelsilva96.opensms.R
import com.maelsilva96.opensms.constants.Constants
import com.maelsilva96.opensms.models.MessageGroup
import java.text.SimpleDateFormat
import java.util.Locale

class MessageGroupListAdapter(context: Context, listGroup: List<MessageGroup>) :
    ArrayAdapter<MessageGroup>(context, 0, listGroup) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val message = getItem(position)

        val view = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.item_message, parent, false)

        val contactName = view.findViewById<TextView>(R.id.contact_name)
        val messagePreview = view.findViewById<TextView>(R.id.message_preview)
        val messageDate = view.findViewById<TextView>(R.id.message_date)

        contactName.text = message?.contact?.name
        val lastMessage = message?.messages?.last()

        messagePreview.text = lastMessage?.getShortMessage(Constants.MESSAGE_LIST_MAX_CHARS)

        val dateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
        messageDate.text = lastMessage?.createdAt?.let { dateFormat.format(it) } ?: "N\\A"

        return view
    }
}