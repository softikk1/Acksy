package dev.softikk.acksy.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import dev.softikk.acksy.R
import dev.softikk.acksy.ui.theme.Dimens

private val BackspaceButtonTextFieldSize = 48.dp

@Composable
fun AcksyTextField(
    modifier: Modifier,
    placeholder: String,
    isError: Boolean = false,
    state: TextFieldState = rememberTextFieldState(),
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    colors: TextFieldColors = TextFieldDefaults.colors(
        focusedTextColor = MaterialTheme.colorScheme.onSurface,
        unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
        errorTextColor = MaterialTheme.colorScheme.onSurface,
        focusedLabelColor = MaterialTheme.colorScheme.onSecondary,
        unfocusedLabelColor = MaterialTheme.colorScheme.onSecondary,
        errorLabelColor = MaterialTheme.colorScheme.onSecondary,
        cursorColor = MaterialTheme.colorScheme.primary,
        selectionColors = TextSelectionColors(
            handleColor = MaterialTheme.colorScheme.primary,
            backgroundColor = MaterialTheme.colorScheme.primary.copy(0.1f)
        ),
        focusedContainerColor = MaterialTheme.colorScheme.secondary,
        unfocusedContainerColor = MaterialTheme.colorScheme.secondary,
        errorContainerColor = MaterialTheme.colorScheme.secondary,
        focusedTrailingIconColor = MaterialTheme.colorScheme.onSecondary,
        unfocusedTrailingIconColor = MaterialTheme.colorScheme.onSecondary,
        errorTrailingIconColor = MaterialTheme.colorScheme.onSecondary
    )
) {
    BasicTextField(
        modifier = modifier
            .height(Dimens.heightComponent)
            .widthIn(max = Dimens.maxWidthElement),
        state = state,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        lineLimits = TextFieldLineLimits.SingleLine,
        decorator = TextFieldDefaults.decorator(
            state = state,
            enabled = true,
            lineLimits = TextFieldLineLimits.SingleLine,
            outputTransformation = null,
            interactionSource = interactionSource,
            colors = colors,
            placeholder = {
                Text(
                    text = placeholder, style = MaterialTheme.typography.bodyMedium
                )
            },
            trailingIcon = {
                Box(
                    modifier = Modifier.clickable {
                        state.edit { replace(0, state.text.length, "") }
                    }) {
                    Icon(
                        modifier = Modifier.size(BackspaceButtonTextFieldSize),
                        imageVector = ImageVector.vectorResource(R.drawable.x),
                        contentDescription = null
                    )
                }
            },
            container = {
                TextFieldDefaults.Container(
                    enabled = true,
                    isError = isError,
                    interactionSource = interactionSource,
                    colors = colors,
                    shape = MaterialTheme.shapes.small,
                    focusedIndicatorLineThickness = 0.dp,
                    unfocusedIndicatorLineThickness = 0.dp
                )
            })
    )
}