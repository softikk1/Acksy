package dev.softikk.acksy.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.softikk.acksy.R
import dev.softikk.acksy.domain.enums.DailyType
import dev.softikk.acksy.domain.models.habits.HabitModel
import dev.softikk.acksy.domain.models.habits.HabitScheduleModel
import dev.softikk.acksy.ui.components.AcksyHabit
import dev.softikk.acksy.ui.components.AcksyScreenName
import dev.softikk.acksy.ui.components.AcksyTasksWidgets
import dev.softikk.acksy.ui.theme.AcksyTheme
import dev.softikk.acksy.ui.theme.Dimens
import dev.softikk.acksy.ui.utils.isTodayHabit
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Clock
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
val testTaskList = listOf(
    HabitModel(
        id = Uuid.generateV4(),
        title = "Study Kotlin & Jetpack Compose",
        createdAt = Clock.System.now().toLocalDateTime(TimeZone.UTC),
        schedule = HabitScheduleModel.Daily(
            dailyType = DailyType.Everyday
        )
    ), HabitModel(
        id = Uuid.generateV4(),
        title = "Morning Gym Workout",
        createdAt = Clock.System.now().toLocalDateTime(TimeZone.UTC),
        schedule = HabitScheduleModel.Daily(
            dailyType = DailyType.Weekdays
        )
    ), HabitModel(
        id = Uuid.generateV4(),
        title = "Weekend Relax & Walk",
        createdAt = Clock.System.now().toLocalDateTime(TimeZone.UTC),
        schedule = HabitScheduleModel.Daily(
            dailyType = DailyType.Weekends
        )
    ), HabitModel(
        id = Uuid.generateV4(),
        title = "Team Code Review",
        createdAt = Clock.System.now().toLocalDateTime(TimeZone.UTC),
        schedule = HabitScheduleModel.Weekly(
            weekdays = setOf(DayOfWeek.MONDAY, DayOfWeek.WEDNESDAY, DayOfWeek.FRIDAY)
        )
    ), HabitModel(
        id = Uuid.generateV4(),
        title = "Grocery Shopping",
        createdAt = Clock.System.now().toLocalDateTime(TimeZone.UTC),
        schedule = HabitScheduleModel.Weekly(
            weekdays = setOf(DayOfWeek.SATURDAY)
        )
    ), HabitModel(
        id = Uuid.generateV4(),
        title = "Monthly Financial Audit",
        createdAt = Clock.System.now().toLocalDateTime(TimeZone.UTC),
        schedule = HabitScheduleModel.Monthly(
            dates = setOf(1u, 15u)
        )
    ), HabitModel(
        id = Uuid.generateV4(),
        title = "Deep Home Cleaning",
        createdAt = Clock.System.now().toLocalDateTime(TimeZone.UTC),
        schedule = HabitScheduleModel.Monthly(
            dates = setOf(1u, 30u)
        )
    )
)

@Preview
@Composable
fun HomeScreen() {
    val habitsWidgets = listOf(
        stringResource(R.string.home_screen_tasks_widget_today),
        stringResource(R.string.home_screen_tasks_widget_all)
    )
    var selected by remember { mutableStateOf(habitsWidgets[0]) }

    AcksyTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(Dimens.Paddings.mediumPadding),
            verticalArrangement = Arrangement.spacedBy(Dimens.Paddings.smallPadding)
        ) {
            AcksyScreenName(
                text = stringResource(R.string.home_screen_title)
            )
            AcksyTasksWidgets(
                selected = selected, habitsWidgets = habitsWidgets
            ) {
                selected = it
            }
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(Dimens.Paddings.mediumPadding)
            ) {
                item {
                    Spacer(modifier = Modifier.padding(vertical = 2.dp))
                }
                items(testTaskList.filter {
                    when (selected) {
                        habitsWidgets[0] -> {
                            isTodayHabit(it)
                        }

                        habitsWidgets[1] -> {
                            true
                        }

                        else -> {
                            false
                        }
                    }
                }) { habit ->
                    AcksyHabit(taskName = habit.title, left = {

                    }, right = {

                    })
                }
                item {
                    Spacer(modifier = Modifier.padding(vertical = 2.dp))
                }
            }
        }
    }
}