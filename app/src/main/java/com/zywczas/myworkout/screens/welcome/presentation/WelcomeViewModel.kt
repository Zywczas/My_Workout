package com.zywczas.myworkout.screens.welcome.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zywczas.common.di.modules.DispatchersModule.DispatcherIO
import com.zywczas.common.di.modules.UtilsModule.WelcomeScreenDelay
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(
    @DispatcherIO private val dispatcherIO: CoroutineDispatcher,
    @WelcomeScreenDelay private val welcomeScreenDelay: Long
) : ViewModel() {

    init {
        goToNextDestination()
    }

    var shouldGoToNextScreen by mutableStateOf(false)
        private set

    private fun goToNextDestination() {
        viewModelScope.launch(dispatcherIO) {
            presentMotto()
            shouldGoToNextScreen = true
        }
    }

    private suspend fun presentMotto() = delay(welcomeScreenDelay)

}