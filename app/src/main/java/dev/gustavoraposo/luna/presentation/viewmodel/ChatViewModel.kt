package dev.gustavoraposo.luna.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.gustavoraposo.luna.domain.model.ChatMessage
import dev.gustavoraposo.luna.domain.usecase.GetAnswerUseCase
import kotlinx.coroutines.launch

class ChatViewModel : ViewModel() {
    private val getAnswerUseCase = GetAnswerUseCase()
    private val _chatGPTResponse = MutableLiveData<ChatMessage>()

    val chatGPTResponse: LiveData<ChatMessage>
        get() = _chatGPTResponse

    fun getAnswer(text: String){
        Log.i("ChatGPT", "question = $text")
        viewModelScope.launch{
            val response = getAnswerUseCase.invoke(text)
            _chatGPTResponse.value = response
        }
    }

}