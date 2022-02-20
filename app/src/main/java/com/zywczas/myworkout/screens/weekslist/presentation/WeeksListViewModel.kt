package com.zywczas.myworkout.screens.weekslist.presentation

import androidx.lifecycle.ViewModel
import com.zywczas.common.di.modules.DispatchersModule.DispatcherIO
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

@HiltViewModel
class WeeksListViewModel @Inject constructor(
    @DispatcherIO dispatcherIO: CoroutineDispatcher
) : ViewModel() {


}