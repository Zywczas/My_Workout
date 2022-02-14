package com.zywczas.myworkout.screens.welcome.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zywczas.common.extetions.logD
import com.zywczas.common.utils.DateTimeProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlin.math.absoluteValue

@HiltViewModel
class WelcomeViewModel @Inject constructor(
    private val dateTime: DateTimeProvider
) : ViewModel() {

    init {
        logD("init")
    }

    private val jakasLiveData = MutableLiveData<Int>()

    var todoItems = mutableStateListOf("siema") //todo tak robic teraz, zamiast live daty
        private set

    fun cos(){

    }

}