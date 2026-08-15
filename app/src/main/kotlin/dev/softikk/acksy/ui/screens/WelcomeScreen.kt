package dev.softikk.acksy.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withLink
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.wear.compose.material.Text
import dev.softikk.acksy.R
import dev.softikk.acksy.ui.components.AcksyTextButton
import dev.softikk.acksy.ui.navigation.Routes
import dev.softikk.acksy.ui.theme.AcksyTheme
import dev.softikk.acksy.ui.theme.Dimens
import dev.softikk.acksy.ui.viewmodels.AuthViewModel

private val MaxWelcomeScreenContentPadding = 300.dp

@Composable
fun WelcomeScreen(authViewModel: AuthViewModel, backStack: NavBackStack<NavKey>) {
    AcksyTheme {
        Box(
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(Dimens.Paddings.mediumPadding),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(Dimens.Paddings.mediumPadding)
                ) {
                    Image(
                        imageVector = ImageVector.vectorResource(R.drawable.logo),
                        contentDescription = null
                    )
                    Text(
                        text = stringResource(R.string.app_name),
                        style = MaterialTheme.typography.headlineLarge,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }

                Spacer(
                    modifier = Modifier
                        .height(MaxWelcomeScreenContentPadding)
                        .weight(1f, fill = false)
                )

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(Dimens.Paddings.largePadding)
                ) {
                    Row(
                        modifier = Modifier.clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }) {
                        backStack.add(Routes.SelectLanguage)
                    },
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(Dimens.Paddings.xsPadding)
                    ) {
                        Text(
                            text = stringResource(R.string.language),
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Image(
                            imageVector = ImageVector.vectorResource(R.drawable.down_arrow),
                            contentDescription = null,
                            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurfaceVariant)
                        )
                    }

                    AcksyTextButton(
                        modifier = Modifier.fillMaxWidth(),
                        text = stringResource(R.string.text_button_welcome_screen),
                        containerColor = MaterialTheme.colorScheme.primary
                    ) {
                        backStack.add(Routes.EnterEmail)
                    }

                    Box {
                        Text(
                            text = buildAnnotatedString {
                                append(stringResource(R.string.legal_text_welcome_screen_1) + " ")
                                withLink(
                                    LinkAnnotation.Url(
                                        url = "", styles = TextLinkStyles(
                                            SpanStyle(
                                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                                textDecoration = TextDecoration.Underline
                                            )
                                        )
                                    )
                                ) {
                                    append(stringResource(R.string.legal_text_welcome_screen_2))
                                }
                                append(" " + stringResource(R.string.legal_text_welcome_screen_3) + " ")
                                withLink(
                                    LinkAnnotation.Url(
                                        url = "", styles = TextLinkStyles(
                                            SpanStyle(
                                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                                textDecoration = TextDecoration.Underline
                                            )
                                        )
                                    )
                                ) {
                                    append(stringResource(R.string.legal_text_welcome_screen_4))
                                }
                            },
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}

