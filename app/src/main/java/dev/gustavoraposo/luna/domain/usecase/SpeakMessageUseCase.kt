package dev.gustavoraposo.luna.domain.usecase

import android.content.Context
import dev.gustavoraposo.luna.tts.google.GoogleTTS

class SpeakMessageUseCase (context: Context){
    private val tts = GoogleTTS(context)

    fun speak(text: String){
        tts.speak(text)
    }
}