package com.zywczas.myworkout.watch.activities.trainingplan.changeweight.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zywczas.common.di.modules.DispatchersModule.DispatcherIO
import com.zywczas.common.utils.SingleLiveData
import com.zywczas.myworkout.watch.activities.trainingplan.changeweight.domain.ChangeWeightRepository
import com.zywczas.myworkout.watch.activities.trainingplan.changeweight.domain.Exercise
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

class ChangeWeightViewModel @Inject constructor(
 @DispatcherIO private val dispatcherIO: CoroutineDispatcher,
 private val repo: ChangeWeightRepository
) : ViewModel() {

    private val _exercise = MediatorLiveData<Exercise>()
    val exercise: LiveData<Exercise> = _exercise

    private val _isWeightSaved = SingleLiveData<Boolean>()
    val isWeightSaved: LiveData<Boolean> = _isWeightSaved

    fun getCurrentExercise(id: Long){
        viewModelScope.launch(dispatcherIO){
            _exercise.postValue(repo.getExercise(id))
        }
    }

    fun saveWeight(weightString: String){
        viewModelScope.launch(dispatcherIO){
            val weight: Double =
                if (weightString.isBlank()){
                    0.0
                } else {
                    weightString.toDouble()
                }
            repo.saveWeight(weight)
            _isWeightSaved.postValue(true)
        }
    }

}