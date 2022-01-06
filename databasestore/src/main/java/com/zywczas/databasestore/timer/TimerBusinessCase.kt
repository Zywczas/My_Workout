package com.zywczas.databasestore.timer

import com.zywczas.databasestore.timer.entities.TimerLocal

interface TimerBusinessCase {

    suspend fun getTimer(): TimerLocal
//todo przy ustawianiu timera dac jakies maximum w stylu 9:59, zebym nie musial wyswietlac dwoch cyfr dla minut
    suspend fun saveBreakPeriod(seconds: Int)

}