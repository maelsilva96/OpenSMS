package com.maelsilva96.opensms.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.maelsilva96.opensms.R
import com.maelsilva96.opensms.constants.MessageLimits
import com.maelsilva96.opensms.constants.Sms
import com.maelsilva96.opensms.models.MessageGroup
import com.maelsilva96.opensms.models.MessageMap

class MessageGroupListAdapter(context: Context, listGroup: List<MessageGroup>) :
    ArrayAdapter<MessageGroup>(context, 0, listGroup) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val message = getItem(position)

        val view = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.item_message, parent, false)

        val contactName = view.findViewById<TextView>(R.id.contact_name)
        val messagePreview = view.findViewById<TextView>(R.id.message_preview)
        val messageDate = view.findViewById<TextView>(R.id.message_date)

        contactName.text = message?.getContact()?.name ?: "N\\A"
        messagePreview.text = message?.getLastMessage()?.getShortMessage(MessageLimits.MESSAGE_LIST_MAX_CHARS) ?: "N\\A"
        messageDate.text = message?.getLastMessage()?.getShortDate() ?: "N\\A"
        return view
    }
}