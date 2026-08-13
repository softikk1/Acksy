package dev.softikk.acksy.domain.models.habits

import kotlinx.datetime.LocalDateTime

data class HabitDetailsModel(
    val score: UInt,
    val total: ULong,
    val bestStreak: ULong,
    val lastStreak: ULong,
    val confirmsDates: Set<LocalDateTime>
)