package com.zywczas.myworkout.watch.activities.trainingplan.week.presentation

import androidx.lifecycle.*
import com.zywczas.common.di.modules.DispatchersModule.DispatcherIO
import com.zywczas.common.extetions.dayFormat
import com.zywczas.common.utils.StringProvider
import com.zywczas.myworkout.watch.R
import com.zywczas.myworkout.watch.activities.trainingplan.week.domain.DaysElements
import com.zywczas.myworkout.watch.activities.trainingplan.week.domain.WeekRepository
import com.zywczas.myworkout.watch.activities.trainingplan.weekslist.domain.WeeksElements
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

class WeekViewModel @Inject constructor(
    @DispatcherIO private val dispatcherIO: CoroutineDispatcher,
    private val repo: WeekRepository,
    private val stringProvider: StringProvider
) : ViewModel(){

    private val _daysElements = MutableLiveData<List<DaysElements>>()
    private val daysElements: LiveData<List<DaysElements>> = _daysElements

    val isEmptyPlanMessageVisible: LiveData<Boolean> = Transformations.switchMap(daysElements) { daysElements ->
        liveData(dispatcherIO) {
            emit(daysElements.isEmpty())
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

}