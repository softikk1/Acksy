package dev.softikk.acksy.ui.utils

import dev.softikk.acksy.domain.enums.DailyType
import dev.softikk.acksy.ui.models.HabitAppModel
import dev.softikk.acksy.ui.models.HabitScheduleAppModel
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Clock

fun isTodayHabit(habitModel: HabitAppModel): Boolean {
    val dateTimeNow = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
    return when (val schedule = habitModel.schedule) {
        is HabitScheduleAppModel.Daily -> {
            when (schedule.dailyType) {
                DailyType.Everyday -> true

                DailyType.Weekdays -> {
                    dateTimeNow.dayOfWeek in setOf(
                        DayOfWeek.MONDAY,
                        DayOfWeek.TUESDAY,
                        DayOfWeek.WEDNESDAY,
                        DayOfWeek.THURSDAY,
                        DayOfWeek.FRIDAY
                    )
                }

                DailyType.Weekends -> {
                    dateTimeNow.dayOfWeek in setOf(
                        DayOfWeek.SATURDAY, DayOfWeek.SUNDAY
                    )
                }
            }
        }

        is HabitScheduleAppModel.Monthly -> {
            dateTimeNow.day.toUInt() in schedule.dates
        }

        is HabitScheduleAppModel.Weekly -> {
            dateTimeNow.dayOfWeek in schedule.weekdays
        }
    }
}