package dev.softikk.acksy.data.mappers

import dev.softikk.acksy.dev.softikk.acksy.enums.DailyType
import dev.softikk.acksy.dev.softikk.acksy.models.HabitScheduleApp
import dev.softikk.acksy.domain.models.habits.HabitScheduleModel
import dev.softikk.acksy.domain.enums.DailyType as DailyTypeClient

fun HabitScheduleApp.toHabitScheduleModel(): HabitScheduleModel = when (val schedule = this) {
    is HabitScheduleApp.Daily -> HabitScheduleModel.Daily(
        when (schedule.dailyType) {
            DailyType.Everyday -> DailyTypeClient.Everyday
            DailyType.Weekdays -> DailyTypeClient.Weekdays
            DailyType.Weekends -> DailyTypeClient.Weekends
        }
    )

    is HabitScheduleApp.Monthly -> {
        HabitScheduleModel.Monthly(
            schedule.dates
        )
    }

    is HabitScheduleApp.Weekly -> HabitScheduleModel.Weekly(schedule.weekdays)
}