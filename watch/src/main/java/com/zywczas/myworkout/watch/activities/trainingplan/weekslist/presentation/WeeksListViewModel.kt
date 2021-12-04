package com.zywczas.myworkout.watch.activities.trainingplan.weekslist.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.zywczas.common.di.modules.DispatchersModule.DispatcherIO
import com.zywczas.common.extetions.dayFormat
import com.zywczas.myworkout.watch.R
import com.zywczas.myworkout.watch.activities.trainingplan.weekslist.domain.WeeksList
import com.zywczas.myworkout.watch.activities.trainingplan.weekslist.domain.WeeksListRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

class WeeksListViewModel @Inject constructor(
    private val repo: WeeksListRepository,
    @DispatcherIO private val dispatcherIO: CoroutineDispatcher
) : ViewModel() {

    private val _weeksList = MutableLiveData<List<WeeksList>>()
    val weeksList: LiveData<List<WeeksList>> = _weeksList

    val isEmptyPlanMessageGone: LiveData<Boolean> = Transformations.switchMap(weeksList) { weeks ->
        liveData(dispatcherIO) {
            emit(weeks.isNotEmpty())
        }
    }

    fun getPlannedWeeks() {
        viewModelScope.launch(dispatcherIO) {
            val weeksList = mutableListOf<WeeksList>()
            weeksList.add(WeeksList.Title(R.string.planned_trainings))
            val weeks = repo.getWeeks().sortedBy { it.sequence }
            weeks.forEach {
                it.displayedDates =
                    when {
                        it.dateStarted != null && it.dateFinished != null -> "${it.dateStarted.dayFormat()}-${it.dateFinished.dayFormat()}"
                        it.dateStarted != null -> it.dateStarted.dayFormat()
                        else -> ""
                    }
            }
            weeksList.addAll(weeks)
            weeksList.add(WeeksList.Settings)
            _weeksList.postValue(weeksList)
        }
    }

}