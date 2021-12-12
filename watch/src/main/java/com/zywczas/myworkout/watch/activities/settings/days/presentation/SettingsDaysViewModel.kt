package com.zywczas.myworkout.watch.activities.settings.days.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zywczas.common.di.modules.DispatchersModule.DispatcherIO
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

class SettingsDaysViewModel @Inject constructor(
    @DispatcherIO private val dispatcherIO: CoroutineDispatcher
) : ViewModel(){




    fun getSettingsList(weekId: Long){
        viewModelScope.launch(dispatcherIO){

        }
    }


}