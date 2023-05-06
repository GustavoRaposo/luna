package dev.gustavoraposo.luna.data.datasource.api

import dev.gustavoraposo.luna.data.model.chatgpt.RequestBody
import dev.gustavoraposo.luna.data.model.chatgpt.GPT3Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface OpenAiApi {
    @POST("completions")
    suspend fun getAnswer(
        @Header("Content-Type") contentType: String,
        @Header("Authorization") apiKey: String,
        @Body body: RequestBody
    ): GPT3Response
}