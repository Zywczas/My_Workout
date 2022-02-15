package com.zywczas.myworkout.watch.activities.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zywczas.common.di.modules.DispatchersModule.DispatcherIO
import com.zywczas.common.di.modules.UtilsModule.WelcomeScreenDelay
import com.zywczas.common.utils.SingleLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    @DispatcherIO private val dispatcherIO: CoroutineDispatcher,
    @WelcomeScreenDelay private val welcomeScreenDelay: Long
) : ViewModel() {

    private val _goToNextActivity = SingleLiveData<Boolean>()
    val goToNextActivity: LiveData<Boolean> = _goToNextActivity

    fun goToNextDestination(){
        viewModelScope.launch(dispatcherIO) {
            presentMotto()
            _goToNextActivity.postValue(true)
        }
    }

    private suspend fun presentMotto() = delay(welcomeScreenDelay)

}