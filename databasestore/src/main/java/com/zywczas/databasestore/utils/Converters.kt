package com.zywczas.databasestore.utils

import androidx.room.TypeConverter
import org.joda.time.DateTime

internal class Converters {

    @TypeConverter
    fun DateTime?.asTimeLong(): Long? = this?.millis

    @TypeConverter
    fun Long?.asDateTime(): DateTime? = this?.let { DateTime(it) }

}