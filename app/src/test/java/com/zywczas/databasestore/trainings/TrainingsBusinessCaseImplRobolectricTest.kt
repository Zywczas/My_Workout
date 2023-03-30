package com.zywczas.databasestore.trainings

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.zywczas.common.utils.DateTimeProvider
import com.zywczas.databasestore.db.TrainingsDatabaseTest
import com.zywczas.databasestore.mockeddata.MockedDayLocal
import com.zywczas.databasestore.synchronisation.SynchronisationBusinessCase
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(RobolectricTestRunner::class)
class TrainingsBusinessCaseImplRobolectricTest : TrainingsDatabaseTest() {

    private val mockedDays = MockedDayLocal()
    private val dateTime: DateTimeProvider = mockk(relaxed = true)
    private val synchronisation: SynchronisationBusinessCase = mockk(relaxed = true)

    private lateinit var tested: TrainingsBusinessCaseImpl

    @Before
    fun setup() {
        tested = TrainingsBusinessCaseImpl(weekDao, dayDao, exerciseDao, dateTime, synchronisation)
    }

    @Test
    fun insertDay_getDay() = runTest {
        val expected = mockedDays.day1

        dayDao.insert(expected)
        val actual = dayDao.getDay(expected.id)

        assertThat(actual).isEqualTo(expected)
    }
}
