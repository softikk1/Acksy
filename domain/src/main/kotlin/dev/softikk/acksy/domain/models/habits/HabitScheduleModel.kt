package dev.softikk.acksy.domain.models.habits

import dev.softikk.acksy.domain.enums.DailyType
import kotlinx.datetime.DayOfWeek

sealed interface HabitScheduleModel {
    data class Daily(val dailyType: DailyType) : HabitScheduleModel
    data class Weekly(val weekdays: Set<DayOfWeek>) : HabitScheduleModel
    data class Monthly(val dates: Set<UInt>) : HabitScheduleModel
}