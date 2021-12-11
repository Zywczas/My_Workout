package com.zywczas.myworkout.watch.utils

import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract

class VoiceRecognitionContract : ActivityResultContract<Unit, String?>(){

    override fun createIntent(context: Context, input: Unit): Intent {
        TODO("Not yet implemented")
    }

    override fun parseResult(resultCode: Int, intent: Intent?): String? {
        TODO("Not yet implemented")
    }

}