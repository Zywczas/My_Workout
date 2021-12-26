package com.zywczas.myworkout.watch.activities.trainingplan.exercise.presentation

import androidx.lifecycle.*
import com.zywczas.common.di.modules.DispatchersModule.DispatcherIO
import com.zywczas.myworkout.watch.activities.trainingplan.exercise.domain.Exercise
import com.zywczas.myworkout.watch.activities.trainingplan.exercise.domain.ExerciseRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

class ExerciseViewModel @Inject constructor(
    @DispatcherIO private val dispatcherIO: CoroutineDispatcher,
    private val repo: ExerciseRepository
) : ViewModel(){

    private val _exercise = MutableLiveData<Exercise>()
    val exercise: LiveData<Exercise> = _exercise

    val isTimerButtonVisible: LiveData<Boolean> = Transformations.switchMap(exercise){ currentExercise ->
        liveData(dispatcherIO){
            val lastExercise = repo.getExercises(currentExercise.foreignDayId).maxByOrNull { it.sequence }
            val isButtonVisible = currentExercise.id != lastExercise?.id && currentExercise.currentSet != lastExercise?.currentSet
            emit(isButtonVisible)
        }
    }

    val isFinishExerciseButtonVisible: LiveData<Boolean> = Transformations.switchMap(isTimerButtonVisible){
        liveData(dispatcherIO) {
            emit(it.not())
        }
    }

    fun getCurrentExercise(id: Long){
        viewModelScope.launch(dispatcherIO){
            _exercise.postValue(repo.getExercise(id))
        }
    }

    fun startBreakTimeCounter(){
        viewModelScope.launch(dispatcherIO){

        }
    }

}