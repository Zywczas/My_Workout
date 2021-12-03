package com.zywczas.myworkout.watch.activities.trainingplan.weekslist.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.zywczas.common.di.modules.DispatchersModule.DispatcherIO
import com.zywczas.common.extetions.dayFormat
import com.zywczas.myworkout.watch.activities.trainingplan.weekslist.domain.Week
import com.zywczas.myworkout.watch.activities.trainingplan.weekslist.domain.WeeksListRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

class WeeksListViewModel
@Inject constructor(private val repo: WeeksListRepository,
                    @DispatcherIO private val dispatcherIO: CoroutineDispatcher) : ViewModel() {

    private val _weeks = MutableLiveData<List<Week>>()
    val weeks: LiveData<List<Week>> = _weeks

    val isEmptyPlanMessageGone: LiveData<Boolean> = Transformations.switchMap(weeks) { weeks ->
        liveData(dispatcherIO) {
            emit(weeks.isNotEmpty())
        }
    }

    fun getPlannedWeeks() {
        viewModelScope.launch(dispatcherIO) {
            val weeks = repo.getWeeks().sortedBy { it.sequence }
            weeks.forEach {
                it.displayedDates =
                        when {
                            it.dateStarted != null && it.dateFinished != null -> "${it.dateStarted.dayFormat()}-${it.dateFinished.dayFormat()}"
                            it.dateStarted != null -> it.dateStarted.dayFormat()
                            else -> ""
                        }
            }
            _weeks.postValue(weeks)
        }
    }

}