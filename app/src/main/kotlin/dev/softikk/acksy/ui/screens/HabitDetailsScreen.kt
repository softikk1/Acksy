package dev.softikk.acksy.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.wear.compose.material.Text
import dev.softikk.acksy.R
import dev.softikk.acksy.domain.enums.DailyType
import dev.softikk.acksy.domain.models.habits.HabitModel
import dev.softikk.acksy.domain.models.habits.HabitScheduleModel
import dev.softikk.acksy.ui.components.AcksyBackScreenButton
import dev.softikk.acksy.ui.components.AcksyHeatMapCalendar
import dev.softikk.acksy.ui.components.AcksyScreenName
import dev.softikk.acksy.ui.components.AcksyStatisticsFolder
import dev.softikk.acksy.ui.components.AcksySubtitle
import dev.softikk.acksy.ui.components.AcksyTitle
import dev.softikk.acksy.ui.components.Statistics
import dev.softikk.acksy.ui.theme.Dimens
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.isoDayNumber

val TestListConfirmsHabit = listOf(
    LocalDateTime(2026, 7, 3, 10, 15),
    LocalDateTime(2026, 7, 7, 14, 30),
    LocalDateTime(2026, 7, 12, 9, 0),
    LocalDateTime(2026, 7, 18, 18, 45),
    LocalDateTime(2026, 7, 25, 12, 0),

    LocalDateTime(2026, 8, 2, 8, 30),
    LocalDateTime(2026, 8, 5, 11, 0),
    LocalDateTime(2026, 8, 10, 16, 20),
    LocalDateTime(2026, 8, 14, 20, 0),
    LocalDateTime(2026, 8, 21, 13, 15),
    LocalDateTime(2026, 8, 28, 19, 45),

    LocalDateTime(2026, 10, 1, 9, 0),
    LocalDateTime(2026, 10, 6, 15, 30),
    LocalDateTime(2026, 10, 12, 11, 10),
    LocalDateTime(2026, 10, 19, 17, 0),
    LocalDateTime(2026, 10, 25, 10, 0)
)
const val TestScore = 80
const val TestTotal = 32
const val TestBestStreak = 10
const val TestStreak = 5

@Composable
fun HabitDetailsScreen(habitModel: HabitModel, backStack: NavBackStack<NavKey>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(Dimens.Paddings.mediumPadding),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(Dimens.Paddings.mediumPadding)
        ) {
            AcksyBackScreenButton(
                backStack = backStack
            )
            AcksyScreenName(
                text = habitModel.title
            )
            AcksySubtitle(
                text = when (val schedule = habitModel.schedule) {
                    is HabitScheduleModel.Daily -> {
                        when (schedule.dailyType) {
                            DailyType.Everyday -> {
                                stringResource(R.string.habit_details_screen_schedule_every_day)
                            }

                            DailyType.Weekdays -> {
                                stringResource(R.string.habit_details_screen_schedule_weekdays)
                            }

                            DailyType.Weekends -> {
                                stringResource(R.string.habit_details_screen_schedule_weekends)
                            }
                        }
                    }

                    is HabitScheduleModel.Monthly -> {
                        stringResource(R.string.habit_details_screen_schedule_monthly) + " " + schedule.dates.sortedBy { it }
                            .joinToString(", ")
                    }

                    is HabitScheduleModel.Weekly -> {
                        stringResource(R.string.habit_details_screen_schedule_weekly) + " " + schedule.weekdays.map { it.isoDayNumber }
                            .sortedBy { it }.joinToString(", ")
                    }
                }
            )

            AcksyStatisticsFolder(
                modifier = Modifier.fillMaxWidth(), items = listOf(
                    Statistics(
                        count = TestScore,
                        description = stringResource(R.string.habit_details_screen_statistic_folder_score),
                        suffix = "%"
                    ), Statistics(
                        count = TestTotal,
                        description = stringResource(R.string.habit_details_screen_statistic_folder_total)
                    ), Statistics(
                        count = TestBestStreak,
                        description = stringResource(R.string.habit_details_screen_statistic_folder_best_streak)
                    ), Statistics(
                        count = TestStreak,
                        description = stringResource(R.string.habit_details_screen_statistic_folder_streak)
                    )
                )
            )

            AcksyTitle(
                text = stringResource(R.string.habit_details_screen_title)
            )
            AcksyHeatMapCalendar(
                backStack = backStack, confirmsHabit = TestListConfirmsHabit.map { it.date })
            Row(
                modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End
            ) {
                Text(
                    modifier = Modifier.clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }) {

                    },
                    text = stringResource(R.string.habit_details_screen_button_delete_habit),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.error
                )
            }
        }
    }
}