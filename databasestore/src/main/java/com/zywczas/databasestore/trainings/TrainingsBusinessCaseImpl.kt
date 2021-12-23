package com.zywczas.databasestore.trainings

import com.zywczas.databasestore.trainings.dao.CardioDao
import com.zywczas.databasestore.trainings.dao.DayDao
import com.zywczas.databasestore.trainings.dao.ExerciseDao
import com.zywczas.databasestore.trainings.dao.WeekDao
import com.zywczas.databasestore.trainings.entities.CardioEntity
import com.zywczas.databasestore.trainings.entities.DayEntity
import com.zywczas.databasestore.trainings.entities.ExerciseEntity
import com.zywczas.databasestore.trainings.entities.WeekEntity
import com.zywczas.databasestore.trainings.relations.DayRelations
import com.zywczas.databasestore.trainings.relations.WeekRelations
import javax.inject.Inject

internal class TrainingsBusinessCaseImpl
@Inject constructor(
    private val weekDao: WeekDao,
    private val dayDao: DayDao,
    private val cardioDao: CardioDao,
    private val exerciseDao: ExerciseDao
) : TrainingsBusinessCase {

    override suspend fun getWeeks(): List<WeekEntity> = weekDao.getWeeks()

    override suspend fun saveNewWeek(name: String) {
        weekDao.insert(
            WeekEntity(
                name = name,
                sequence = findNextWeekPosition(),
            )
        )
    }

    //todo poprawic tak zeby bralo z sql
    private suspend fun findNextWeekPosition(): Int = weekDao.getWeeks().maxByOrNull { it.sequence }?.let { it.sequence + 1 } ?: 1

    override suspend fun getWeek(id: Long): WeekEntity = weekDao.getWeek(id)

    override suspend fun getDays(weekId: Long): List<DayEntity> = dayDao.getDays(weekId)

    override suspend fun saveDay(day: DayEntity) {
        dayDao.insert(day)
    }

    override suspend fun copyWeekAndTrainings(weekId: Long) {
        val weekRelationsToCopy = weekDao.getWeekRelations(weekId)
        val newWeekRelations = copyWeekRelations(weekRelationsToCopy)
        saveDays(newWeekRelations)
    }

    private suspend fun copyWeekRelations(oldWeekRelations: WeekRelations): WeekRelations {
        val newWeek = WeekEntity(
            name = oldWeekRelations.week.name,
            sequence = findNextWeekPosition()
        )
        return WeekRelations(
            week = newWeek,
            days = copyDaysRelations(oldWeekRelations.days)
        )
    }

    private fun copyDaysRelations(oldDaysRelations: List<DayRelations>): List<DayRelations> {
        val copiedDaysRelations = mutableListOf<DayRelations>()
        oldDaysRelations.forEach {
            val newDay = DayEntity(
                name = it.day.name,
                sequence = it.day.sequence
            )
            val newDayRelation = DayRelations(
                day = newDay,
                exercises = copyExercises(it.exercises)
            )
            copiedDaysRelations.add(newDayRelation)
        }
        return copiedDaysRelations
    }

    private fun copyExercises(oldExercises: List<ExerciseEntity>): List<ExerciseEntity> {
        val copiedExercises = mutableListOf<ExerciseEntity>()
        oldExercises.forEach {
            val newExercise = ExerciseEntity(
                name = it.name,
                sequence = it.sequence,
                setsQuantity = it.setsQuantity,
                repsQuantity = it.repsQuantity,
                weight = it.weight
            )
            copiedExercises.add(newExercise)
        }
        return copiedExercises
    }

    private suspend fun saveDays(weekRelations: WeekRelations) {
        val newWeekId = weekDao.insert(weekRelations.week)
        saveDays(newWeekId, weekRelations.days)
    }

    private suspend fun saveDays(weekId: Long, daysRelations: List<DayRelations>) {
        daysRelations.forEach {
            it.day.foreignWeekId = weekId
            val newDayId = dayDao.insert(it.day)
            saveExercises(newDayId, it.exercises)
            saveCardio(newDayId, it.cardio)
        }
    }

    private suspend fun saveExercises(dayId: Long, exercises: List<ExerciseEntity>) {
        exercises.forEach {
            it.foreignDayId = dayId
            exerciseDao.insert(it)
        }
    }

    private suspend fun saveCardio(dayId: Long, cardio: CardioEntity?): Long? = cardio?.let {
        cardio.foreignDayId = dayId
        cardioDao.insert(cardio)
    }

    override suspend fun getExercises(dayId: Long): List<ExerciseEntity> = exerciseDao.getExercises(dayId)

    override suspend fun getDay(id: Long): DayEntity = dayDao.getDay(id)

    override suspend fun saveExercise(dayId: Long, name: String, sets: Int, reps: String, weight: Double) {
        exerciseDao.insert(
            ExerciseEntity(
                foreignDayId = dayId,
                name = name,
                sequence = findNextExercisePosition(dayId),
                setsQuantity = sets,
                repsQuantity = reps,
                weight = weight
            )
        )
    }
//todo wrzucic to w SQL
    private suspend fun findNextExercisePosition(dayId: Long): Int = exerciseDao.getExercises(dayId).maxByOrNull { it.sequence }?.let { it.sequence + 1 } ?: 1

    override suspend fun copyDaysAndTrainings(dayId: Long) {
        val day = dayDao.getDayRelations(dayId)
        copyDaysRelations(listOf(day))
    }

}