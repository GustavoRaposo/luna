package dev.gustavoraposo.luna.tts.google

import android.content.Context
import android.speech.tts.TextToSpeech
import android.util.Log
import dev.gustavoraposo.luna.tts.TTS
import org.intellij.lang.annotations.Language
import java.util.Locale

class GoogleTTS (context: Context): TTS{
    private var tts: TextToSpeech? = null
    private val ttsListener = TextToSpeech.OnInitListener {
        if (it == TextToSpeech.SUCCESS){
            if( setLanguage("pt-BR") )
                Log.i("TTS", "TTS Was Initialized With Success")
        } else {
            Log.e("TTS", "Initilization Failed!")
        }
    }
    init{
        tts = TextToSpeech(context, ttsListener)
    }

    override fun setLanguage(language: String) : Boolean {
        val result = tts!!.setLanguage(Locale(language))
        return if(result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED){
            Log.e("TTS","The Language Specified Is Not Supported!")
            true
        }else{
            Log.i("TTS", "Language Was Changed With Success")
            false
        }
    }

    override fun speak(text: String) {
        tts!!.speak(text, TextToSpeech.QUEUE_FLUSH, null, "")
    }

    override fun stop() {
        tts!!.stop()
        tts!!.shutdown()
    }
}