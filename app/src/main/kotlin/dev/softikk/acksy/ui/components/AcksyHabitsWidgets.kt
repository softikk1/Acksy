package dev.softikk.acksy.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.wear.compose.material.Text
import dev.softikk.acksy.ui.theme.Dimens

@Composable
fun AcksyTasksWidgets(selected: String, habitsWidgets: List<String>, onState: (String) -> Unit) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(Dimens.Paddings.smallPadding),
        verticalAlignment = Alignment.CenterVertically
    ) {
        habitsWidgets.forEach { text ->
            AcksyTaskWidgetItem(
                text = text, isSelected = selected == text
            ) {
                onState(text)
            }
        }
    }
}

@Composable
fun AcksyTaskWidgetItem(text: String, isSelected: Boolean, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .background(
                color = if (isSelected) MaterialTheme.colorScheme.secondary else Color.Transparent,
                shape = RoundedCornerShape(Dimens.Shapes.mediumShape)
            )
            .clickable(
                indication = null, interactionSource = remember { MutableInteractionSource() }) {
                onClick()
            }, contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier.padding(Dimens.Paddings.smallPadding),
            text = text,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}