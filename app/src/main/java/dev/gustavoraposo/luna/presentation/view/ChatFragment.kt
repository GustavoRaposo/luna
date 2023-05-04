package dev.gustavoraposo.luna.presentation.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dev.gustavoraposo.luna.R
import dev.gustavoraposo.luna.domain.model.ChatMessage
import dev.gustavoraposo.luna.domain.model.ChatMessageSentBy
import dev.gustavoraposo.luna.presentation.adapter.ChatMessageListAdapter
import dev.gustavoraposo.luna.presentation.viewmodel.ChatViewModel

class ChatFragment : Fragment() {

    companion object {
        fun newInstance() = ChatFragment()
    }

    private lateinit var viewModel: ChatViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var editText: EditText
    private lateinit var sendButton: ImageButton
    private lateinit var listenButton: ImageButton
    private lateinit var chatMessageListAdapter: ChatMessageListAdapter
    private lateinit var messageList: MutableList<ChatMessage>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_chat, container, false)
        recyclerView = view.findViewById(R.id.recyclerViewChat)
        editText = view.findViewById(R.id.editText)
        sendButton = view.findViewById(R.id.buttonSend)
        listenButton = view.findViewById(R.id.buttonListen)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ChatViewModel::class.java)

        messageList = mutableListOf(
            ChatMessage("Olá, eu sou a Luna, como posso te ajudar?", ChatMessageSentBy.BOT)
        )

        chatMessageListAdapter = ChatMessageListAdapter(context, messageList)
        recyclerView.adapter = chatMessageListAdapter
        val linearLayoutManager = LinearLayoutManager(context)
        linearLayoutManager.stackFromEnd = true
        recyclerView.layoutManager = linearLayoutManager

        editText.setOnKeyListener { view, i, keyEvent ->
            if((keyEvent.action == KeyEvent.ACTION_DOWN) &&
                (i == KeyEvent.KEYCODE_ENTER)){
                addToChat(editText.text.toString().trim(), ChatMessageSentBy.USER)
                editText.text.clear()
                return@setOnKeyListener true
            }
            false
        }


        sendButton.setOnClickListener(View.OnClickListener {
            addToChat(editText.text.toString().trim(), ChatMessageSentBy.USER)
            editText.text.clear()
            // TODO: send to gpt3
        })
        listenButton.setOnClickListener(View.OnClickListener {
            Toast.makeText(context, "listen", Toast.LENGTH_LONG)
            // TODO: add message to chat, then send to gpt3
        })
    }

    private fun addToChat(message: String, sentBy: ChatMessageSentBy){
        if(message.isNotEmpty())
            activity?.runOnUiThread(Runnable {
                messageList.add(ChatMessage(message, sentBy))
                chatMessageListAdapter.notifyDataSetChanged()
                recyclerView.smoothScrollToPosition(chatMessageListAdapter.itemCount)
            })
    }

}