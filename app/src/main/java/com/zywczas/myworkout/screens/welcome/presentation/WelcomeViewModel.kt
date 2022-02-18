package com.zywczas.myworkout.screens.welcome.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zywczas.common.di.modules.DispatchersModule.DispatcherIO
import com.zywczas.common.di.modules.UtilsModule.WelcomeScreenDelay
import com.zywczas.common.extetions.logD
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(
    @DispatcherIO private val dispatcherIO: CoroutineDispatcher,
    @WelcomeScreenDelay private val welcomeScreenDelay: Long
) : ViewModel() {

    private val _shouldGoToNextScreen = MutableSharedFlow<Boolean>()
    val shouldGoToNextScreen = _shouldGoToNextScreen.asSharedFlow()

//    private val _shouldGoToNextScreen = MutableLiveData<Boolean>()
//    val shouldGoToNextScreen = _shouldGoToNextScreen

//    var shouldGoToNextScreen by mutableStateOf(false) //todo usunac pozniej
//        private set

    fun goToNextDestination() {
        viewModelScope.launch(dispatcherIO) {
            presentMotto()
            _shouldGoToNextScreen.emit(true)
//            _shouldGoToNextScreen.postValue(true)
        }
    }

    private suspend fun presentMotto() = delay(welcomeScreenDelay)

}