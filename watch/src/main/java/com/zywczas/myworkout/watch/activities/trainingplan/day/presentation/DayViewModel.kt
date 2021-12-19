package com.zywczas.myworkout.watch.activities.trainingplan.day.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.liveData
import com.zywczas.common.di.modules.DispatchersModule.DispatcherIO
import com.zywczas.myworkout.watch.activities.BaseViewModel
import com.zywczas.myworkout.watch.activities.trainingplan.day.domain.DayRepository
import com.zywczas.myworkout.watch.activities.trainingplan.week.domain.DaysElements
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class DayViewModel @Inject constructor(
    @DispatcherIO private val dispatcherIO: CoroutineDispatcher,
    private val repo: DayRepository,
) : BaseViewModel(){

    private val _daysElements = MutableLiveData<List<DaysElements>>()
    val daysElements: LiveData<List<DaysElements>> = _daysElements

    val isEmptyPlanMessageGone: LiveData<Boolean> = Transformations.switchMap(daysElements) { daysElements ->
        liveData(dispatcherIO) {
            emit(daysElements.isNotEmpty())
        }
    }

}