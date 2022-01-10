package com.zywczas.myworkout.watch.activities.trainingplan.timer.presentation

import androidx.lifecycle.*
import com.zywczas.common.di.modules.DispatchersModule.DispatcherIO
import com.zywczas.common.utils.DateTimeProvider
import com.zywczas.common.utils.SingleLiveData
import com.zywczas.myworkout.watch.activities.trainingplan.timer.domain.NextExercise
import com.zywczas.myworkout.watch.activities.trainingplan.timer.domain.TimerRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

class TimerViewModel @Inject constructor(
    @DispatcherIO private val dispatcherIO: CoroutineDispatcher,
    private val repo: TimerRepository,
    private val dateTime: DateTimeProvider
) : ViewModel() {

    private val _breakPeriodDisplayFormat = MutableLiveData<String>()
    val breakPeriodDisplayFormat: LiveData<String> = _breakPeriodDisplayFormat

    private val _breakPeriodInSeconds = SingleLiveData<Int>()
    val breakPeriodInSeconds: LiveData<Int> = _breakPeriodInSeconds

    private val _nextExercise = MutableLiveData<NextExercise>()
    val nextExercise: LiveData<NextExercise> = _nextExercise

    private val _nextExerciseId = SingleLiveData<Long>()
    val nextExerciseId: LiveData<Long> = _nextExerciseId

    val isExerciseLongDescriptionVisible: LiveData<Boolean> = Transformations.switchMap(nextExercise) {
        liveData(dispatcherIO) {
            emit(it.nextSet == 1)
        }
    }

    fun getBreakPeriod() {
        viewModelScope.launch {
            val breakPeriodString = dateTime.getTimerRepresentationOf(repo.getBreakPeriodInSeconds())
            _breakPeriodDisplayFormat.postValue(breakPeriodString)
        }
    }

    fun getExerciseDetails(id: Long, set: Int) {
        viewModelScope.launch(dispatcherIO) {
            val nextExercise = repo.getNextExercise(id).apply {
                nextSet = set
            }
            _nextExercise.postValue(nextExercise)
        }
    }

    fun startBreak() {
        viewModelScope.launch(dispatcherIO) {
            _breakPeriodInSeconds.postValue(repo.getBreakPeriodInSeconds())
        }
    }

    fun goToNextExercise() {
        viewModelScope.launch(dispatcherIO) {
            nextExercise.value?.let {
                repo.save(it)
                _nextExerciseId.postValue(it.id)
            }
        }
    }

}