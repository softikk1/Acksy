package dev.softikk.acksy.ui.components

import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.wear.compose.material.Text
import dev.softikk.acksy.ui.theme.Dimens

@Composable
fun AcksyCounterStatistics(
    modifier: Modifier = Modifier,
    contentAlignment: Alignment = Alignment.Center,
    count: Int,
    suffix: String? = null,
    color: Color,
    style: TextStyle
) {
    var targetCount by remember { mutableIntStateOf(0) }
    val countAnimation by animateIntAsState(
        targetCount, tween(Dimens.Anim.LARGE_DURATION_MILLIS)
    )

    LaunchedEffect(Unit) {
        targetCount = count
    }

    Box(
        modifier = modifier, contentAlignment = contentAlignment
    ) {
        Text(
            text = if (suffix != null) countAnimation.toString() + suffix else countAnimation.toString(),
            style = style,
            color = color
        )
    }
}