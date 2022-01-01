package com.zywczas.databasestore.trainings

import com.zywczas.common.utils.DateTimeProvider
import com.zywczas.databasestore.synchronisation.SynchronisationBusinessCase
import com.zywczas.databasestore.trainings.dao.DayDao
import com.zywczas.databasestore.trainings.dao.ExerciseDao
import com.zywczas.databasestore.trainings.dao.WeekDao
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
    private val exerciseDao: ExerciseDao,
    private val dateTime: DateTimeProvider,
    private val synchronisation: SynchronisationBusinessCase
) : TrainingsBusinessCase {

    override suspend fun getWeeks(): List<WeekEntity> = weekDao.getWeeks()

    override suspend fun saveNewWeek(name: String) {
        weekDao.insert(
            WeekEntity(
                name = name,
                sequence = findNextWeekPosition(),
            )
        )
        synchronisation.updateDatabaseTimeStamp()
    }

    //todo poprawic tak zeby bralo z sql
    private suspend fun findNextWeekPosition(): Int = weekDao.getWeeks().maxByOrNull { it.sequence }?.let { it.sequence + 1 } ?: 1

    override suspend fun getWeek(id: Long): WeekEntity = weekDao.getWeek(id)

    override suspend fun getDays(weekId: Long): List<DayEntity> = dayDao.getDays(weekId)

    override suspend fun saveDay(day: DayEntity) {
        dayDao.insert(day)
        synchronisation.updateDatabaseTimeStamp()
    }

    override suspend fun copyWeekAndTrainings(weekId: Long) {
        val weekRelationsToCopy = weekDao.getWeekRelations(weekId)
        val newWeekRelations = copyWeekRelations(weekRelationsToCopy)
        saveDays(newWeekRelations)
        synchronisation.updateDatabaseTimeStamp()
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
        weekRelations.days.forEach {
            it.day.foreignWeekId = newWeekId
            val newDayId = dayDao.insert(it.day)
            saveExercises(newDayId, it.exercises)
        }
    }

    private suspend fun saveExercises(dayId: Long, exercises: List<ExerciseEntity>) {
        exercises.forEach {
            it.foreignDayId = dayId
            exerciseDao.insert(it)
        }
    }

    override suspend fun getExercises(dayId: Long): List<ExerciseEntity> = exerciseDao.getExercises(dayId)

    override suspend fun getDay(id: Long): DayEntity = dayDao.getDay(id)

    override suspend fun saveNewExercise(dayId: Long, name: String, sets: Int, reps: String, weight: Double) {
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
        synchronisation.updateDatabaseTimeStamp()
    }

    //todo wrzucic to w SQL
    private suspend fun findNextExercisePosition(dayId: Long): Int = exerciseDao.getExercises(dayId).maxByOrNull { it.sequence }?.let { it.sequence + 1 } ?: 1

    override suspend fun copyDaysAndTrainings(dayId: Long) {
        val day = dayDao.getDayRelations(dayId)
        copyDaysRelations(listOf(day))
        synchronisation.updateDatabaseTimeStamp()
    }

    override suspend fun getWeekByExerciseId(exerciseId: Long): WeekEntity {
        val exercise = exerciseDao.getExercise(exerciseId)
        val day = dayDao.getDay(exercise.foreignDayId)
        return weekDao.getWeek(day.foreignWeekId)
    }

    override suspend fun getExercise(id: Long): ExerciseEntity = exerciseDao.getExercise(id)

    override suspend fun saveExercise(exercise: ExerciseEntity) {
        exerciseDao.insert(exercise)
        synchronisation.updateDatabaseTimeStamp()
    }

    override suspend fun markExerciseAsFinished(id: Long) {
        val exercise = exerciseDao.getExercise(id).apply {
            isFinished = true
            timeStamp = dateTime.now()
        }
        exerciseDao.insert(exercise)
        synchronisation.updateDatabaseTimeStamp()
    }

    override suspend fun markDayAsFinished(id: Long) {
        val day = dayDao.getDay(id).apply {
            isFinished = true
            timeStamp = dateTime.now()
        }
        dayDao.insert(day)
        synchronisation.updateDatabaseTimeStamp()
    }

    override suspend fun markWeekAsFinished(id: Long) {
        val week = weekDao.getWeek(id).apply {
            isFinished = true
            timeStamp = dateTime.now()
        }
        weekDao.insert(week)
        synchronisation.updateDatabaseTimeStamp()
    }

    override suspend fun saveWeight(exerciseId: Long, weight: Double) {
        val exercise = exerciseDao.getExercise(exerciseId).apply {
            this.weight = weight
            timeStamp = dateTime.now()
        }
        exerciseDao.insert(exercise)
        synchronisation.updateDatabaseTimeStamp()
    }

    override suspend fun isCardioDone(dayId: Long): Boolean = dayDao.getDay(dayId).isCardioDone

    override suspend fun addCardio(dayId: Long) {
        val day = dayDao.getDay(dayId).apply {
            isCardioDone = true
        }
        dayDao.insert(day)
        synchronisation.updateDatabaseTimeStamp()
    }

    override suspend fun deleteExercise(id: Long) {
        exerciseDao.deleteExercise(id)
        synchronisation.updateDatabaseTimeStamp()
    }

    override suspend fun deleteDay(id: Long) {
        exerciseDao.deleteExercises(dayId = id)
        dayDao.deleteDay(id)
        synchronisation.updateDatabaseTimeStamp()
    }

    override suspend fun deleteWeek(id: Long) {
        val dayIds = dayDao.getDaysIdsOfTheWeek(weekId = id)
        dayIds.forEach { dayId ->
            exerciseDao.deleteExercises(dayId)
            dayDao.deleteDay(dayId)
        }
        weekDao.deleteWeek(id)
        synchronisation.updateDatabaseTimeStamp()
    }

    override suspend fun getWeekId(dayId: Long): Long = dayDao.getDay(dayId).foreignWeekId

}