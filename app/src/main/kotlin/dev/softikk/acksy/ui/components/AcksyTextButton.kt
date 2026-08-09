package dev.softikk.acksy.ui.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import dev.softikk.acksy.ui.theme.Dimens
import dev.softikk.acksy.ui.theme.MilkyWhite
import dev.softikk.acksy.ui.theme.White

@Composable
fun AcksyTextButton(
    modifier: Modifier = Modifier,
    text: String,
    containerColor: Color,
    contentColor: Color = if (isSystemInDarkTheme()) White else MilkyWhite,
    onClick: () -> Unit = {}
) {
    Button(
        modifier = modifier
            .height(Dimens.heightComponent)
            .widthIn(max = Dimens.maxWidthElement), colors = ButtonColors(
            containerColor = containerColor,
            contentColor = contentColor,
            disabledContainerColor = containerColor,
            disabledContentColor = contentColor
        ), shape = MaterialTheme.shapes.small, onClick = {
            onClick()
        }) {
        Text(
            text = text, style = MaterialTheme.typography.bodyLarge, color = contentColor
        )
    }
}
