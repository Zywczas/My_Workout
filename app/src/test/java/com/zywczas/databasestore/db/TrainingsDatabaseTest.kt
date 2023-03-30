package com.zywczas.databasestore.db

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.zywczas.databasestore.trainings.dao.DayDao
import com.zywczas.databasestore.trainings.dao.ExerciseDao
import com.zywczas.databasestore.trainings.dao.WeekDao
import org.junit.After
import org.junit.Before

abstract class TrainingsDatabaseTest {

    private lateinit var trainingsDatabase: TrainingsDatabase
    protected lateinit var weekDao: WeekDao
    protected lateinit var dayDao: DayDao
    protected lateinit var exerciseDao: ExerciseDao

    @Before
    fun setupDatabase() {
        trainingsDatabase = Room
            .inMemoryDatabaseBuilder(ApplicationProvider.getApplicationContext(), TrainingsDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        weekDao = trainingsDatabase.weekDao()
        dayDao = trainingsDatabase.dayDao()
        exerciseDao = trainingsDatabase.exerciseDao()
    }

    @After
    fun tearDownDatabase() {
        trainingsDatabase.close()
    }
}
