package dev.softikk.acksy.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import dev.softikk.acksy.R
import dev.softikk.acksy.ui.components.AcksyAnimButtonsGroup
import dev.softikk.acksy.ui.components.AcksyListColors
import dev.softikk.acksy.ui.components.AcksyScreenName
import dev.softikk.acksy.ui.components.AcksySubtitle
import dev.softikk.acksy.ui.theme.Dimens
import dev.softikk.acksy.ui.theme.PastelBlue

private val HeightLanguageSelectorComponent = 48.dp

@Composable
fun SettingScreen() {
    var themeState by remember { mutableIntStateOf(0) }
    var listColorsState by remember { mutableStateOf(PastelBlue) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(Dimens.Paddings.mediumPadding)
    ) {
        AcksyScreenName(
            modifier = Modifier.padding(horizontal = Dimens.Paddings.mediumPadding),
            text = stringResource(R.string.settings_screen_name)
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(Dimens.Paddings.smallPadding)
        ) {
            AcksySubtitle(
                modifier = Modifier.padding(horizontal = Dimens.Paddings.mediumPadding),
                text = stringResource(R.string.settings_screen_subtitle_language)
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(HeightLanguageSelectorComponent)
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ) {

                    }, verticalArrangement = Arrangement.SpaceBetween
            ) {
                Spacer(
                    modifier = Modifier
                        .height(1.dp)
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.2f))
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = Dimens.Paddings.mediumPadding),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(Dimens.Paddings.mediumPadding),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            modifier = Modifier.size(24.dp),
                            imageVector = ImageVector.vectorResource(R.drawable.globe),
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onSurface
                        )
                        Text(
                            text = "Langtext",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                    Icon(
                        modifier = Modifier.size(24.dp),
                        imageVector = ImageVector.vectorResource(R.drawable.right_arrow),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurface
                    )
                }
                Spacer(
                    modifier = Modifier
                        .height(1.dp)
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.2f))
                )
            }
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(Dimens.Paddings.smallPadding)
        ) {
            AcksySubtitle(
                modifier = Modifier.padding(horizontal = Dimens.Paddings.mediumPadding),
                text = stringResource(R.string.settings_screen_subtitle_theme)
            )
            AcksyAnimButtonsGroup(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = Dimens.Paddings.mediumPadding),
                state = themeState,
                buttons = listOf(
                    stringResource(R.string.settings_screen_theme_system_item),
                    stringResource(R.string.settings_screen_theme_light_item),
                    stringResource(R.string.settings_screen_theme_dark_item)
                )
            ) {
                themeState = it
            }
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(Dimens.Paddings.smallPadding)
        ) {
            AcksySubtitle(
                modifier = Modifier.padding(horizontal = Dimens.Paddings.mediumPadding),
                text = stringResource(R.string.settings_screen_subtitle_accent_color)
            )
            AcksyListColors(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = Dimens.Paddings.mediumPadding),
                state = listColorsState
            ) {
                listColorsState = it
            }
        }
    }
}