package com.zywczas.databasestore.trainings

import com.zywczas.common.utils.DateTimeProvider
import com.zywczas.databasestore.synchronisation.SynchronisationBusinessCase
import com.zywczas.databasestore.trainings.dao.DayDao
import com.zywczas.databasestore.trainings.dao.ExerciseDao
import com.zywczas.databasestore.trainings.dao.WeekDao
import com.zywczas.databasestore.trainings.entities.DayLocal
import com.zywczas.databasestore.trainings.entities.ExerciseLocal
import com.zywczas.databasestore.trainings.entities.WeekLocal
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

    override suspend fun getWeeks(): List<WeekLocal> = weekDao.getWeeks()

    override suspend fun saveNewWeek(name: String) {
        weekDao.insert(
            WeekLocal(
                name = name,
                sequence = findNextWeekPosition(),
            )
        )
        synchronisation.updateDatabaseTimeStamp()
    }

    //todo poprawic tak zeby bralo z sql
    private suspend fun findNextWeekPosition(): Int = weekDao.getWeeks().maxByOrNull { it.sequence }?.let { it.sequence + 1 } ?: 1

    override suspend fun getWeek(id: Long): WeekLocal = weekDao.getWeek(id)

    override suspend fun getDays(weekId: Long): List<DayLocal> = dayDao.getDays(weekId)

    override suspend fun saveDay(day: DayLocal) {
        dayDao.insert(day)
        synchronisation.updateDatabaseTimeStamp()
    }

    override suspend fun copyWeekAndTrainings(weekId: Long) {
        val weekRelationsToCopy = weekDao.getWeekRelations(weekId)
        val newWeekRelations = copyWeekRelations(weekRelationsToCopy)
        saveWeekRelations(newWeekRelations)
        synchronisation.updateDatabaseTimeStamp()
    }

    private suspend fun copyWeekRelations(oldWeekRelations: WeekRelations): WeekRelations {
        val newWeek = WeekLocal(
            name = oldWeekRelations.week.name,
            sequence = findNextWeekPosition(),
            copyVersion = findNextWeekCopyVersion(oldWeekRelations.week.name)
        )
        return WeekRelations(
            week = newWeek,
            days = copyDaysRelationsForNewWeek(oldWeekRelations.days)
        )
    }

    //todo wrzucic to w SQL
    private suspend fun findNextWeekCopyVersion(nameOfWeekToBeCopied: String): Int =
        weekDao.getWeeks()
            .filter { it.name == nameOfWeekToBeCopied }
            .maxByOrNull { it.sequence }
            ?.let { it.copyVersion + 1 } ?: 1

    private fun copyDaysRelationsForNewWeek(oldDaysRelations: List<DayRelations>): List<DayRelations> {
        val copiedDaysRelations = mutableListOf<DayRelations>()
        oldDaysRelations.forEach {
            val newDay = DayLocal(
                name = it.day.name,
                sequence = it.day.sequence
            )
            val newDayRelation = DayRelations(
                day = newDay,
                exercises = copyExercisesForNewDay(it.exercises)
            )
            copiedDaysRelations.add(newDayRelation)
        }
        return copiedDaysRelations
    }

    private fun copyExercisesForNewDay(oldExercises: List<ExerciseLocal>): List<ExerciseLocal> {
        val copiedExercises = mutableListOf<ExerciseLocal>()
        oldExercises.forEach {
            val newExercise = ExerciseLocal(
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

    private suspend fun saveWeekRelations(weekRelations: WeekRelations) {
        val newWeekId = weekDao.insert(weekRelations.week)
        weekRelations.days.forEach {
            saveDayRelations(it, newWeekId)
        }
    }

    private suspend fun saveDayRelations(dayRelation: DayRelations, newWeekId: Long) {
        dayRelation.day.foreignWeekId = newWeekId
        val newDayId = dayDao.insert(dayRelation.day)
        saveExercises(newDayId, dayRelation.exercises)
    }

    private suspend fun saveExercises(dayId: Long, exercises: List<ExerciseLocal>) {
        exercises.forEach {
            it.foreignDayId = dayId
            exerciseDao.insert(it)
        }
    }

    override suspend fun getExercises(dayId: Long): List<ExerciseLocal> = exerciseDao.getExercises(dayId)

    override suspend fun getDay(id: Long): DayLocal = dayDao.getDay(id)

    override suspend fun saveNewExercise(dayId: Long, name: String, sets: Int, reps: String, weight: Double) {
        exerciseDao.insert(
            ExerciseLocal(
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

    override suspend fun copyDayAndTrainingsInTheSameWeek(dayId: Long) {
        val dayRelationToBeCopied = dayDao.getDayRelations(dayId)
        val newDay = DayLocal(
            foreignWeekId = dayRelationToBeCopied.day.foreignWeekId,
            name = dayRelationToBeCopied.day.name,
            sequence = findNextDayPosition(dayRelationToBeCopied.day.foreignWeekId)
        )
        val newDayRelation = DayRelations(
            day = newDay,
            exercises = copyExercisesForNewDay(dayRelationToBeCopied.exercises)
        )
        saveDayRelations(newDayRelation, dayRelationToBeCopied.day.foreignWeekId)
        synchronisation.updateDatabaseTimeStamp()
    }

    //todo wrzucic to w SQL
    private suspend fun findNextDayPosition(weekId: Long): Int = dayDao.getDays(weekId).maxByOrNull { it.sequence }?.let { it.sequence + 1 } ?: 1

    override suspend fun getWeekByExerciseId(exerciseId: Long): WeekLocal {
        val exercise = exerciseDao.getExercise(exerciseId)
        val day = dayDao.getDay(exercise.foreignDayId)
        return weekDao.getWeek(day.foreignWeekId)
    }

    override suspend fun getExercise(id: Long): ExerciseLocal = exerciseDao.getExercise(id)

    override suspend fun saveExercise(exercise: ExerciseLocal) {
        exerciseDao.insert(exercise)
        synchronisation.updateDatabaseTimeStamp()
    }

    override suspend fun markExerciseAsFinished(id: Long) {
        val exercise = exerciseDao.getExercise(id).apply {
            isFinished = true
        }
        exerciseDao.insert(exercise)
        synchronisation.updateDatabaseTimeStamp()
    }

    override suspend fun markDayAsStarted(id: Long) {
        val day = dayDao.getDay(id).apply {
            dateStarted = dateTime.now()
        }
        dayDao.insert(day)
        synchronisation.updateDatabaseTimeStamp()
    }

    override suspend fun markWeekAsStartedIfNotStarted(dayId: Long) {
        val day = dayDao.getDay(dayId)
        val week = weekDao.getWeek(day.foreignWeekId)
        if (week.dateStarted == null) {
            week.dateStarted = dateTime.now()
            weekDao.insert(week)
            synchronisation.updateDatabaseTimeStamp()
        }
    }

    override suspend fun markDayAsFinished(id: Long) {
        val day = dayDao.getDay(id).apply {
            dateFinished = dateTime.now()
        }
        dayDao.insert(day)
        synchronisation.updateDatabaseTimeStamp()
    }

    override suspend fun markWeekAsFinished(id: Long) {
        val week = weekDao.getWeek(id).apply {
            dateFinished = dateTime.now()
        }
        weekDao.insert(week)
        synchronisation.updateDatabaseTimeStamp()
    }

    override suspend fun saveWeight(exerciseId: Long, weight: Double) {
        val exercise = exerciseDao.getExercise(exerciseId).apply {
            this.weight = weight
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

    override suspend fun isDayStarted(id: Long): Boolean = dayDao.getDay(id).dateStarted != null

}