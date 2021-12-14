package com.zywczas.myworkout.watch.activities.trainingplan.weekslist.presentation

import androidx.lifecycle.*
import com.zywczas.common.di.modules.DispatchersModule.DispatcherIO
import com.zywczas.common.extetions.dayFormat
import com.zywczas.myworkout.watch.R
import com.zywczas.myworkout.watch.activities.BaseViewModel
import com.zywczas.myworkout.watch.activities.trainingplan.weekslist.domain.WeeksElements
import com.zywczas.myworkout.watch.activities.trainingplan.weekslist.domain.WeeksListRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

class WeeksListViewModel @Inject constructor(
    private val repo: WeeksListRepository,
    @DispatcherIO private val dispatcherIO: CoroutineDispatcher
) : BaseViewModel() {

    private val _weeksElements = MutableLiveData<List<WeeksElements>>()
    val weeksElements: LiveData<List<WeeksElements>> = _weeksElements

    init {
        getWeeksList()
    }

    val isEmptyPlanMessageVisible: LiveData<Boolean> = Transformations.switchMap(weeksElements) { weeksElements ->
        liveData(dispatcherIO) {
            emit(weeksElements.isEmpty())
        }
    }
//todo dodac usuwanie jesli wiecej niz 5
    private fun getWeeksList() {
        viewModelScope.launch(dispatcherIO) {
            val weeks = repo.getWeeks().sortedByDescending { it.sequence }.withDisplayedDates()
            if (weeks.isNotEmpty()){
                val weeksElements = mutableListOf<WeeksElements>().apply {
                    add(WeeksElements.Title())
                    addAll(weeks)
                    add(WeeksElements.AddNewWeek())
                }
                _weeksElements.postValue(weeksElements)
            } else {
                _weeksElements.postValue(emptyList())
            }
        }
    }

    private fun List<WeeksElements.Week>.withDisplayedDates(): List<WeeksElements.Week> {
        forEach {
            if (it.dateStarted != null && it.dateFinished != null) {
                it.displayedDates = "${it.dateStarted.dayFormat()}-${it.dateFinished.dayFormat()}"
            } else if (it.dateStarted != null) {
                it.displayedDates = it.dateStarted.dayFormat()
            }
        }
        return this
    }

    fun addNewWeek(name: String?){
        viewModelScope.launch(dispatcherIO){
            name?.let {
                repo.saveNewWeek(WeeksElements.Week(
                    name = it,
                    sequence = findNextWeekPosition()
                ))
                getWeeksList()
            } ?: postMessage(R.string.week_name_not_provided)
        }
    }

    private fun findNextWeekPosition(): Int =
        weeksElements.value?.let { weeks ->
            weeks.find { it is WeeksElements.Week }?.let { (it as WeeksElements.Week).sequence + 1 }
        } ?: 1

}