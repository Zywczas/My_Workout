package com.zywczas.databasestore.trainings.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.zywczas.databasestore.trainings.entities.CardioEntity

@Dao
internal interface CardioDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(model: CardioEntity): Long

    @Query("DELETE FROM Cardio WHERE id = :id")
    suspend fun deleteCardio(id: Long)

}