package com.zywczas.databasestore.db

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import org.junit.After
import org.junit.Before

internal abstract class TrainingsDatabaseTest {

    lateinit var trainingsDatabase: TrainingsDatabase

    @Before
    fun setupDatabase() {
        trainingsDatabase = Room
            .inMemoryDatabaseBuilder(ApplicationProvider.getApplicationContext(), TrainingsDatabase::class.java)
            .allowMainThreadQueries()
            .build()
    }

    @After
    fun tearDownDatabase() {
        trainingsDatabase.close()
    }
}
