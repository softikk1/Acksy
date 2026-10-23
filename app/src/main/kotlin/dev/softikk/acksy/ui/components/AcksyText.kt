package dev.softikk.acksy.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.wear.compose.material.Text

@Composable
private fun AcksyText(
    modifier: Modifier = Modifier,
    textAlign: TextAlign = TextAlign.Center,
    text: String,
    style: TextStyle,
    color: Color
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = style,
            color = color,
            textAlign = textAlign
        )
    }
}

@Composable
fun AcksyScreenName(
    modifier: Modifier = Modifier,
    textAlign: TextAlign = TextAlign.Center,
    text: String
) {
    AcksyText(
        modifier = modifier,
        textAlign = textAlign,
        text = text,
        style = MaterialTheme.typography.headlineLarge,
        color = MaterialTheme.colorScheme.onSurface
    )
}

@Composable
fun AcksyTitle(
    modifier: Modifier = Modifier,
    textAlign: TextAlign = TextAlign.Center,
    text: String
) {
    AcksyText(
        modifier = modifier,
        textAlign = textAlign,
        text = text,
        style = MaterialTheme.typography.headlineMedium,
        color = MaterialTheme.colorScheme.onSurface
    )
}

@Composable
fun AcksySubtitle(
    modifier: Modifier = Modifier,
    textAlign: TextAlign = TextAlign.Center,
    text: String
) {
    AcksyText(
        modifier = modifier,
        textAlign = textAlign,
        text = text,
        style = MaterialTheme.typography.bodyMedium,
        color = MaterialTheme.colorScheme.onSurfaceVariant
    )
}