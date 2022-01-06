package com.zywczas.databasestore.trainings.dao

import androidx.room.*
import com.zywczas.databasestore.trainings.entities.WeekEntity
import com.zywczas.databasestore.trainings.relations.WeekRelations

@Dao
internal interface WeekDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(model: WeekEntity): Long

    @Query("SELECT * FROM Week")
    suspend fun getWeeks(): List<WeekEntity>

    @Query("SELECT * FROM Week WHERE id = :id")
    suspend fun getWeek(id: Long): WeekEntity

    @Transaction
    @Query("SELECT * FROM Week WHERE id = :id")
    suspend fun getWeekRelations(id: Long): WeekRelations

    @Query("DELETE FROM Week WHERE id = :id")
    suspend fun deleteWeek(id: Long)

}