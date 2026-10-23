package dev.softikk.acksy.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.lifecycle.compose.dropUnlessResumed
import dev.softikk.acksy.R
import dev.softikk.acksy.ui.components.AcksyBackScreenButton
import dev.softikk.acksy.ui.components.AcksyCodeField
import dev.softikk.acksy.ui.components.AcksySubtitle
import dev.softikk.acksy.ui.components.AcksyTextButton
import dev.softikk.acksy.ui.components.AcksyTitle
import dev.softikk.acksy.ui.navigation.Routes
import dev.softikk.acksy.ui.theme.AcksyTheme
import dev.softikk.acksy.ui.theme.Dimens

@Composable
fun EnterVerifCodeScreen(backStack: NavBackStack<NavKey>) {
    var verificationCodeTextFieldState by remember { mutableStateOf("") }
    val email = "email@gmail.com"

    AcksyTheme {
        Box(
            modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(Dimens.Paddings.mediumPadding),
                verticalArrangement = Arrangement.spacedBy(Dimens.Paddings.xl2Padding)
            ) {
                AcksyBackScreenButton(
                    modifier = Modifier.fillMaxWidth(), backStack = backStack
                )
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(Dimens.Paddings.mediumPadding)
                ) {
                    AcksyTitle(
                        text = stringResource(R.string.enter_verif_code_screen_title)
                    )

                    AcksySubtitle(
                        text = stringResource(R.string.enter_verif_code_screen_description, email)
                    )
                }

                Column(
                    verticalArrangement = Arrangement.spacedBy(Dimens.Paddings.largePadding),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AcksyCodeField(
                        value = verificationCodeTextFieldState
                    ) {
                        verificationCodeTextFieldState = it
                    }
                    AcksyTextButton(
                        modifier = Modifier.fillMaxWidth(),
                        text = stringResource(R.string.auth_button_text),
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.surface,
                        onClick = dropUnlessResumed {
                            backStack.add(Routes.Home)
                        })
                }
            }
        }
    }
}