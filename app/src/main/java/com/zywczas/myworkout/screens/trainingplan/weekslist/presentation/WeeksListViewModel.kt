package com.zywczas.myworkout.screens.trainingplan.weekslist.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
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
    private val stringProvider: StringProvider,
) : ViewModel() {

    var weeksList by mutableStateOf<List<Week>>(emptyList())
        private set

    // It's not a derived state of weeksList to avoid text blinking on screen init
    var isEmptyPlanMessageVisible by mutableStateOf(false)
        private set

    var showAddNewWeekDialog by mutableStateOf(false)
    var isNewWeekNameError by mutableStateOf(false)

    fun displayWeeksList() {
        viewModelScope.launch(dispatcherIO) {
            keepOnlyLast5Weeks(repo.getWeeks())
        }
    }

    private suspend fun keepOnlyLast5Weeks(weeks: List<Week>) {
        val weeksToBeDisplayed = weeks
            .sortedByDescending { it.sequence }
            .take(5)
            .withCopyVersion()
            .withDisplayedDates()
        weeksList = weeksToBeDisplayed
        isEmptyPlanMessageVisible = weeksToBeDisplayed.isEmpty()
        val weeksToBeDeleted = weeks.subtract(weeksToBeDisplayed.toSet())
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

    fun addNewWeek(name: String) {
        viewModelScope.launch(dispatcherIO) {
            if (name.isBlank()) {
                isNewWeekNameError = true
            } else {
                showAddNewWeekDialog = false
                repo.saveNewWeek(name)
                displayWeeksList()
            }
        }
    }

    fun onAddNewWeekDialogTextChanged() {
        isNewWeekNameError = false
    }

    fun onAddNewWeekDialogDismissRequest() {
        showAddNewWeekDialog = false
        isNewWeekNameError = false
    }

    fun onPlusButtonClick() {
        showAddNewWeekDialog = true
    }
}
