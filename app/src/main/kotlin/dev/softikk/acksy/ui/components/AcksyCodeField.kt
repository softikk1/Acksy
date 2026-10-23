package dev.softikk.acksy.ui.components

import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import dev.softikk.acksy.ui.theme.Dimens

private val BorderWidth = 1.25.dp

@Composable
fun AcksyCodeField(
    modifier: Modifier = Modifier, value: String, onValueChange: (String) -> Unit
) {
    BasicTextField(
        modifier = modifier.widthIn(max = Dimens.maxWidthElement),
        value = value,
        onValueChange = {
            if ((it.length <= 6) and (it.isDigitsOnly())) {
                onValueChange(it)
            }
        },
        decorationBox = {
            Row(
                horizontalArrangement = Arrangement.spacedBy(Dimens.Paddings.mediumPadding)
            ) {
                repeat(6) { index ->
                    val visibleState = remember { MutableTransitionState(false) }

                    visibleState.targetState = value.length >= index + 1

                    val lastChar by remember(value) {
                        mutableStateOf(if (value.length >= index + 1) value[index].toString() else "")
                    }

                    val shape = when (index) {
                        0 -> MaterialTheme.shapes.extraSmall.copy(
                            topStart = CornerSize(Dimens.Shapes.mediumShape),
                            bottomStart = CornerSize(Dimens.Shapes.mediumShape)
                        )

                        5 -> MaterialTheme.shapes.extraSmall.copy(
                            topEnd = CornerSize(Dimens.Shapes.mediumShape),
                            bottomEnd = CornerSize(Dimens.Shapes.mediumShape)
                        )

                        else -> MaterialTheme.shapes.extraSmall
                    }
                    Box(
                        modifier = Modifier
                            .height(Dimens.heightComponent)
                            .weight(1f)
                            .background(
                                color = MaterialTheme.colorScheme.secondary, shape = shape
                            )
                            .border(
                                width = BorderWidth, color = if (value.isEmpty()) {
                                    if (index == 0) MaterialTheme.colorScheme.primary else Color.Transparent
                                } else {
                                    if (index == value.length) MaterialTheme.colorScheme.primary else Color.Transparent
                                }, shape = shape
                            ), contentAlignment = Alignment.Center
                    ) {
                        androidx.compose.animation.AnimatedVisibility(
                            visibleState = visibleState,
                            enter = slideInVertically { -it },
                            exit = slideOutVertically { -it }) {
                            Text(
                                text = lastChar,
                                style = MaterialTheme.typography.headlineMedium,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }
                }
            }
        })
}