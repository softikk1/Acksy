package dev.softikk.acksy.data.repository

import dev.softikk.acksy.data.mappers.toHabitScheduleApp
import dev.softikk.acksy.data.mappers.toHabitScheduleModel
import dev.softikk.acksy.data.sources.HabitsRemoteSource
import dev.softikk.acksy.domain.models.ErrorModel
import dev.softikk.acksy.domain.models.Response
import dev.softikk.acksy.domain.models.habits.HabitDetailsModel
import dev.softikk.acksy.domain.models.habits.HabitModel
import dev.softikk.acksy.domain.models.habits.HabitScheduleModel
import dev.softikk.acksy.domain.repository.HabitsRepository
import kotlin.uuid.Uuid

class HabitsRepositoryImpl(private val habitsSource: HabitsRemoteSource) : HabitsRepository {
    override suspend fun getHabits(
        userId: Uuid, timeZone: String
    ): Response<List<HabitModel>, ErrorModel> {
        return when (val result = habitsSource.getHabits(
            userId = userId, timeZone = timeZone
        )) {
            is Response.Success -> {
                Response.Success(result.value.habits.map { habit ->
                    HabitModel(
                        id = habit.id,
                        title = habit.title,
                        createdAt = habit.createdAt,
                        schedule = habit.schedule.toHabitScheduleModel()
                    )
                })
            }

            is Response.Failed -> {
                result
            }
        }
    }

    override suspend fun createHabit(
        title: String, schedule: HabitScheduleModel
    ): Response<Unit, ErrorModel> {
        return habitsSource.createHabit(
            title = title, schedule = schedule.toHabitScheduleApp()
        )
    }

    override suspend fun confirmHabit(habitId: Uuid): Response<Unit, ErrorModel> {
        return habitsSource.confirmHabit(habitId)
    }

    override suspend fun deleteHabit(habitId: Uuid): Response<Unit, ErrorModel> {
        return habitsSource.deleteHabit(habitId)
    }

    override suspend fun habitDetails(habitId: Uuid): Response<HabitDetailsModel, ErrorModel> {
        return when (val result = habitsSource.habitDetails(habitId)) {
            is Response.Success -> {
                val habitDetails = result.value
                Response.Success(
                    HabitDetailsModel(
                        score = habitDetails.score,
                        total = habitDetails.total,
                        bestStreak = habitDetails.bestStreak,
                        lastStreak = habitDetails.lastStreak,
                        confirmsDates = habitDetails.confirmsDates
                    )
                )
            }

            is Response.Failed -> {
                result
            }
        }
    }

    override suspend fun deleteConfirmHabit(
        habitId: Uuid, confirmId: Uuid
    ): Response<Unit, ErrorModel> {
        return habitsSource.deleteConfirmHabit(habitId = habitId, confirmId = confirmId)
    }
}