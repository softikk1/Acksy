package dev.softikk.acksy.ui.models

import dev.softikk.acksy.domain.enums.DailyType
import kotlinx.datetime.DayOfWeek
import kotlinx.serialization.Serializable

@Serializable
sealed interface HabitScheduleAppModel {
    @Serializable
    data class Daily(val dailyType: DailyType) : HabitScheduleAppModel

    @Serializable
    data class Weekly(val weekdays: Set<DayOfWeek>) : HabitScheduleAppModel

    @Serializable
    data class Monthly(val dates: Set<UInt>) : HabitScheduleAppModel
}