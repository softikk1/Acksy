package dev.softikk.acksy.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.dropUnlessResumed
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import dev.softikk.acksy.R
import dev.softikk.acksy.domain.enums.DailyType
import dev.softikk.acksy.ui.components.AcksyHabit
import dev.softikk.acksy.ui.components.AcksyScreenName
import dev.softikk.acksy.ui.components.AcksyTasksWidgets
import dev.softikk.acksy.ui.models.HabitAppModel
import dev.softikk.acksy.ui.models.HabitDetailsAppModel
import dev.softikk.acksy.ui.models.HabitScheduleAppModel
import dev.softikk.acksy.ui.navigation.Routes
import dev.softikk.acksy.ui.theme.Dimens
import dev.softikk.acksy.ui.utils.isTodayHabit
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDateTime
import kotlin.uuid.Uuid

@Composable
fun HomeScreen(backStack: NavBackStack<NavKey>, innerPadding: PaddingValues) {
    val habitsWidgets = listOf(
        stringResource(R.string.home_screen_tasks_widget_today),
        stringResource(R.string.home_screen_tasks_widget_all)
    )
    var selected by remember { mutableStateOf(habitsWidgets[0]) }
    val habitsAppModel = remember {
        mutableStateListOf(
            HabitAppModel(Uuid.random(), "Утренняя зарядка", LocalDateTime(2026, 9, 1, 7, 30), HabitScheduleAppModel.Daily(
                DailyType.entries.first())),
            HabitAppModel(Uuid.random(), "Выпить стакан воды", LocalDateTime(2026, 9, 1, 7, 45), HabitScheduleAppModel.Daily(DailyType.entries.first())),
            HabitAppModel(Uuid.random(), "Принять витамины",
                LocalDateTime(2026, 9, 3, 8, 0), HabitScheduleAppModel.Daily(DailyType.entries.first())),
            HabitAppModel(Uuid.random(), "Чтение 20 минут", LocalDateTime(2026, 9, 5, 21, 0), HabitScheduleAppModel.Daily(DailyType.entries.first())),
            HabitAppModel(Uuid.random(), "Медитация", LocalDateTime(2026, 9, 7, 6, 50), HabitScheduleAppModel.Daily(DailyType.entries.first())),
            HabitAppModel(Uuid.random(), "Выучить 10 слов", LocalDateTime(2026, 9, 10, 19, 0), HabitScheduleAppModel.Daily(DailyType.entries.first())),
            HabitAppModel(Uuid.random(), "Записать дневник", LocalDateTime(2026, 9, 12, 22, 0), HabitScheduleAppModel.Daily(DailyType.entries.first())),
            HabitAppModel(Uuid.random(), "Прогулка 8000 шагов", LocalDateTime(2026, 9, 14, 18, 0), HabitScheduleAppModel.Daily(DailyType.entries.first())),

            HabitAppModel(Uuid.random(), "Тренировка в зале", LocalDateTime(2026, 9, 2, 18, 30), HabitScheduleAppModel.Weekly(setOf(DayOfWeek.MONDAY, DayOfWeek.WEDNESDAY, DayOfWeek.FRIDAY))),
            HabitAppModel(Uuid.random(), "Йога", LocalDateTime(2026, 9, 4, 19, 0), HabitScheduleAppModel.Weekly(setOf(DayOfWeek.TUESDAY, DayOfWeek.THURSDAY))),
            HabitAppModel(Uuid.random(), "Пробежка", LocalDateTime(2026, 9, 6, 8, 0), HabitScheduleAppModel.Weekly(setOf(DayOfWeek.TUESDAY, DayOfWeek.SATURDAY))),
            HabitAppModel(Uuid.random(), "Созвон с родителями", LocalDateTime(2026, 9, 8, 20, 0), HabitScheduleAppModel.Weekly(setOf(DayOfWeek.SUNDAY))),
            HabitAppModel(Uuid.random(), "Генеральная уборка", LocalDateTime(2026, 9, 9, 11, 0), HabitScheduleAppModel.Weekly(setOf(DayOfWeek.SATURDAY))),
            HabitAppModel(Uuid.random(), "Занятие английским", LocalDateTime(2026, 9, 11, 17, 0), HabitScheduleAppModel.Weekly(setOf(DayOfWeek.WEDNESDAY, DayOfWeek.FRIDAY))),
            HabitAppModel(Uuid.random(), "Полить цветы", LocalDateTime(2026, 9, 13, 10, 0), HabitScheduleAppModel.Weekly(setOf(DayOfWeek.WEDNESDAY, DayOfWeek.SUNDAY))),

            HabitAppModel(Uuid.random(), "Оплатить подписки", LocalDateTime(2026, 9, 1, 12, 0), HabitScheduleAppModel.Monthly(setOf(1u, 15u))),
            HabitAppModel(Uuid.random(), "Оплатить интернет", LocalDateTime(2026, 9, 2, 12, 0), HabitScheduleAppModel.Monthly(setOf(5u))),
            HabitAppModel(Uuid.random(), "Передать показания счётчиков", LocalDateTime(2026, 9, 3, 12, 0), HabitScheduleAppModel.Monthly(setOf(20u, 21u, 22u, 23u, 24u, 25u))),
            HabitAppModel(Uuid.random(), "Сделать бэкап фото", LocalDateTime(2026, 9, 4, 12, 0), HabitScheduleAppModel.Monthly(setOf(10u))),
            HabitAppModel(Uuid.random(), "Проверить бюджет", LocalDateTime(2026, 9, 5, 12, 0), HabitScheduleAppModel.Monthly(setOf(1u, 21u))),
            HabitAppModel(Uuid.random(), "Стрижка", LocalDateTime(2026, 9, 6, 12, 0), HabitScheduleAppModel.Monthly(setOf(14u))),
            HabitAppModel(Uuid.random(), "Заменить фильтр воды", LocalDateTime(2026, 9, 7, 12, 0), HabitScheduleAppModel.Monthly(setOf(28u))),
            HabitAppModel(Uuid.random(), "Подвести итоги месяца", LocalDateTime(2026, 9, 8, 12, 0), HabitScheduleAppModel.Monthly(setOf(30u, 31u)))
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(Dimens.Paddings.mediumPadding),
        verticalArrangement = Arrangement.spacedBy(Dimens.Paddings.smallPadding)
    ) {
        AcksyScreenName(
            text = stringResource(R.string.home_screen_name)
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
            items(habitsAppModel.filter {
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

                }, onClick = dropUnlessResumed {
                    val habitDetailsAppModel = HabitDetailsAppModel(
                        score = 123u,
                        total = 123u,
                        bestStreak = 123u,
                        lastStreak = 123u,
                        confirmsDates = emptySet()
                    )
                    backStack.add(
                        Routes.HabitDetails(
                            habitModel = habit,
                            habitDetailsAppModel = habitDetailsAppModel
                        )
                    )
                })
            }
            item {
                Spacer(
                    modifier = Modifier
                        .padding(bottom = innerPadding.calculateBottomPadding())
                )
            }
        }
    }
}