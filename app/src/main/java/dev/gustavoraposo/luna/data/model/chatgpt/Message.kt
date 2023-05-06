package dev.gustavoraposo.luna.data.model.chatgpt

import com.google.gson.annotations.SerializedName

data class Message(
    @SerializedName("role")
    val role: String,

    @SerializedName("content")
    val content: String
)
