package com.zywczas.databasestore.trainings.dao

import androidx.room.*
import com.zywczas.databasestore.trainings.entities.DayEntity
import com.zywczas.databasestore.trainings.relations.DayRelations
import com.zywczas.databasestore.trainings.relations.WeekRelations

@Dao
internal interface DayDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(model: DayEntity): Long

    @Query("SELECT * FROM Day WHERE foreignWeekId = :weekId")
    suspend fun getDays(weekId: Long): List<DayEntity>

    @Query("SELECT * FROM Day WHERE id = :id")
    suspend fun getDay(id: Long): DayEntity

    @Transaction
    @Query("SELECT * FROM Day WHERE id = :id")
    suspend fun getDayRelations(id: Long): DayRelations

    @Query("DELETE FROM Day WHERE id = :id")
    suspend fun deleteDay(id: Long)

    @Query("SELECT id FROM Day WHERE foreignWeekId = :weekId")
    suspend fun getDaysIdsOfTheWeek(weekId: Long): List<Long>

}