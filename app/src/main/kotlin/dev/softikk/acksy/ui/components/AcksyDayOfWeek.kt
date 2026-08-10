package dev.softikk.acksy.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.softikk.acksy.ui.theme.Dimens
import dev.softikk.acksy.ui.theme.White
import kotlinx.datetime.DayOfWeek

private val HeightDayOfWeek = 48.dp
private val SizeBoxDayOfWeek = 45.dp

@Composable
fun AcksyDayOfWeek(
    modifier: Modifier = Modifier, state: SnapshotStateList<DayOfWeek>
) {
    val weekDays = DayOfWeek.entries.toTypedArray()
    Row(
        modifier = modifier
            .height(HeightDayOfWeek)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        repeat(weekDays.size) { index ->
            val dayOfWeek = weekDays[index]
            val isDayOfWeekInList = dayOfWeek in state.toList()
            val colorAnimation by animateColorAsState(
                if (isDayOfWeekInList) {
                    MaterialTheme.colorScheme.primary
                } else {
                    MaterialTheme.colorScheme.surfaceContainer
                }, tween(Dimens.Anim.MEDIUM_ANIM_DURATION_MILLIS)
            )
            Box(
                modifier = Modifier
                    .size(SizeBoxDayOfWeek)
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }) {
                        if (isDayOfWeekInList) {
                            state.remove(dayOfWeek)
                        } else {
                            state.add(dayOfWeek)
                        }
                    }
                    .background(
                        color = colorAnimation, shape = CircleShape
                    ), contentAlignment = Alignment.Center) {
                Text(
                    text = dayOfWeek.name[0].toString(),
                    style = MaterialTheme.typography.labelLarge,
                    color = White
                )
            }
        }
    }
}