package com.zywczas.myworkout.watch.activities.settings.timer.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zywczas.common.di.modules.DispatchersModule.DispatcherIO
import com.zywczas.common.utils.SingleLiveData
import com.zywczas.myworkout.watch.activities.settings.timer.domain.SettingsTimerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsTimerViewModel @Inject constructor(
    private val repo: SettingsTimerRepository,
    @DispatcherIO private val dispatcherIO: CoroutineDispatcher
) : ViewModel() {

    private val _breakPeriodInSeconds = MutableLiveData<Int>()
    val breakPeriodInSeconds: LiveData<Int> = _breakPeriodInSeconds

    private val _isBreakPeriodSaved = SingleLiveData<Boolean>()
    val isBreakPeriodSaved: LiveData<Boolean> = _isBreakPeriodSaved

    fun getBreakPeriod() {
        viewModelScope.launch(dispatcherIO) {
            _breakPeriodInSeconds.postValue(repo.getBreakPeriodInSeconds())
        }
    }

    fun saveBreakPeriod(seconds: Int) {
        viewModelScope.launch(dispatcherIO) {
            repo.saveBreakPeriod(seconds)
            _isBreakPeriodSaved.postValue(true)
        }
    }

}