package com.zywczas.databasestore.trainings.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.zywczas.databasestore.trainings.entities.ExerciseLocal

@Dao
interface ExerciseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(model: ExerciseLocal): Long

    @Query("SELECT * FROM Exercise WHERE foreignDayId = :dayId")
    suspend fun getExercises(dayId: Long): List<ExerciseLocal>

    @Query("SELECT * FROM Exercise WHERE id = :id")
    suspend fun getExercise(id: Long): ExerciseLocal

    @Query("DELETE FROM Exercise WHERE id = :id")
    suspend fun deleteExercise(id: Long)

    @Query("DELETE FROM Exercise WHERE foreignDayId = :dayId")
    suspend fun deleteExercises(dayId: Long)

}
