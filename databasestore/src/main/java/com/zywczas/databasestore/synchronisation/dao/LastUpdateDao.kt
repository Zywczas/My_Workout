package com.zywczas.databasestore.synchronisation.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.zywczas.databasestore.synchronisation.entities.LastUpdateLocal

@Dao
interface LastUpdateDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(model: LastUpdateLocal)

}