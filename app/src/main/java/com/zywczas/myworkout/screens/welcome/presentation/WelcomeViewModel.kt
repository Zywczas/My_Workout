package com.zywczas.myworkout.screens.welcome.presentation

import androidx.lifecycle.ViewModel
import com.zywczas.common.utils.DateTimeProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(
    private val dateTime: DateTimeProvider
) : ViewModel() {

}