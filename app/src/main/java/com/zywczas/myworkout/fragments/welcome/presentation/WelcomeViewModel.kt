package com.zywczas.myworkout.fragments.welcome.presentation

import androidx.lifecycle.ViewModel
import com.zywczas.common.extetions.logD
import com.zywczas.common.utils.DateTimeProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(
    private val dateTime: DateTimeProvider
) : ViewModel() {

    init {
        logD("init")
    }

}