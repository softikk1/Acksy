package dev.softikk.acksy.data.sources

import dev.softikk.acksy.domain.models.ErrorModel
import dev.softikk.acksy.domain.models.Response
import dev.softikk.acksy.dev.softikk.acksy.entities.habits.GetHabitsRespondDto
import dev.softikk.acksy.dev.softikk.acksy.entities.habits.HabitDetailsRespondDto
import dev.softikk.acksy.dev.softikk.acksy.models.HabitScheduleApp
import kotlin.uuid.Uuid

interface HabitsRemoteSource {
    suspend fun getHabits(userId: Uuid, timeZone: String): Response<GetHabitsRespondDto, ErrorModel>
    suspend fun createHabit(title: String, schedule: HabitScheduleApp): Response<Unit, ErrorModel>
    suspend fun confirmHabit(habitId: Uuid): Response<Unit, ErrorModel>
    suspend fun deleteHabit(habitId: Uuid): Response<Unit, ErrorModel>
    suspend fun habitDetails(habitId: Uuid): Response<HabitDetailsRespondDto, ErrorModel>
    suspend fun deleteConfirmHabit(habitId: Uuid, confirmId: Uuid): Response<Unit, ErrorModel>
}