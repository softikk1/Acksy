package dev.softikk.acksy.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.ExperimentalWearMaterialApi
import androidx.wear.compose.material.FractionalThreshold
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.rememberSwipeableState
import androidx.wear.compose.material.swipeable
import dev.softikk.acksy.R
import dev.softikk.acksy.ui.theme.DarkWhite
import dev.softikk.acksy.ui.theme.Dimens
import dev.softikk.acksy.ui.theme.OrangeJuice
import dev.softikk.acksy.ui.theme.PastelGreen
import kotlin.math.roundToInt

enum class Swipeable {
    Center, Left, Right
}

private val IconTaskPadding = 13.dp
private val HeightTask = 50.dp

@OptIn(ExperimentalWearMaterialApi::class)
@Composable
fun AcksyHabit(taskName: String, left: () -> Unit, right: () -> Unit) {
    val colorTask =
        if (isSystemInDarkTheme()) MaterialTheme.colorScheme.surfaceContainer else DarkWhite
    val swipeableState = rememberSwipeableState(0, confirmStateChange = { value ->
        when (Swipeable.entries.elementAt(value)) {
            Swipeable.Center -> Unit
            Swipeable.Left -> {
                left()
            }

            Swipeable.Right -> {
                right()
            }
        }
        true
    })
    val anchors = mapOf(0f to 0, 150f to 1, -150f to 2)
    Box(
        modifier = Modifier
            .height(HeightTask)
            .fillMaxWidth()
            .swipeable(
                state = swipeableState,
                anchors = anchors,
                thresholds = { _, _ -> FractionalThreshold(0.7f) },
                orientation = Orientation.Horizontal
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .dropShadow(
                    shape = RoundedCornerShape(Dimens.Shapes.mediumShape),
                    shadow = Shadow(radius = 4.dp, offset = DpOffset(x = 0.dp, y = 1.dp), alpha = 0.1f)
                )
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
                    .background(
                        color = PastelGreen, shape = RoundedCornerShape(
                            topStart = Dimens.Shapes.mediumShape,
                            bottomStart = Dimens.Shapes.mediumShape
                        )
                    ), contentAlignment = Alignment.CenterStart
            ) {
                Icon(
                    modifier = Modifier.padding(start = IconTaskPadding),
                    imageVector = ImageVector.vectorResource(R.drawable.check),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.surface
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
                    .background(
                        color = OrangeJuice, shape = RoundedCornerShape(
                            topEnd = Dimens.Shapes.mediumShape,
                            bottomEnd = Dimens.Shapes.mediumShape
                        )
                    ), contentAlignment = Alignment.CenterEnd
            ) {
                Icon(
                    modifier = Modifier.padding(end = IconTaskPadding),
                    imageVector = ImageVector.vectorResource(R.drawable.skip),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.surface
                )
            }
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .offset { IntOffset(swipeableState.offset.value.roundToInt(), 0) }
                .background(
                    color = colorTask, shape = RoundedCornerShape(Dimens.Shapes.mediumShape)
                ), contentAlignment = Alignment.CenterStart) {
            Text(
                modifier = Modifier.padding(start = Dimens.Paddings.mediumPadding),
                text = taskName,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}