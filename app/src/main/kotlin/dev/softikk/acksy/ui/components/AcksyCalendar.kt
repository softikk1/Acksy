package dev.softikk.acksy.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLocale
import androidx.compose.ui.text.style.TextAlign
import com.kizitonwose.calendar.compose.HorizontalCalendar
import com.kizitonwose.calendar.compose.rememberCalendarState
import com.kizitonwose.calendar.core.CalendarDay
import com.kizitonwose.calendar.core.DayPosition
import com.kizitonwose.calendar.core.firstDayOfWeekFromLocale
import dev.softikk.acksy.ui.theme.AcksyTheme
import dev.softikk.acksy.ui.theme.Dimens
import dev.softikk.acksy.ui.theme.White
import kotlinx.datetime.toKotlinLocalDate
import java.time.DayOfWeek
import java.time.YearMonth
import java.time.format.TextStyle
import kotlinx.datetime.LocalDate as LocalDateK

@Composable
fun AcksyCalendar(
    selectedDates: List<LocalDateK>,
    onSelect: (LocalDateK) -> Unit = {},
    onRemove: (LocalDateK) -> Unit = {}
) {
    val currentMonth = remember { YearMonth.now() }
    val startMonth = remember { currentMonth.minusMonths(100) }
    val endMonth = remember { currentMonth.plusMonths(100) }
    val firstDayOfWeek = remember { firstDayOfWeekFromLocale() }

    val state = rememberCalendarState(
        startMonth = startMonth,
        endMonth = endMonth,
        firstVisibleMonth = currentMonth,
        firstDayOfWeek = firstDayOfWeek
    )

    AcksyTheme {
        HorizontalCalendar(state = state, dayContent = {
            Day(
                day = it,
                isSelected = it.date.toKotlinLocalDate() in selectedDates,
                onClick = { calendarDay ->
                    if (calendarDay.date.toKotlinLocalDate() in selectedDates) onRemove(calendarDay.date.toKotlinLocalDate())
                    else onSelect(calendarDay.date.toKotlinLocalDate())
                })
        }, monthHeader = { month ->
            val daysOfWeek = month.weekDays.first().map { it.date.dayOfWeek }
            MonthHeader(daysOfWeek = daysOfWeek)
        })
    }
}

@Composable
fun MonthHeader(daysOfWeek: List<DayOfWeek>) {
    val locale = LocalLocale.current.platformLocale

    Row(modifier = Modifier.fillMaxWidth()) {
        for (dayOfWeek in daysOfWeek) {
            Text(
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center,
                text = dayOfWeek.getDisplayName(
                    TextStyle.NARROW, locale
                ),
            )
        }
    }
}

@Composable
fun Day(day: CalendarDay, isSelected: Boolean, onClick: (CalendarDay) -> Unit) {
    Box(
        modifier = Modifier
            .padding(Dimens.Paddings.xsPadding)
            .aspectRatio(1f)
            .clip(CircleShape)
            .background(
                color = if (isSelected) {
                    if (day.position == DayPosition.MonthDate) {
                        MaterialTheme.colorScheme.primary
                    } else {
                        Color.Transparent
                    }
                } else {
                    Color.Transparent
                }
            )
            .clickable(
                enabled = day.position == DayPosition.MonthDate,
                onClick = { onClick(day) },
                indication = null,
                interactionSource = remember { MutableInteractionSource() }),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = day.date.dayOfMonth.toString(),
            style = MaterialTheme.typography.bodyMedium,
            color = if (day.position == DayPosition.MonthDate) {
                if (isSelected) White else MaterialTheme.colorScheme.onSurface
            } else MaterialTheme.colorScheme.onSurfaceVariant.copy(
                0.4f
            )
        )
    }
}