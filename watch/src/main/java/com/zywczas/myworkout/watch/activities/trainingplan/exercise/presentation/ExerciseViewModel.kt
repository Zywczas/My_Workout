package com.zywczas.myworkout.watch.activities.trainingplan.exercise.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

    fun getCurrentExercise(id: Long){
        viewModelScope.launch(dispatcherIO){
            _exercise.postValue(repo.getCurrentExercise(id))
        }
    }

}