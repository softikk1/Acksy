package dev.softikk.acksy.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import dev.softikk.acksy.R
import dev.softikk.acksy.ui.components.AcksyAnimButtonsGroup
import dev.softikk.acksy.ui.components.AcksyBackScreenButton
import dev.softikk.acksy.ui.components.AcksyButtonsGroup
import dev.softikk.acksy.ui.components.AcksyCalendar
import dev.softikk.acksy.ui.components.AcksyDayOfWeek
import dev.softikk.acksy.ui.components.AcksyScreenName
import dev.softikk.acksy.ui.components.AcksySubtitle
import dev.softikk.acksy.ui.components.AcksyTextButton
import dev.softikk.acksy.ui.components.AcksyTextField
import dev.softikk.acksy.ui.components.AcksyTitle
import dev.softikk.acksy.ui.theme.Dimens
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDate

@Composable
fun CreateHabitScreen(backStack: NavBackStack<NavKey>) {
    val titleHabitTextFieldState = rememberTextFieldState()
    var stateAnimButtonsGroup by remember { mutableIntStateOf(0) }

    var stateButtonsGroup by remember { mutableIntStateOf(0) }
    val stateDayOfWeek = remember { mutableStateListOf<DayOfWeek>() }
    val selectedDays = remember { mutableStateListOf<LocalDate>() }

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
                text = stringResource(R.string.create_habit_screen_name)
            )
            AcksyTextField(
                modifier = Modifier.fillMaxWidth(),
                state = titleHabitTextFieldState,
                placeholder = stringResource(R.string.create_habit_screen_text_field_placeholder_text)
            )
            AcksyAnimButtonsGroup(
                state = stateAnimButtonsGroup, buttons = listOf(
                    stringResource(R.string.create_habit_screen_schedule_daily),
                    stringResource(R.string.create_habit_screen_schedule_weekly),
                    stringResource(R.string.create_habit_screen_schedule_monthly)
                )
            ) {
                stateAnimButtonsGroup = it
            }

            when (stateAnimButtonsGroup) {
                0 -> {
                    AcksyTitle(
                        text = stringResource(R.string.create_habit_screen_title_daily)
                    )
                    AcksyButtonsGroup(
                        state = stateButtonsGroup, buttons = listOf(
                            stringResource(R.string.create_habit_screen_schedule_daily_every_day),
                            stringResource(R.string.create_habit_screen_schedule_daily_every_weekdays),
                            stringResource(R.string.create_habit_screen_schedule_daily_every_weekends)
                        )
                    ) {
                        stateButtonsGroup = it
                    }
                }

                1 -> {
                    AcksyTitle(
                        text = stringResource(R.string.create_habit_screen_title_weekly)
                    )
                    AcksyDayOfWeek(
                        state = stateDayOfWeek
                    )
                }

                2 -> {
                    AcksySubtitle(
                        text = stringResource(R.string.create_habit_screen_title_monthly) + " " + selectedDays.sortedBy {
                            it.day
                        }.map {
                            it.day
                        }.joinToString(
                            ", "
                        )
                    )
                    AcksyCalendar(selectedDates = selectedDays, onSelect = {
                        selectedDays.add(it)
                    }, onRemove = {
                        selectedDays.remove(it)
                    })
                }
            }
        }

        AcksyTextButton(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(R.string.create_habit_screen_text_button_save),
            containerColor = MaterialTheme.colorScheme.onSurface
        ) {

        }
    }
}