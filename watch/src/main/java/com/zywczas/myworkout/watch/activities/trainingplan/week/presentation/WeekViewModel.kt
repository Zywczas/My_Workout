package com.zywczas.myworkout.watch.activities.trainingplan.week.presentation

import androidx.lifecycle.*
import com.zywczas.common.di.modules.DispatchersModule.DispatcherIO
import com.zywczas.common.extetions.dayFormat
import com.zywczas.common.utils.DateTimeProvider
import com.zywczas.common.utils.StringProvider
import com.zywczas.databasestore.trainings.entities.DayEntity
import com.zywczas.databasestore.trainings.entities.WeekEntity
import com.zywczas.databasestore.trainings.relations.DayRelations
import com.zywczas.databasestore.trainings.relations.WeekRelations
import com.zywczas.myworkout.watch.R
import com.zywczas.myworkout.watch.activities.BaseViewModel
import com.zywczas.myworkout.watch.activities.trainingplan.week.domain.DaysElements
import com.zywczas.myworkout.watch.activities.trainingplan.week.domain.WeekRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

class WeekViewModel @Inject constructor(
    @DispatcherIO private val dispatcherIO: CoroutineDispatcher,
    private val repo: WeekRepository,
    private val stringProvider: StringProvider,
    private val dateTime: DateTimeProvider
) : BaseViewModel(){

    private val _daysElements = MutableLiveData<List<DaysElements>>()
    val daysElements: LiveData<List<DaysElements>> = _daysElements

    val isEmptyPlanMessageGone: LiveData<Boolean> = Transformations.switchMap(daysElements) { daysElements ->
        liveData(dispatcherIO) {
            emit(daysElements.isNotEmpty())
        }
    }

    fun getDaysList(weekId: Long){
        viewModelScope.launch(dispatcherIO){
            val days = repo.getDays(weekId).sortedByDescending { it.sequence }.withDisplayedDate()
            if (days.isNotEmpty()){
                val week = repo.getWeek(weekId).withDisplayedDate()
                val daysElements = mutableListOf<DaysElements>().apply {
                    add(week)
                    addAll(days)
                    add(DaysElements.AddNewDay())
                    add(DaysElements.CopyWeek())
                }
                _daysElements.postValue(daysElements)
            }
        }
    }

    private suspend fun List<DaysElements.Day>.withDisplayedDate(): List<DaysElements.Day> {
        forEach {
            if (it.dateFinished != null) {
                it.displayedDate = stringProvider.getString(R.string.done, it.dateFinished.dayFormat())
            }
        }
        return this
    }

    private suspend fun DaysElements.WeekHeader.withDisplayedDate(): DaysElements.WeekHeader {
        if (dateFinished != null) {
            displayedDate =  stringProvider.getString(R.string.done, dateFinished.dayFormat())
        } else if (dateStarted != null) {
            displayedDate =  stringProvider.getString(R.string.started, dateStarted.dayFormat())
        }
        return this
    }

    fun addNewDay(name: String?, weekId: Long){
        viewModelScope.launch(dispatcherIO){
            name?.let {
                repo.saveNewDay(name = it, weekId = weekId, sequence = findNextDayPosition())
                getDaysList(weekId)
            } ?: postMessage(R.string.day_name_not_provided)
        }
    }

    private fun findNextDayPosition(): Int =
        daysElements.value?.let { days ->
            days.find { it is DaysElements.Day }?.let { (it as DaysElements.Day).sequence + 1 }
        } ?: 1

    fun copyWeek(id: Long){
        viewModelScope.launch(dispatcherIO){
            showProgressBar(true)
            repo.copyWeekAndTrainings(id)
            postMessage(...)
            showProgressBar(false)
        }
    }

}