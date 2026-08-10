package dev.softikk.acksy.data.sources.remote

import dev.softikk.acksy.data.models.ErrorModel
import dev.softikk.acksy.data.models.Response
import dev.softikk.acksy.data.sources.HabitsRemoteSource
import dev.softikk.acksy.data.sources.remote.resources.HabitsRes
import dev.softikk.acksy.data.sources.remote.utils.getFailedResponse
import dev.softikk.acksy.dev.softikk.acksy.entities.habits.CreateHabitReceiveDto
import dev.softikk.acksy.dev.softikk.acksy.entities.habits.GetHabitsReceiveDto
import dev.softikk.acksy.dev.softikk.acksy.entities.habits.GetHabitsRespondDto
import dev.softikk.acksy.dev.softikk.acksy.entities.habits.HabitDetailsRespondDto
import dev.softikk.acksy.dev.softikk.acksy.models.HabitScheduleApp
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.resources.delete
import io.ktor.client.plugins.resources.get
import io.ktor.client.plugins.resources.post
import io.ktor.client.request.setBody
import io.ktor.http.HttpStatusCode
import kotlin.uuid.Uuid

class HabitsRemoteSourceImpl(private val client: HttpClient) : HabitsRemoteSource {
    override suspend fun getHabits(
        userId: Uuid, timeZone: String
    ): Response<GetHabitsRespondDto, ErrorModel> {
        val result = client.get(HabitsRes()) {
            setBody(
                GetHabitsReceiveDto(
                    userId = userId, timeZone = timeZone
                )
            )
        }
        return when (result.status) {
            HttpStatusCode.OK -> {
                Response.Success(result.body<GetHabitsRespondDto>())
            }

            else -> {
                getFailedResponse(result)
            }
        }
    }

    override suspend fun createHabit(
        title: String, schedule: HabitScheduleApp
    ): Response<Unit, ErrorModel> {
        val result = client.post(HabitsRes.Create()) {
            setBody(
                CreateHabitReceiveDto(
                    title = title, schedule = schedule
                )
            )
        }
        return when (result.status) {
            HttpStatusCode.OK -> {
                Response.Success(Unit)
            }

            else -> {
                getFailedResponse(result)
            }
        }
    }

    override suspend fun confirmHabit(habitId: Uuid): Response<Unit, ErrorModel> {
        val result = client.post(HabitsRes.HabitId.Confirm(HabitsRes.HabitId(habitId = habitId)))
        return when (result.status) {
            HttpStatusCode.OK -> {
                Response.Success(Unit)
            }

            else -> {
                getFailedResponse(result)
            }
        }
    }

    override suspend fun deleteHabit(habitId: Uuid): Response<Unit, ErrorModel> {
        val result = client.delete(HabitsRes.HabitId.Delete(HabitsRes.HabitId(habitId = habitId)))
        return when (result.status) {
            HttpStatusCode.OK -> {
                Response.Success(Unit)
            }

            else -> {
                getFailedResponse(result)
            }
        }
    }

    override suspend fun habitDetails(habitId: Uuid): Response<HabitDetailsRespondDto, ErrorModel> {
        val result = client.get(HabitsRes.HabitId.Details(HabitsRes.HabitId(habitId = habitId)))
        return when (result.status) {
            HttpStatusCode.OK -> {
                Response.Success(result.body<HabitDetailsRespondDto>())
            }

            else -> {
                getFailedResponse(result)
            }
        }
    }

    override suspend fun deleteConfirmHabit(
        habitId: Uuid,
        confirmId: Uuid
    ): Response<Unit, ErrorModel> {
        val result = client.delete(
            HabitsRes.HabitId.ConfirmId.Delete(
                HabitsRes.HabitId.ConfirmId(
                    HabitsRes.HabitId(habitId = habitId), confirmId = confirmId
                )
            )
        )
        return when (result.status) {
            HttpStatusCode.OK -> {
                Response.Success(Unit)
            }

            else -> {
                getFailedResponse(result)
            }
        }
    }
}