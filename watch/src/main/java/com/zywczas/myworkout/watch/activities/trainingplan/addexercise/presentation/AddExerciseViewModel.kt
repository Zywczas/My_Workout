package com.zywczas.myworkout.watch.activities.trainingplan.addexercise.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.zywczas.common.di.modules.DispatchersModule.DispatcherIO
import com.zywczas.common.utils.SingleLiveData
import com.zywczas.myworkout.watch.R
import com.zywczas.myworkout.watch.activities.BaseViewModel
import com.zywczas.myworkout.watch.activities.trainingplan.addexercise.domain.AddExerciseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddExerciseViewModel @Inject constructor(
    @DispatcherIO private val dispatcherIO: CoroutineDispatcher,
    private val repo: AddExerciseRepository
) : BaseViewModel() {

    private val _isExerciseAdded = SingleLiveData<Boolean>()
    val isExerciseAdded: LiveData<Boolean> = _isExerciseAdded

    fun addExercise(dayId: Long, name: String, sets: String, reps: String, weight: String){
        //todo sprawdzic czy jak te wartosci sa nie wpisane to czy zwraca pusty string
        viewModelScope.launch(dispatcherIO){
            if (sets.isBlank() || reps.isBlank() || weight.isBlank()){
                postMessage(R.string.empty_fields)
                //todo dac sprawdzenie czy ilosc serii nie jest zero, jesli jest to wyswietlic blad
            } else {
                repo.saveExercise(
                    dayId = dayId,
                    name = name,
                    sets = sets.toInt(),
                    reps = reps,
                    weight = weight.toDouble()
                )
                _isExerciseAdded.postValue(true)
            }
        }
    }

}