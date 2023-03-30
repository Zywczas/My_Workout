package com.zywczas.databasestore.trainings.dao

import androidx.annotation.VisibleForTesting
import androidx.room.*
import com.zywczas.databasestore.trainings.entities.WeekLocal
import com.zywczas.databasestore.trainings.relations.WeekRelations

@Dao
@VisibleForTesting(otherwise = VisibleForTesting.PACKAGE_PRIVATE)
interface WeekDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(model: WeekLocal): Long

    @Query("SELECT * FROM Week")
    suspend fun getWeeks(): List<WeekLocal>

    @Query("SELECT * FROM Week WHERE id = :id")
    suspend fun getWeek(id: Long): WeekLocal

    @Transaction
    @Query("SELECT * FROM Week WHERE id = :id")
    suspend fun getWeekRelations(id: Long): WeekRelations

    @Query("DELETE FROM Week WHERE id = :id")
    suspend fun deleteWeek(id: Long)

}
