package dev.softikk.acksy.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.wear.compose.material.Text
import dev.softikk.acksy.ui.theme.Dimens

data class Statistics(
    val count: Int, val description: String, val suffix: String? = null
)

@Composable
fun AcksyStatisticsFolder(
    modifier: Modifier = Modifier, items: List<Statistics>
) {
    val itemsSize = items.size
    Box(
        modifier = modifier
            .height(Dimens.heightComponent)
            .widthIn(max = Dimens.maxWidthElement)
            .background(
                color = MaterialTheme.colorScheme.secondary,
                shape = MaterialTheme.shapes.medium
            )
    ) {
        Row(
            modifier = Modifier.padding(horizontal = Dimens.Paddings.mediumPadding),
            verticalAlignment = Alignment.CenterVertically
        ) {
            repeat(itemsSize) { index ->
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(Dimens.Paddings.xsPadding),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        val element = items[index]
                        AcksyCounterStatistics(
                            count = element.count,
                            suffix = element.suffix,
                            color = MaterialTheme.colorScheme.onSurface,
                            style = MaterialTheme.typography.bodyLarge
                        )
                        Text(
                            text = element.description,
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSecondary
                        )
                    }
                }
            }
        }
    }
}