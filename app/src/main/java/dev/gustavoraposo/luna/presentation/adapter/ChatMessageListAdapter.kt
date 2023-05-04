package dev.gustavoraposo.luna.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import dev.gustavoraposo.luna.R
import dev.gustavoraposo.luna.domain.model.ChatMessage
import dev.gustavoraposo.luna.domain.model.ChatMessageSentBy

class ChatMessageListAdapter(
    private val context: Context?,
    private val messages: MutableList<ChatMessage>
    ) : Adapter<ChatMessageListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.chat_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return messages.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val message = messages[position]
        when(message.sentBy){
            ChatMessageSentBy.USER -> {
                holder.userChatView.visibility = View.VISIBLE
                holder.botChatView.visibility = View.GONE
                holder.systemChatView.visibility = View.GONE
                holder.userChatText.text = message.text
            }
            ChatMessageSentBy.BOT -> {
                holder.userChatView.visibility = View.GONE
                holder.botChatView.visibility = View.VISIBLE
                holder.systemChatView.visibility = View.GONE
                holder.botChatText.text = message.text
            }
            ChatMessageSentBy.SYSTEM -> {
                holder.userChatView.visibility = View.GONE
                holder.botChatView.visibility = View.GONE
                holder.systemChatView.visibility = View.VISIBLE
                holder.systemChatText.text = message.text
            }
        }
    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val userChatView = itemView.findViewById<LinearLayout>(R.id.userChatView)
        val userChatText = itemView.findViewById<TextView>(R.id.userChatText)
        val botChatText = itemView.findViewById<TextView>(R.id.botChatText)
        val botChatView = itemView.findViewById<LinearLayout>(R.id.botChatView)
        val systemChatView = itemView.findViewById<LinearLayout>(R.id.systemChatView)
        val systemChatText = itemView.findViewById<TextView>(R.id.systemChatText)
    }
}