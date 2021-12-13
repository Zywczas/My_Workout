package com.zywczas.myworkout.watch.activities.trainingplan.week.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zywczas.common.di.modules.DispatchersModule.DispatcherIO
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

class WeekViewModel @Inject constructor(
    @DispatcherIO private val dispatcherIO: CoroutineDispatcher
) : ViewModel(){

    fun getDaysList(weekId: Long){
        viewModelScope.launch(dispatcherIO){

        }
    }

}