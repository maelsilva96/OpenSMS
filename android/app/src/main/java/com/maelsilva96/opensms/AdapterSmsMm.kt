package com.maelsilva96.opensms

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class AdapterSmsMm(context: Context, list: List<String>) :
    ArrayAdapter<String>(context, 0, list) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val sms = getItem(position)

        val view = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.item_message, parent, false)

        val contactName = view.findViewById<TextView>(R.id.contact_name)
        val messagePreview = view.findViewById<TextView>(R.id.message_preview)
        val messageDate = view.findViewById<TextView>(R.id.message_date)

        contactName.text = sms
        messagePreview.text = "test..."
        messageDate.text = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date(0))

        return view
    }
}
