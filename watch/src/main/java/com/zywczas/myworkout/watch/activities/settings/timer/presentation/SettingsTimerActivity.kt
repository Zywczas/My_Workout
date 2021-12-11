package com.zywczas.myworkout.watch.activities.settings.timer.presentation

import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import com.zywczas.common.utils.autoRelease
import com.zywczas.myworkout.watch.R
import com.zywczas.myworkout.watch.activities.BaseActivity
import com.zywczas.myworkout.watch.databinding.ActivitySettingsTimerBinding

class SettingsTimerActivity : BaseActivity() {

    private var binding: ActivitySettingsTimerBinding by autoRelease()
    private val viewModel: SettingsTimerViewModel by viewModels { viewModelFactory }

    private val voiceRecognitionLauncher: ActivityResultLauncher<Intent> = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if (it.resultCode == RESULT_OK){
            it.data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)?.let { words ->
                var sentence = ""
                var spaceBetweenWords = ""
                words.forEach {
                    sentence = sentence.plus("$spaceBetweenWords$it")
                    spaceBetweenWords = " "
                }
                binding.time.text = sentence
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsTimerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupLiveDataObservers()
        setupOnClickListeners()
    }

    private fun setupLiveDataObservers(){
        viewModel.breakPeriodInSeconds.observe(this) { binding.time.text = getString(R.string.number_of_seconds, it) }
    }

    private fun setupOnClickListeners(){
        binding.change.setOnClickListener {
            val voiceRecognitionIntent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
                putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
            }
            voiceRecognitionLauncher.launch(voiceRecognitionIntent)
        }
    }



}