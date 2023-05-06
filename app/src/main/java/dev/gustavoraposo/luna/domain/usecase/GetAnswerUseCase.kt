package dev.gustavoraposo.luna.domain.usecase

import android.util.Log
import dev.gustavoraposo.luna.data.model.chatgpt.toChatMessages
import dev.gustavoraposo.luna.data.repository.OpenAiRepositoryImpl
import dev.gustavoraposo.luna.domain.model.ChatMessage
import dev.gustavoraposo.luna.domain.model.ChatMessageSentBy

class GetAnswerUseCase {
    private val repository = OpenAiRepositoryImpl()

    suspend fun invoke(text: String): ChatMessage = try {
        val response = repository.getAnswer(text)
        Log.i("ChatGPT","text = ${response.choices[0].text}")
        Log.i("ChatGPT","finish reason = ${response.choices[0].finish_reason}")
        Log.i("ChatGPT", "model = ${response.model}")
        Log.i("ChatGPT", "total tokens = ${response.usage.total_tokens}")
        if(response.choices[0].text.startsWith("\n\n")){
            response.choices[0].text.replace("\n\n", "")
        }
        textReplacer(response.toChatMessages())
    } catch (e: Exception) {
        ChatMessage(
            text = "ERROR: " + e.message,
            sentBy = ChatMessageSentBy.SYSTEM
        )
    }

    private fun textReplacer(message: ChatMessage): ChatMessage{
        return if(message.text.startsWith("\n\n")){
            ChatMessage(
                text = message.text.replace("\n\n", ""),
                sentBy = ChatMessageSentBy.BOT
            )
        }else message
    }
}

