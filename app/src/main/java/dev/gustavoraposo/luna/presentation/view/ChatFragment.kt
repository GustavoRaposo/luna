package dev.gustavoraposo.luna.presentation.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import dev.gustavoraposo.luna.R
import dev.gustavoraposo.luna.presentation.viewmodel.ChatViewModel

class ChatFragment : Fragment() {

    companion object {
        fun newInstance() = ChatFragment()
    }

    private lateinit var viewModel: ChatViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var editText: EditText
    private lateinit var sendButton: Button
    private lateinit var listenButton: Button

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
        sendButton.setOnClickListener(View.OnClickListener {
            Toast.makeText(context, "send", Toast.LENGTH_LONG)
            // TODO: add message to chat, then send to gpt3
        })
        listenButton.setOnClickListener(View.OnClickListener {
            Toast.makeText(context, "listen", Toast.LENGTH_LONG)
            // TODO: add message to chat, then send to gpt3
        })
    }

}