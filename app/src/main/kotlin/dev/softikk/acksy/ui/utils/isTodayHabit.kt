package dev.softikk.acksy.ui.utils

import dev.softikk.acksy.domain.enums.DailyType
import dev.softikk.acksy.domain.models.habits.HabitModel
import dev.softikk.acksy.domain.models.habits.HabitScheduleModel
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Clock

fun isTodayHabit(habitModel: HabitModel): Boolean {
    val dateTimeNow = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
    return when (val schedule = habitModel.schedule) {
        is HabitScheduleModel.Daily -> {
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

        is HabitScheduleModel.Monthly -> {
            dateTimeNow.day.toUInt() in schedule.dates
        }

        is HabitScheduleModel.Weekly -> {
            dateTimeNow.dayOfWeek in schedule.weekdays
        }
    }
}