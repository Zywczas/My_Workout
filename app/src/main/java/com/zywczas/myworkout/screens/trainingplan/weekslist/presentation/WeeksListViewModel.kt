package com.zywczas.myworkout.screens.trainingplan.weekslist.presentation

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zywczas.common.di.modules.DispatchersModule.DispatcherIO
import com.zywczas.common.extetions.dayFormat
import com.zywczas.common.utils.StringProvider
import com.zywczas.myworkout.R
import com.zywczas.myworkout.screens.trainingplan.weekslist.domain.Week
import com.zywczas.myworkout.screens.trainingplan.weekslist.domain.WeeksListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeeksListViewModel @Inject constructor(
    private val repo: WeeksListRepository,
    @DispatcherIO private val dispatcherIO: CoroutineDispatcher,
    private val stringProvider: StringProvider
) : ViewModel() {

    var weeksList = mutableStateListOf<Week>()
        private set

    var isEmptyPlanMessageVisible = mutableStateOf(false)
        private set

    fun getWeeksList() {
        viewModelScope.launch(dispatcherIO) {
            val weeks = repo.getWeeks().sortedByDescending { it.sequence }
            keepOnlyLast5Weeks(weeks)
        }
    }
    
    private suspend fun keepOnlyLast5Weeks(weeks: List<Week>) {
        val weeksToBeDisplayed = weeks.take(5)
            .withCopyVersion()
            .withDisplayedDates()
        val weeksToBeDeleted = weeks.subtract(weeksToBeDisplayed.toSet())
        if (weeksToBeDisplayed.isNotEmpty()) {
            isEmptyPlanMessageVisible.value = false
            this@WeeksListViewModel.weeksList.clear()
            this@WeeksListViewModel.weeksList.addAll(weeksToBeDisplayed)
        } else {
            this@WeeksListViewModel.weeksList.clear()
            isEmptyPlanMessageVisible.value = true
        }
        weeksToBeDeleted.forEach { repo.deleteWeek(it.id) }
    }

    private fun List<Week>.withCopyVersion(): List<Week> {
        forEach {
            if (it.copyVersion > 1) {
                it.name = "${it.name} - ${it.copyVersion}"
            }
        }
        return this
    }

    private suspend fun List<Week>.withDisplayedDates(): List<Week> {
        forEach {
            if (it.dateStarted != null && it.dateFinished != null) {
                it.displayedDates = "${it.dateStarted.dayFormat()}-${it.dateFinished.dayFormat()}"
            } else if (it.dateStarted != null) {
                it.displayedDates = stringProvider.getString(R.string.started, it.dateStarted.dayFormat())
            }
        }
        return this
    }

//    fun addNewWeek(name: String?) { //todo dokonczyc
//        viewModelScope.launch(dispatcherIO){
//            name?.let {
//                repo.saveNewWeek(it)
//                getWeeksList()
//            } ?: postMessage(R.string.week_name_not_provided)
//        }
//    }

    private var i = 1

    fun addNewWeek() {
        viewModelScope.launch(dispatcherIO){
//            name?.let {
                repo.saveNewWeek("tydzien $i")
            i++
                getWeeksList()
//            }
        }
    }

}