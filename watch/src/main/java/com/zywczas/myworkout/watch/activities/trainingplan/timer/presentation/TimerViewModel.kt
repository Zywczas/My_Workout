package com.zywczas.myworkout.watch.activities.trainingplan.timer.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zywczas.common.di.modules.DispatchersModule.DispatcherIO
import com.zywczas.myworkout.watch.activities.trainingplan.timer.domain.TimerRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

class TimerViewModel @Inject constructor(
    @DispatcherIO private val dispatcherIO: CoroutineDispatcher,
    private val repo: TimerRepository
) : ViewModel(){

    fun getExerciseDetails(id: Long, set: Int){
        viewModelScope.launch(dispatcherIO){
            _exercise.postValue(repo.getExercise(id))
        }
    }

}