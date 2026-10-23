package dev.softikk.acksy.ui.models

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable
import kotlin.uuid.Uuid

@Serializable
data class HabitAppModel(
    val id: Uuid, val title: String, val createdAt: LocalDateTime, val schedule: HabitScheduleAppModel
)