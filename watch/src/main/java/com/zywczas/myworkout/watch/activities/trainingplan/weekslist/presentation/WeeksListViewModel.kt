package com.zywczas.myworkout.watch.activities.trainingplan.weekslist.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zywczas.common.di.modules.DispatchersModule.DispatcherIO
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

    fun getPlannedWeeks(){
        viewModelScope.launch(dispatcherIO){
            _weeks.postValue(repo.getWeeks())
        }
    }

}