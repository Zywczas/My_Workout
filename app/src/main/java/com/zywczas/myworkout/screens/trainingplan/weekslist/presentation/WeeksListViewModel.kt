package com.zywczas.myworkout.screens.trainingplan.weekslist.presentation

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zywczas.common.di.modules.DispatchersModule.DispatcherIO
import com.zywczas.common.utils.StringProvider
import com.zywczas.myworkout.screens.trainingplan.weekslist.domain.Week
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeeksListViewModel @Inject constructor(
    @DispatcherIO private val dispatcherIO: CoroutineDispatcher,
    private val stringProvider: StringProvider
) : ViewModel() {

    var weeks = mutableStateListOf<Week>()
        private set

    fun getWeeksList() {
        viewModelScope.launch(dispatcherIO) {

        }
    }

}