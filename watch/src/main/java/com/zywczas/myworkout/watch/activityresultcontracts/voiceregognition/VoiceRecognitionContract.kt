package com.zywczas.myworkout.watch.activityresultcontracts.voiceregognition

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.speech.RecognizerIntent
import androidx.activity.result.contract.ActivityResultContract

class VoiceRecognitionContract : ActivityResultContract<Unit, String?>() {

    override fun createIntent(context: Context, input: Unit): Intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
        putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
    }

    override fun parseResult(resultCode: Int, intent: Intent?): String? =
        if (resultCode == Activity.RESULT_OK && intent != null) {
            intent.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)?.let { words ->
                var sentence = ""
                var spaceBetweenWords = ""
                //todo change it to "for each indexed"
                words.forEach {
                    sentence = sentence.plus("$spaceBetweenWords$it")
                    spaceBetweenWords = " "
                }
                sentence
            }
        } else {
            null
        }
}
