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

    private val _isTimerButtonVisible = MutableLiveData<Boolean>()
    val isTimerButtonVisible: LiveData<Boolean> = _isTimerButtonVisible

    val isFinishExerciseButtonVisible: LiveData<Boolean> = Transformations.switchMap(isTimerButtonVisible){
        liveData(dispatcherIO) {
            emit(it.not())
        }
    }

    //jezeli jest to ostatnie cwiczenie to zamiast guzika timer powinno byc cos ala Zakoncz sesje i po kliknieciu przechodzi do widoku dnia ale najpierw
    // zaznacza
    //cwiczenie jako zrobione

    fun getCurrentExercise(id: Long){
        viewModelScope.launch(dispatcherIO){
            _exercise.postValue(repo.getExercise(id))
        }
    }

    fun getTimerButtonVisibility(){
        viewModelScope.launch(dispatcherIO){
            //todo
        }
    }

    fun startBreakTimeCounter(){
        viewModelScope.launch(dispatcherIO){

        }
    }

}