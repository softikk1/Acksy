package dev.softikk.acksy.domain.repository

import dev.softikk.acksy.domain.models.ErrorModel
import dev.softikk.acksy.domain.models.Response
import dev.softikk.acksy.domain.models.habits.HabitDetailsModel
import dev.softikk.acksy.domain.models.habits.HabitModel
import dev.softikk.acksy.domain.models.habits.HabitScheduleModel
import kotlin.uuid.Uuid

interface HabitsRepository {
    suspend fun getHabits(
        userId: Uuid, timeZone: String
    ): Response<List<HabitModel>, ErrorModel>

    suspend fun createHabit(
        title: String, schedule: HabitScheduleModel
    ): Response<Unit, ErrorModel>

    suspend fun confirmHabit(habitId: Uuid): Response<Unit, ErrorModel>
    suspend fun deleteHabit(habitId: Uuid): Response<Unit, ErrorModel>
    suspend fun habitDetails(habitId: Uuid): Response<HabitDetailsModel, ErrorModel>
    suspend fun deleteConfirmHabit(
        habitId: Uuid, confirmId: Uuid
    ): Response<Unit, ErrorModel>
}