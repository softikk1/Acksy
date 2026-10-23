package dev.softikk.acksy.ui.models

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

@Serializable
data class HabitDetailsAppModel(
    val score: UInt,
    val total: ULong,
    val bestStreak: ULong,
    val lastStreak: ULong,
    val confirmsDates: Set<LocalDateTime>
)