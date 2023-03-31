package com.zywczas.myworkout.screens.welcome.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zywczas.common.di.modules.DispatchersModule.DispatcherIO
import com.zywczas.common.di.modules.UtilsModule.WelcomeScreenDelay
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(
    @DispatcherIO private val dispatcherIO: CoroutineDispatcher,
    @WelcomeScreenDelay private val welcomeScreenDelay: Long
) : ViewModel() {

    private val _shouldGoToNextScreen = MutableStateFlow(false)
    val shouldGoToNextScreen: StateFlow<Boolean> = _shouldGoToNextScreen

    fun goToNextDestination() {
        viewModelScope.launch(dispatcherIO) {
            presentScreen()
            _shouldGoToNextScreen.emit(true)
        }
    }

    private suspend fun presentScreen() = delay(welcomeScreenDelay)
}
