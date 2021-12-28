package com.zywczas.common.utils

import com.zywczas.common.extetions.timerFormat
import org.joda.time.DateTime
import javax.inject.Inject

class DateTimeProviderImpl @Inject constructor() : DateTimeProvider {

    override suspend fun now(): DateTime = DateTime.now()

    override suspend fun getTimerRepresentationOf(seconds: Int): String = DateTime(seconds*1000L).timerFormat()

}