package com.zywczas.myworkout.watch.activities.settings.weeks.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.zywczas.common.di.modules.DispatchersModule.DispatcherIO
import com.zywczas.myworkout.watch.R
import com.zywczas.myworkout.watch.activities.BaseViewModel
import com.zywczas.myworkout.watch.activities.settings.weeks.domain.SettingsWeeksElements
import com.zywczas.myworkout.watch.activities.settings.weeks.domain.SettingsWeeksRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

class SettingsWeeksViewModel @Inject constructor(
    @DispatcherIO private val dispatcherIO: CoroutineDispatcher,
    private val repo: SettingsWeeksRepository
) : BaseViewModel(){

    private val _settingsElements = MutableLiveData<List<SettingsWeeksElements>>()
    val settingsElements: LiveData<List<SettingsWeeksElements>> = _settingsElements

    init {
        getSettingsList()
    }

    private fun getSettingsList() {
        viewModelScope.launch(dispatcherIO){
            val weeks = repo.getWeeks().sortedBy { it.sequence }
            val settingsElements = mutableListOf<SettingsWeeksElements>().apply {
                add(SettingsWeeksElements.Title())
                addAll(weeks)
                add(SettingsWeeksElements.AddNewWeek())
            }
            _settingsElements.postValue(settingsElements)
        }
    }

    fun addNewWeek(name: String?){
        viewModelScope.launch(dispatcherIO){
            name?.let {
                repo.saveNewWeek(SettingsWeeksElements.Week(
                    name = it,
                    sequence = findNextWeekPosition()
                ))
                getSettingsList()
            } ?: postMessage(R.string.week_name_not_provided)
        }
    }

    private fun findNextWeekPosition(): Int =
            settingsElements.value?.let { settings ->
                settings.findLast { it is SettingsWeeksElements.Week }?.let { (it as SettingsWeeksElements.Week).sequence + 1 }
            } ?: 1

}