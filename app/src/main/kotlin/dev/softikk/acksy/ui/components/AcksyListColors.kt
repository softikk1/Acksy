package dev.softikk.acksy.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import dev.softikk.acksy.ui.theme.Blue
import dev.softikk.acksy.ui.theme.Dimens
import dev.softikk.acksy.ui.theme.Green
import dev.softikk.acksy.ui.theme.Orange
import dev.softikk.acksy.ui.theme.PastelBlue
import dev.softikk.acksy.ui.theme.Purple
import dev.softikk.acksy.ui.theme.Red
import dev.softikk.acksy.ui.theme.SmartDay

private val ColorItemSize = 34.dp
private val ActiveColorItemSize = 42.dp
private val BorderWidth = 2.dp

@Composable
fun AcksyListColors(
    colors: List<Color> = listOf(PastelBlue, Green, Blue, Purple, Red, Orange, SmartDay),
    state: Color,
    onState: (Color) -> Unit
) {
    Row {
        repeat(colors.size) { index ->
            val color = colors[index]
            ColorItem(
                color = color, isActive = state == color
            ) {
                onState(color)
            }
        }
    }
}


@Composable
fun ColorItem(
    color: Color, isActive: Boolean, onClick: () -> Unit
) {
    val colorAnim by animateColorAsState(if (isActive) color else Color.Transparent, tween(400))
    Box(
        modifier = Modifier
            .size(ActiveColorItemSize)
            .background(
                color = Color.Transparent, shape = CircleShape
            )
            .border(BorderWidth, colorAnim, CircleShape)
            .clickable(
                indication = null, interactionSource = remember { MutableInteractionSource() }) {
                onClick()
            }, contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(ColorItemSize)
                .background(
                    color = color, shape = CircleShape
                )
        )
    }
}