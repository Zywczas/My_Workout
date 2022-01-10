package com.zywczas.myworkout.watch.services.timer

interface TimerServiceRepository { //todo przeniesc

    suspend fun getBreakPeriodInSeconds(): Int

}