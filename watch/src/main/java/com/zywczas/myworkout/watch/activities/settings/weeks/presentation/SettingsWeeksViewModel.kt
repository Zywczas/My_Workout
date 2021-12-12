package com.zywczas.myworkout.watch.activities.settings.weeks.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zywczas.common.di.modules.DispatchersModule.DispatcherIO
import com.zywczas.myworkout.watch.activities.settings.weeks.domain.SettingsWeeksElements
import com.zywczas.myworkout.watch.activities.settings.weeks.domain.SettingsWeeksRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class SettingsWeeksViewModel @Inject constructor(
    @DispatcherIO private val dispatcherIO: CoroutineDispatcher,
    private val repo: SettingsWeeksRepository
) : ViewModel(){

    private val _settingsElements = MutableLiveData<List<SettingsWeeksElements>>()
    private val settingsElements: LiveData<List<SettingsWeeksElements>> = _settingsElements

    init {

    }


}