package com.zywczas.myworkout.watch.activities.trainingplan.day.presentation

import androidx.lifecycle.*
import com.zywczas.common.di.modules.DispatchersModule.DispatcherIO
import com.zywczas.common.extetions.dayFormat
import com.zywczas.common.utils.SingleLiveData
import com.zywczas.common.utils.StringProvider
import com.zywczas.myworkout.watch.R
import com.zywczas.myworkout.watch.activities.BaseViewModel
import com.zywczas.myworkout.watch.activities.trainingplan.day.domain.DayElements
import com.zywczas.myworkout.watch.activities.trainingplan.day.domain.DayRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

class DayViewModel @Inject constructor(
    @DispatcherIO private val dispatcherIO: CoroutineDispatcher,
    private val repo: DayRepository,
    private val stringProvider: StringProvider
) : BaseViewModel() {

    private val _dayElements = MutableLiveData<List<DayElements>>()
    val dayElements: LiveData<List<DayElements>> = _dayElements

    val isEmptyPlanMessageGone: LiveData<Boolean> = Transformations.switchMap(dayElements) { dayElements ->
        liveData(dispatcherIO) {
            emit(dayElements.isNotEmpty())
        }
    }

    private val _newExerciseName = SingleLiveData<String>()
    val newExerciseName: LiveData<String> = _newExerciseName

    private val _nextExerciseId = SingleLiveData<Long>()
    val nextExerciseId: LiveData<Long> = _nextExerciseId

    fun getExerciseList(dayId: Long) {
        viewModelScope.launch(dispatcherIO) {
            val exercises = repo.getExercises(dayId).sortedBy { it.sequence }
            if (exercises.isNotEmpty()) {
                val dayHeader = repo.getDayHeader(dayId).withDisplayedDate()
                val dayElements = mutableListOf<DayElements>().apply {
                    if (dayHeader.displayedDate.isNotBlank()) { //todo poprawic na inna nazwe funkcji
                        add(dayHeader)
                    }
                    if (dayHeader.dateFinished == null) {
                        add(DayElements.GoToExercise(title =
                                if (dayHeader.dateStarted != null) {
                                    R.string.continue_exercise
                                } else {
                                    R.string.start_exercise
                                }
                            )
                        )
                    }
                    addAll(exercises)
                    if (repo.isCardioDone(dayId)){
                        add(DayElements.Cardio())
                    }
                    add(DayElements.AddNewExercise())
                    add(DayElements.AddCardio())
                    add(DayElements.CopyDay())
                }
                _dayElements.postValue(dayElements)
            } else {
                _dayElements.postValue(emptyList())
            }
        }
    }

    private suspend fun DayElements.DayHeader.withDisplayedDate(): DayElements.DayHeader {
        if (dateFinished != null) {
            displayedDate = stringProvider.getString(R.string.done, dateFinished.dayFormat())
        } else if (dateStarted != null) {
            displayedDate = stringProvider.getString(R.string.started, dateStarted.dayFormat())
        }
        return this
    }

    fun addNewExercise(name: String?) {
        viewModelScope.launch(dispatcherIO) {
            name?.let {
                _newExerciseName.postValue(it)
            } ?: postMessage(R.string.exercise_name_not_provided)
        }
    }

    fun goToNextExercise() {
        viewModelScope.launch(dispatcherIO) {
            dayElements.value?.let {
                val nextExerciseId = (it.find { it is DayElements.Exercise && it.isFinished.not() } as? DayElements.Exercise)?.id
                nextExerciseId?.let { _nextExerciseId.postValue(it) }
            }
        }
    }

    fun addCardio(dayId: Long){
        viewModelScope.launch(dispatcherIO){
            showProgressBar(true)
            repo.addCardio(dayId)
            getExerciseList(dayId)
            showProgressBar(false)
        }
    }

    fun copyDay(id: Long) {
        viewModelScope.launch(dispatcherIO) {
            showProgressBar(true)
            repo.copyDayAndTrainings(id)
            postMessage(R.string.day_copied)
            showProgressBar(false)
        }
    }

}