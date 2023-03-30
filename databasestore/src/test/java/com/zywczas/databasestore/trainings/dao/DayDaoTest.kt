package com.zywczas.databasestore.trainings.dao

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.zywczas.databasestore.db.TrainingsDatabaseTest
import com.zywczas.databasestore.mockeddata.MockedDayLocal
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@OptIn(ExperimentalCoroutinesApi::class)
internal class DayDaoTest : TrainingsDatabaseTest() {

    private lateinit var dao: DayDao
    private val mockedDays = MockedDayLocal()

    @Before
    fun setup() {
        dao = trainingsDatabase.dayDao()
    }

    @Test
    fun insertDay_getDay() = runTest {
        val expected = mockedDays.day1

        dao.insert(expected)
        val actual = dao.getDay(expected.id)

        assertThat(actual).isEqualTo(expected)
    }
}
