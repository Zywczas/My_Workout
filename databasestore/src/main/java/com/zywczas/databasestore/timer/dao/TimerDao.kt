package com.zywczas.databasestore.timer.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.zywczas.databasestore.timer.entities.TimerEntity

@Dao
internal interface TimerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(model: TimerEntity): Long

    @Query("SELECT * FROM Timer LIMIT 1")
    suspend fun getTimer(): TimerEntity?

}
