package dev.softikk.acksy.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import dev.softikk.acksy.R

@Composable
fun AcksyBackScreenButton(modifier: Modifier = Modifier, backStack: NavBackStack<NavKey>) {
    Box(
        modifier
            .clickable(
                indication = null, interactionSource = remember { MutableInteractionSource() }) {
                backStack.removeLastOrNull()
            }) {
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.arrow_back),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurface
        )
    }
}