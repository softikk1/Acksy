package dev.softikk.acksy.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.wear.compose.material.Text
import dev.softikk.acksy.ui.theme.Dimens

@Composable
fun AcksyLanguageSelectWidget(
    modifier: Modifier = Modifier,
    isSelect: Boolean,
    title: String,
    description: String,
    onClick: () -> Unit = {}
) {
    Row(
        modifier = modifier.clickable(onClick = onClick),
        horizontalArrangement = Arrangement.spacedBy(Dimens.Paddings.smallPadding),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            modifier = Modifier
                .padding(start = Dimens.Paddings.mediumPadding)
                .padding(vertical = Dimens.Paddings.smallPadding)
                .padding(end = Dimens.Paddings.smallPadding),
            selected = isSelect,
            onClick = null,
            colors = RadioButtonDefaults.colors(
                selectedColor = MaterialTheme.colorScheme.primary,
                unselectedColor = MaterialTheme.colorScheme.onSurfaceVariant
            )
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(Dimens.Paddings.xsPadding)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = description,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}