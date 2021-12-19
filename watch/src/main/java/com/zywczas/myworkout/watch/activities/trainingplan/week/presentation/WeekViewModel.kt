package com.zywczas.myworkout.watch.activities.trainingplan.week.presentation

import androidx.lifecycle.*
import com.zywczas.common.di.modules.DispatchersModule.DispatcherIO
import com.zywczas.common.extetions.dayFormat
import com.zywczas.common.utils.StringProvider
import com.zywczas.myworkout.watch.R
import com.zywczas.myworkout.watch.activities.BaseViewModel
import com.zywczas.myworkout.watch.activities.trainingplan.week.domain.WeekElements
import com.zywczas.myworkout.watch.activities.trainingplan.week.domain.WeekRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

class WeekViewModel @Inject constructor(
    @DispatcherIO private val dispatcherIO: CoroutineDispatcher,
    private val repo: WeekRepository,
    private val stringProvider: StringProvider
) : BaseViewModel(){

    private val _weekElements = MutableLiveData<List<WeekElements>>()
    val weekElements: LiveData<List<WeekElements>> = _weekElements

    val isEmptyPlanMessageGone: LiveData<Boolean> = Transformations.switchMap(weekElements) { daysElements ->
        liveData(dispatcherIO) {
            emit(daysElements.isNotEmpty())
        }
    }

    fun getDaysList(weekId: Long){
        viewModelScope.launch(dispatcherIO){
            val days = repo.getDays(weekId).sortedByDescending { it.sequence }.withDisplayedDate()
            if (days.isNotEmpty()){
                val week = repo.getWeek(weekId).withDisplayedDate()
                val weekElements = mutableListOf<WeekElements>().apply {
                    add(week)
                    addAll(days)
                    add(WeekElements.AddNewDay())
                    add(WeekElements.CopyWeek())
                }
                _weekElements.postValue(weekElements)
            }
        }
    }

    private suspend fun List<WeekElements.Day>.withDisplayedDate(): List<WeekElements.Day> {
        forEach {
            if (it.dateFinished != null) {
                it.displayedDate = stringProvider.getString(R.string.done, it.dateFinished.dayFormat())
            }
        }
        return this
    }

    private suspend fun WeekElements.WeekHeader.withDisplayedDate(): WeekElements.WeekHeader {
        if (dateFinished != null) {
            displayedDate =  stringProvider.getString(R.string.done, dateFinished.dayFormat())
        } else if (dateStarted != null) {
            displayedDate =  stringProvider.getString(R.string.started, dateStarted.dayFormat())
        }
        return this
    }

    fun addNewDay(name: String?, weekId: Long) {
        viewModelScope.launch(dispatcherIO){
            name?.let {
                repo.saveNewDay(name = it, weekId = weekId, sequence = findNextDayPosition())
                getDaysList(weekId)
            } ?: postMessage(R.string.day_name_not_provided)
        }
    }

    private fun findNextDayPosition(): Int =
        weekElements.value?.let { days ->
            days.find { it is WeekElements.Day }?.let { (it as WeekElements.Day).sequence + 1 }
        } ?: 1

    fun copyWeek(id: Long){
        viewModelScope.launch(dispatcherIO){
            showProgressBar(true)
            repo.copyWeekAndTrainings(id)
            postMessage(R.string.week_copied)
            showProgressBar(false)
        }
    }

}