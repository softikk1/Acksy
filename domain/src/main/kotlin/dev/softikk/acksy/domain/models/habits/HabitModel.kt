package dev.softikk.acksy.domain.models.habits

import kotlinx.datetime.LocalDateTime
import kotlin.uuid.Uuid

data class HabitModel(
    val id: Uuid, val title: String, val createdAt: LocalDateTime, val schedule: HabitScheduleModel
)