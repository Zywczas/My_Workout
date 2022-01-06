package com.zywczas.myworkout.watch.activities.trainingplan.weekslist.presentation

import androidx.lifecycle.*
import com.zywczas.common.di.modules.DispatchersModule.DispatcherIO
import com.zywczas.common.extetions.dayFormat
import com.zywczas.common.utils.StringProvider
import com.zywczas.myworkout.watch.R
import com.zywczas.myworkout.watch.activities.BaseViewModel
import com.zywczas.myworkout.watch.activities.trainingplan.weekslist.domain.WeeksListElements
import com.zywczas.myworkout.watch.activities.trainingplan.weekslist.domain.WeeksListRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

class WeeksListViewModel @Inject constructor(
    private val repo: WeeksListRepository,
    @DispatcherIO private val dispatcherIO: CoroutineDispatcher,
    private val stringProvider: StringProvider
) : BaseViewModel() {

    private val _weeksListElements = MutableLiveData<List<WeeksListElements>>()
    val weeksListElements: LiveData<List<WeeksListElements>> = _weeksListElements

    val isEmptyPlanMessageVisible: LiveData<Boolean> = Transformations.switchMap(weeksListElements) { weeksListElements ->
        liveData(dispatcherIO) {
            emit(weeksListElements.isEmpty())
        }
    }
//todo dodac usuwanie jesli wiecej niz 5
    fun getWeeksList() {
        viewModelScope.launch(dispatcherIO) {
            val weeks = repo.getWeeks().sortedByDescending { it.sequence }.withDisplayedDates()
            if (weeks.isNotEmpty()){
                val weeksListElements = mutableListOf<WeeksListElements>().apply {
                    add(WeeksListElements.Title())
                    addAll(weeks)
                    add(WeeksListElements.AddNewWeek())
                }
                _weeksListElements.postValue(weeksListElements)
            } else {
                _weeksListElements.postValue(emptyList())
            }
        }
    }

    private suspend fun List<WeeksListElements.Week>.withDisplayedDates(): List<WeeksListElements.Week> {
        forEach {
            if (it.dateStarted != null && it.dateFinished != null) {
                it.displayedDates = "${it.dateStarted.dayFormat()}-${it.dateFinished.dayFormat()}"
            } else if (it.dateStarted != null) {
                it.displayedDates = stringProvider.getString(R.string.started, it.dateStarted.dayFormat())
            }
        }
        return this
    }

    fun addNewWeek(name: String?){
        viewModelScope.launch(dispatcherIO){
            name?.let {
                repo.saveNewWeek(it)
                getWeeksList()
            } ?: postMessage(R.string.week_name_not_provided)
        }
    }

}