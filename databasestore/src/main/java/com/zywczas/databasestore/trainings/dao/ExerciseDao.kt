package com.zywczas.databasestore.trainings.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.zywczas.databasestore.trainings.entities.ExerciseEntity

@Dao
internal interface ExerciseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(model: ExerciseEntity): Long

    @Query("SELECT * FROM Exercise WHERE foreignDayId = :dayId")
    suspend fun getExercises(dayId: Long): List<ExerciseEntity>

    @Query("SELECT * FROM Exercise WHERE id = :id")
    suspend fun getExercise(id: Long): ExerciseEntity

    @Query("DELETE FROM Exercise WHERE id = :id")
    suspend fun deleteExercise(id: Long)

    @Query("DELETE FROM Exercise WHERE foreignDayId = :dayId")
    suspend fun deleteExercises(dayId: Long)

}