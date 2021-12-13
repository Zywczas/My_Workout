package com.zywczas.databasestore.trainings.dao

import androidx.room.*
import com.zywczas.databasestore.trainings.entities.DayEntity

@Dao
internal interface DayDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(model: DayEntity): Long

    @Transaction
    @Query("SELECT * FROM Day WHERE foreignWeekId = :weekId")
    suspend fun getDays(weekId: Long): List<DayEntity>

    @Query("DELETE FROM Day WHERE id = :id")//todo sprawdzic czy tu nie powinno byc samo @Delete
    suspend fun deleteDay(id: Long)

    @Query("DELETE FROM Day WHERE foreignWeekId = :weekId")
    suspend fun deleteDays(weekId: Long)

}