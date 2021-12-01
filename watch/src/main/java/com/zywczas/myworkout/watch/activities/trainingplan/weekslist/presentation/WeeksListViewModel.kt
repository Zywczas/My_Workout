package com.zywczas.myworkout.watch.activities.trainingplan.weekslist.presentation

import androidx.lifecycle.ViewModel
import com.zywczas.common.di.modules.DispatchersModule.DispatcherIO
import com.zywczas.myworkout.watch.activities.trainingplan.weekslist.domain.WeeksListRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class WeeksListViewModel
@Inject constructor(private val repo: WeeksListRepository,
                    @DispatcherIO private val dispatcherIO: CoroutineDispatcher) : ViewModel() {


    val cos = ""

}