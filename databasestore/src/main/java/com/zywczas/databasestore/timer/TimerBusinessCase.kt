package com.zywczas.databasestore.timer

import com.zywczas.databasestore.timer.entities.TimerEntity

interface TimerBusinessCase {

    suspend fun getTimer(): TimerEntity
//todo przy ustawianiu timera dac jakies maximum w stylu 9:59, zebym nie musial wyswietlac dwoch cyfr dla minut
    suspend fun saveBreakPeriod(seconds: Int)

}