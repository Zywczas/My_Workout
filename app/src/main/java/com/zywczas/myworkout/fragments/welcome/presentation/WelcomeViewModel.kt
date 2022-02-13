package com.zywczas.myworkout.fragments.welcome.presentation

import androidx.lifecycle.ViewModel
import com.zywczas.common.extetions.logD
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor() : ViewModel() {

    init {
        logD("init")
    }

}