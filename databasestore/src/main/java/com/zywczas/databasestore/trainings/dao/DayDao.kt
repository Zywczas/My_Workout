package com.zywczas.databasestore.trainings.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.zywczas.databasestore.trainings.entities.DayEntity

@Dao
internal interface DayDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(model: DayEntity): Long

    @Query("DELETE FROM Day WHERE id = :id")
    suspend fun deleteDay(id: Long)

    @Query("DELETE FROM Day WHERE foreignWeekId = :weekId")
    suspend fun deleteDays(weekId: Long)

}