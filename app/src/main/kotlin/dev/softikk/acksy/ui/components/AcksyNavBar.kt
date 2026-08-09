package dev.softikk.acksy.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import dev.softikk.acksy.R
import dev.softikk.acksy.ui.navigation.Routes
import dev.softikk.acksy.ui.theme.Dimens
import dev.softikk.acksy.ui.theme.White

private val ElevationShadow = 1.5.dp
private val WidthNavBar = 220.dp

@Composable
fun AcksyNavBar(
    modifier: Modifier = Modifier, backStack: NavBackStack<NavKey>
) {
    val actionButtonColor =
        if (isSystemInDarkTheme()) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
    val shape = MaterialTheme.shapes.large
    var visible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        visible = true
    }
    Box(
        modifier = modifier, contentAlignment = Alignment.CenterStart
    ) {
        AnimatedVisibility(
            visible = visible,
            enter = slideInHorizontally { -it / 2 },
            exit = slideOutHorizontally { -it / 2 }) {
            Box(
                modifier = Modifier
                    .padding(start = WidthNavBar + Dimens.Paddings.mediumPadding)
                    .size(Dimens.heightComponent)
                    .shadow(
                        elevation = ElevationShadow, shape = shape
                    )
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }) {
                        backStack.add(Routes.CreateHabit)
                    }
                    .background(color = actionButtonColor, shape = shape),
                contentAlignment = Alignment.Center) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.plus),
                    contentDescription = null,
                    tint = White
                )
            }
        }
        Row(
            modifier = Modifier
                .width(WidthNavBar)
                .shadow(
                    elevation = ElevationShadow, shape = shape
                )
                .background(
                    color = MaterialTheme.colorScheme.surface, shape = shape
                )
        ) {
            ItemNavBar(
                icon = ImageVector.vectorResource(R.drawable.home),
                isActive = backStack.lastOrNull() == Routes.Home
            ) {
                backStack.add(Routes.Home)
            }
            ItemNavBar(
                icon = ImageVector.vectorResource(R.drawable.statisticas),
                isActive = backStack.lastOrNull() == Routes.Statistics
            ) {
                backStack.add(Routes.Statistics)
            }
            ItemNavBar(
                icon = ImageVector.vectorResource(R.drawable.settings),
                isActive = backStack.lastOrNull() == Routes.Settings
            ) {
                backStack.add(Routes.Settings)
            }
        }
    }
}

@Composable
fun RowScope.ItemNavBar(icon: ImageVector, isActive: Boolean, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .height(Dimens.heightComponent)
            .weight(1f)
            .clickable(
                indication = null, interactionSource = remember { MutableInteractionSource() }) {
                onClick()
            }, contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = if (isActive) MaterialTheme.colorScheme.onSurface else MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}