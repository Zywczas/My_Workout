package com.zywczas.myworkout.watch.activities.trainingplan.week.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zywczas.common.di.modules.DispatchersModule.DispatcherIO
import com.zywczas.myworkout.watch.activities.trainingplan.week.domain.DaysElements
import com.zywczas.myworkout.watch.activities.trainingplan.week.domain.WeekRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

class WeekViewModel @Inject constructor(
    @DispatcherIO private val dispatcherIO: CoroutineDispatcher,
    private val repo: WeekRepository
) : ViewModel(){

    private val _daysElements = MutableLiveData<List<DaysElements>>()
    private val daysElements: LiveData<List<DaysElements>> = _daysElements

    fun getDaysList(weekId: Long){
        viewModelScope.launch(dispatcherIO){
            val week = repo.getWeek(weekId)
            val days = repo.getDays(weekId).sortedByDescending { it.sequence }.withDisplayedDate()
        }
    }

}