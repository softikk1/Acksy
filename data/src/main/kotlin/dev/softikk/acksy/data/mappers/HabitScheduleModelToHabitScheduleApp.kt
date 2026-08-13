package dev.softikk.acksy.data.mappers

import dev.softikk.acksy.dev.softikk.acksy.enums.DailyType
import dev.softikk.acksy.dev.softikk.acksy.models.HabitScheduleApp
import dev.softikk.acksy.domain.models.habits.HabitScheduleModel
import dev.softikk.acksy.domain.enums.DailyType as DailyTypeClient

fun HabitScheduleModel.toHabitScheduleApp(): HabitScheduleApp = when (val schedule = this) {
    is HabitScheduleModel.Daily -> HabitScheduleApp.Daily(
        when (schedule.dailyType) {
            DailyTypeClient.Everyday -> DailyType.Everyday
            DailyTypeClient.Weekdays -> DailyType.Weekdays
            DailyTypeClient.Weekends -> DailyType.Weekends
        }
    )

    is HabitScheduleModel.Monthly -> {
        HabitScheduleApp.Monthly(
            schedule.dates
        )
    }

    is HabitScheduleModel.Weekly -> HabitScheduleApp.Weekly(schedule.weekdays)
}