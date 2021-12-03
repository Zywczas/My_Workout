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
import com.zywczas.myworkout.watch.adapters.WeekItem
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import org.joda.time.DateTime
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
            val weeks = items.sortedBy { it.sequence }
//            val weeks = repo.getWeeks().sortedBy { it.sequence }
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

        val items = listOf(
                Week(id = 222,
                     name = "tydzien 2",
                     sequence = 2,
                     dateStarted = DateTime(),
                     dateFinished = null,
                     isFinished = false),
                Week(id = 44,
                     name = "tydzien 4",
                     sequence = 4,
                     dateStarted = DateTime(),
                     dateFinished = DateTime(),
                     isFinished = false),
                Week(id = 2442,
                     name = "tydzien 1",
                     sequence = 1,
                     dateStarted = DateTime(),
                     dateFinished = DateTime(),
                     isFinished = true),
                Week(id = 3342,
                     name = "tydzien 3",
                     sequence = 3,
                     dateStarted = null,
                     dateFinished = DateTime(),
                     isFinished = true),
        )

}