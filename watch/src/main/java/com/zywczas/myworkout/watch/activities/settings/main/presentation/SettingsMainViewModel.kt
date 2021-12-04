package com.zywczas.myworkout.watch.activities.settings.main.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zywczas.common.di.modules.DispatchersModule.DispatcherIO
import com.zywczas.myworkout.watch.activities.settings.main.domain.SettingsMainElements
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

class SettingsMainViewModel @Inject constructor(
    @DispatcherIO private val dispatcherIO: CoroutineDispatcher
) : ViewModel() {

    private val _settingsElements = MutableLiveData<List<SettingsMainElements>>()
    val settingsElements: LiveData<List<SettingsMainElements>> = _settingsElements

    fun getSettingsList() {
        viewModelScope.launch(dispatcherIO){
            val settingsElements = mutableListOf<SettingsMainElements>().apply {
                add(SettingsMainElements.Title())
                add(SettingsMainElements.Trainings())
                add(SettingsMainElements.BreakInterval())
            }
            _settingsElements.postValue(settingsElements)
        }
    }

}