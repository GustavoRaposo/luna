package dev.gustavoraposo.luna.data.repository

import dev.gustavoraposo.luna.data.datasource.api.OpenAiApi
import dev.gustavoraposo.luna.data.model.chatgpt.RequestBody
import dev.gustavoraposo.luna.data.model.chatgpt.GPT3Response
import dev.gustavoraposo.luna.data.util.NetworkUtils

class OpenAiRepositoryImpl() : OpenAiRepository {

    override suspend fun getAnswer(text: String): GPT3Response {
        val retrofitClient = NetworkUtils.getRetrofitInstance("https://api.openai.com/v1/")
        val apiService = retrofitClient.create(OpenAiApi::class.java)
        return apiService.getAnswer(
            contentType = "application/json",
            apiKey = "Bearer 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",
            body = RequestBody(
                model= "text-davinci-003",
                prompt = text,
                maxTokens = 1024
            )
        )
    }


}

interface OpenAiRepository {
    suspend fun getAnswer(text: String) : GPT3Response
}