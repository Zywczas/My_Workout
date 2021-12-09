package com.zywczas.common.extetions

import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat

fun DateTime.dayFormat(): String = DateTimeFormat.forPattern("dd.MM").print(this)

fun DateTime.timerFormat(): String = DateTimeFormat.forPattern("m:ss").print(this)