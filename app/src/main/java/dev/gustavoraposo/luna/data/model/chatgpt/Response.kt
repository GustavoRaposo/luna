package dev.gustavoraposo.luna.data.model.chatgpt

import com.google.gson.annotations.SerializedName
import dev.gustavoraposo.luna.domain.model.ChatMessage
import dev.gustavoraposo.luna.domain.model.ChatMessageSentBy

data class GPT3Response(
    @SerializedName("choices")
    val choices: List<Choice>,

    @SerializedName("created")
    val created: Int,

    @SerializedName("id")
    val id: String,

    @SerializedName("model")
    val model: String,

    @SerializedName("object")
    val `object`: String,

    @SerializedName("usage")
    val usage: Usage
)

fun GPT3Response.toChatMessages() : ChatMessage{
    return ChatMessage(
        text = this.choices[0].text,
        sentBy = ChatMessageSentBy.BOT
    )
}
