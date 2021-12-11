package com.zywczas.myworkout.watch.activityresultcontracts.voiceregognition

import androidx.activity.result.ActivityResultCaller
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.launch
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner

class VoiceRecognitionLauncher (
    private val lifecycleOwner: LifecycleOwner,
    private val activityResultCaller: ActivityResultCaller,
    private val onResultAction: (String?) -> Unit
) {

    init {
        register()
    }

    private lateinit var launcher: ActivityResultLauncher<Unit>

    private fun register() {
        if (lifecycleOwner.lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)) {
            throw IllegalStateException(
                "${this.javaClass.simpleName} is initialized too late in ${lifecycleOwner.javaClass.simpleName}. " +
                        "You need to initialize it in one of following: onAttach(), onCreate(), onCreateView(), onViewCreated(), onStart() " +
                        "or at initialization of the ${lifecycleOwner.javaClass.simpleName}"
            )
        }
        launcher = activityResultCaller.registerForActivityResult(VoiceRecognitionContract()) { onResultAction(it) }
    }

    fun launch() {
        launcher.launch()
    }

}