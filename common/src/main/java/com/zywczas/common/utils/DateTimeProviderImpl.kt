package com.zywczas.common.utils

import org.joda.time.DateTime
import javax.inject.Inject

class DateTimeProviderImpl @Inject constructor() : DateTimeProvider {

    override suspend fun now(): DateTime = DateTime.now()

    override suspend fun year(): Int = DateTime.now().year

    override suspend fun lastYear(): Int = DateTime.now().year - 1

    override suspend fun nextYear(): Int = DateTime.now().year + 1

}