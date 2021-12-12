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
import com.zywczas.myworkout.watch.activities.trainingplan.weekslist.domain.WeeksElements
import com.zywczas.myworkout.watch.activities.trainingplan.weekslist.domain.WeeksListRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

class WeeksListViewModel @Inject constructor(
    private val repo: WeeksListRepository,
    @DispatcherIO private val dispatcherIO: CoroutineDispatcher
) : ViewModel() {

    private val _weeksElements = MutableLiveData<List<WeeksElements>>()
    val weeksElements: LiveData<List<WeeksElements>> = _weeksElements

    init {
        getWeeksList()
    }

    val isEmptyPlanMessageGone: LiveData<Boolean> = Transformations.switchMap(weeksElements) { weeksElements ->
        liveData(dispatcherIO) {
            emit(weeksElements.firstOrNull { it is WeeksElements.Week }?.let { true } ?: false )
        }
    }

    private fun getWeeksList() {
        viewModelScope.launch(dispatcherIO) {
            val weeks = repo.getWeeks().sortedBy { it.sequence }.withSetDisplayedDates()
            if (weeks.isNotEmpty()){
                val weeksElements = mutableListOf<WeeksElements>().apply {
                    add(WeeksElements.Title(R.string.planned_trainings))
                    addAll(weeks)
                    add(WeeksElements.Settings())
                }
                _weeksElements.postValue(weeksElements)
            } else {
                _weeksElements.postValue(emptyList())
            }
        }
    }

    private fun List<WeeksElements.Week>.withSetDisplayedDates(): List<WeeksElements.Week> {
        forEach {
            it.displayedDates =
                when {
                    it.dateStarted != null && it.dateFinished != null -> "${it.dateStarted.dayFormat()}-${it.dateFinished.dayFormat()}"
                    it.dateStarted != null -> it.dateStarted.dayFormat()
                    else -> ""
                }
        }
        return this
    }

}