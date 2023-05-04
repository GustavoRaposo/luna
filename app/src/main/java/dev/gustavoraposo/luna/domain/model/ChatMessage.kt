package dev.gustavoraposo.luna.domain.model

data class ChatMessage(
    val text: String,
    val sentBy: ChatMessageSentBy
)
