package dev.softikk.acksy.ui.components

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import dev.softikk.acksy.ui.theme.Dimens
import dev.softikk.acksy.ui.theme.White

private val HeightAnimButtonsGroup = 40.dp
private val HeightButtonsGroup = 46.dp


@Composable
fun AcksyAnimButtonsGroup(
    state: Int, buttons: List<String>, onState: (Int) -> Unit
) {
    val buttonsCount = buttons.size
    Row(
        modifier = Modifier
            .height(HeightAnimButtonsGroup)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(Dimens.Paddings.xsPadding)
    ) {
        repeat(buttonsCount) { index ->
            val startAnim =
                if (index == state) Dimens.Shapes.largeShape else if (index == 0) Dimens.Shapes.largeShape else Dimens.Shapes.smallShape
            val endAnim =
                if (index == state) Dimens.Shapes.largeShape else if (index == (buttonsCount - 1)) Dimens.Shapes.largeShape else Dimens.Shapes.smallShape
            val animationSpec: AnimationSpec<Dp> = tween(Dimens.Anim.MEDIUM_ANIM_DURATION_MILLIS)
            val topStart by animateDpAsState(
                startAnim, animationSpec = animationSpec
            )
            val bottomStart by animateDpAsState(
                startAnim, animationSpec = animationSpec
            )
            val topEnd by animateDpAsState(
                endAnim, animationSpec = animationSpec
            )
            val bottomEnd by animateDpAsState(
                endAnim, animationSpec = animationSpec
            )
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }) {
                        onState(index)
                    }
                    .background(
                        color = if (index == state) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary,
                        shape = RoundedCornerShape(
                            topStart = topStart,
                            bottomStart = bottomStart,
                            topEnd = topEnd,
                            bottomEnd = bottomEnd
                        )
                    ), contentAlignment = Alignment.Center) {
                Text(
                    text = buttons[index],
                    style = MaterialTheme.typography.labelLarge,
                    color = if (index == state) White else MaterialTheme.colorScheme.onSecondary
                )
            }
        }
    }
}


@Composable
fun AcksyButtonsGroup(
    state: Int, buttons: List<String>, onState: (Int) -> Unit
) {
    val buttonsCount = buttons.size
    Row(
        modifier = Modifier
            .height(HeightButtonsGroup)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(Dimens.Paddings.smallPadding)
    ) {
        repeat(buttonsCount) { index ->
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }) {
                        onState(index)
                    }
                    .background(
                        color = if (index == state) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary
                    ), contentAlignment = Alignment.Center) {
                Text(
                    text = buttons[index],
                    style = MaterialTheme.typography.labelLarge,
                    color = if (index == state) White else MaterialTheme.colorScheme.onSecondary
                )
            }
        }
    }
}