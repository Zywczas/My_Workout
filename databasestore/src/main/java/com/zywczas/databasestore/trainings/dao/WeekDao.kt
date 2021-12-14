package com.zywczas.databasestore.trainings.dao

import androidx.room.*
import com.zywczas.databasestore.trainings.entities.WeekEntity

@Dao
internal interface WeekDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(model: WeekEntity): Long

//todo moze nie potrzebna
//    @Transaction
//    @Query("SELECT * FROM Week")
//    suspend fun getWeekRelationsList(): List<WeekRelations>

    @Transaction
    @Query("SELECT * FROM Week")
    suspend fun getWeeks(): List<WeekEntity>

    @Query("SELECT * FROM Week WHERE id = :id")
    suspend fun getWeek(id: Long): WeekEntity

//    @Transaction
//    @Query("SELECT * FROM Week WHERE id = :id")
//    suspend fun getWeekRelations(id: Long): WeekEntity

    @Query("DELETE FROM Week WHERE id = :id") //todo sprawdzic czy tu nie powinno byc samo @Delete
    suspend fun deleteWeek(id: Long)

}