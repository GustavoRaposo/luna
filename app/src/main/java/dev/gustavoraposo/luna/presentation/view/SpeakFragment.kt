package dev.gustavoraposo.luna.presentation.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import dev.gustavoraposo.luna.R
import dev.gustavoraposo.luna.domain.usecase.SpeakMessageUseCase

class SpeakFragment : Fragment() {

    private lateinit var speakButton: Button
    private lateinit var tts: SpeakMessageUseCase
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_speak, container, false)
        tts = context?.let { SpeakMessageUseCase(it) }!!
        speakButton = view.findViewById(R.id.buttonSpeak)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        speakButton.setOnClickListener(View.OnClickListener {
            tts.speak("Isto é um teste")
        })
    }
}