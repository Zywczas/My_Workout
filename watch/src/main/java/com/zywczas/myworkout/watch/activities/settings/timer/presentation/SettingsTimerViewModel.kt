package com.zywczas.myworkout.watch.activities.settings.timer.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zywczas.common.di.modules.DispatchersModule.DispatcherIO
import com.zywczas.myworkout.watch.activities.settings.timer.domain.SettingsTimerRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

class SettingsTimerViewModel @Inject constructor(
    private val repo: SettingsTimerRepository,
    @DispatcherIO private val dispatcherIO: CoroutineDispatcher
) : ViewModel(){

    private val _breakPeriodInSeconds = MutableLiveData<Int>()
    val breakPeriodInSeconds: LiveData<Int> = _breakPeriodInSeconds

    init { //todo dac init tez w innych view modelach
        getBreakPeriod()
    }

    private fun getBreakPeriod(){
        viewModelScope.launch(dispatcherIO){
            _breakPeriodInSeconds.postValue(repo.getBreakPeriodInSeconds())
        }
    }

}