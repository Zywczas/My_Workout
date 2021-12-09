package com.zywczas.databasestore.trainings.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.zywczas.databasestore.trainings.entities.WeekEntity
import com.zywczas.databasestore.trainings.relations.WeekRelations

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

//    @Transaction
//    @Query("SELECT * FROM Week WHERE id = :id")
//    suspend fun getWeekRelations(id: Long): WeekEntity

    @Query("DELETE FROM Week WHERE id = :id") //todo sprawdzic czy tu nie powinno byc samo @Delete
    suspend fun deleteWeek(id: Long)

}