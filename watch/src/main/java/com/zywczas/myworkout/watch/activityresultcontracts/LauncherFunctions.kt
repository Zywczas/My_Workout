package com.zywczas.myworkout.watch.activityresultcontracts

import androidx.activity.ComponentActivity
import com.zywczas.myworkout.watch.activityresultcontracts.voiceregognition.VoiceRecognitionLauncher

fun ComponentActivity.registerVoiceRecognition(onResultAction: (String?) -> Unit) =
    VoiceRecognitionLauncher(this, this, onResultAction)