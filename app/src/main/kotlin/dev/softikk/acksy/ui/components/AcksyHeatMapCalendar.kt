package dev.softikk.acksy.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.kizitonwose.calendar.compose.HeatMapCalendar
import com.kizitonwose.calendar.compose.heatmapcalendar.rememberHeatMapCalendarState
import com.kizitonwose.calendar.core.CalendarDay
import com.kizitonwose.calendar.core.CalendarMonth
import com.kizitonwose.calendar.core.firstDayOfWeekFromLocale
import dev.softikk.acksy.ui.navigation.Routes
import dev.softikk.acksy.ui.theme.Dimens
import kotlinx.datetime.LocalDate
import kotlinx.datetime.toKotlinLocalDate
import java.time.YearMonth
import java.time.format.TextStyle

@Composable
fun AcksyHeatMapCalendar(
    backStack: NavBackStack<NavKey>, confirmsHabit: List<LocalDate>
) {
    val currentMonth = remember { YearMonth.now() }
    val startMonth = remember { currentMonth.minusMonths(100) }
    val endMonth = remember { currentMonth.plusMonths(100) }
    val firstDayOfWeek = remember { firstDayOfWeekFromLocale() }

    val locale = LocalLocale.current.platformLocale

    val state = rememberHeatMapCalendarState(
        startMonth = startMonth,
        endMonth = endMonth,
        firstVisibleMonth = currentMonth,
        firstDayOfWeek = firstDayOfWeek,
    )

    if (backStack.lastOrNull() != null) {
        HeatMapCalendar(state = state, dayContent = { day, _ ->
            HeatMapDay(
                day = day,
                confirmsHabit = confirmsHabit,
                isStatistics = backStack.lastOrNull() == Routes.Statistics
            )
        }, weekHeader = { day ->
            Box(
                modifier = Modifier
                    .height(24.dp)
                    .fillMaxWidth()
                    .padding(end = Dimens.Paddings.xsPadding),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(
                    textAlign = TextAlign.Center,
                    text = day.getDisplayName(
                        TextStyle.NARROW_STANDALONE, locale
                    ),
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }, monthHeader = { month ->
            HeatMapMonthHeader(month)
        })
    }
}

@Composable
private fun HeatMapDay(day: CalendarDay, confirmsHabit: List<LocalDate>, isStatistics: Boolean) {
    Box(
        modifier = Modifier
            .size(24.dp)
            .padding(1.dp)
            .clip(RoundedCornerShape(3.dp))
            .border(0.5.dp, MaterialTheme.colorScheme.surfaceContainer, RoundedCornerShape(3.dp))
            .background(if (isStatistics) getContributionColorForDateStatistics(if (day.date.toKotlinLocalDate() in confirmsHabit) confirmsHabit.count {
                it == day.date.toKotlinLocalDate()
            } else 0)
            else getContributionColorForDateHabit(if (day.date.toKotlinLocalDate() in confirmsHabit) 1 else 0)))
}

@Composable
private fun HeatMapMonthHeader(month: CalendarMonth) {
    val locale = LocalLocale.current.platformLocale

    Text(
        textAlign = TextAlign.Center, text = month.yearMonth.month.getDisplayName(
            TextStyle.SHORT_STANDALONE, locale
        ), style = MaterialTheme.typography.labelLarge, color = MaterialTheme.colorScheme.onSurface
    )
}

@Composable
private fun getContributionColorForDateStatistics(size: Int): Color = when (size) {
    0 -> Color.Transparent
    in 1..3 -> MaterialTheme.colorScheme.secondary
    in 4..6 -> MaterialTheme.colorScheme.tertiary
    else -> MaterialTheme.colorScheme.primary
}

@Composable
private fun getContributionColorForDateHabit(size: Int): Color = if (size > 0) {
    MaterialTheme.colorScheme.primary
} else {
    Color.Transparent
}
