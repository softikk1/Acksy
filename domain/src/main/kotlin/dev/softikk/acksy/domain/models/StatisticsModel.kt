package dev.softikk.acksy.domain.models

import kotlinx.datetime.LocalDateTime

data class StatisticsModel(
    val score: UInt,
    val total: ULong,
    val bestStreak: ULong,
    val lastStreak: ULong,
    val confirms: Set<LocalDateTime>
)
