package dev.gustavoraposo.luna.tts

interface TTS {
    fun setLanguage(language: String) : Boolean
    fun speak(text: String)
    fun stop()
}