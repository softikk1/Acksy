package dev.softikk.acksy.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import dev.softikk.acksy.R
import dev.softikk.acksy.ui.components.AcksyBackScreenButton
import dev.softikk.acksy.ui.components.AcksyLanguageSelectWidget
import dev.softikk.acksy.ui.components.AcksyScreenName
import dev.softikk.acksy.ui.theme.Dimens

enum class SelectLanguage {
    Russian, English
}

@Composable
fun SelectLanguageScreen(
    backStack: NavBackStack<NavKey>
) {

    var selectedLanguageState by remember { mutableStateOf(SelectLanguage.Russian) }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(Dimens.Paddings.mediumPadding)
    ) {
        AcksyBackScreenButton(
            modifier = Modifier.padding(horizontal = Dimens.Paddings.mediumPadding),
            backStack = backStack
        )
        AcksyScreenName(
            modifier = Modifier.padding(horizontal = Dimens.Paddings.mediumPadding),
            text = stringResource(R.string.select_language_screen_name)
        )
        AcksyLanguageSelectWidget(
            modifier = Modifier.fillMaxWidth(),
            isSelect = selectedLanguageState == SelectLanguage.Russian,
            title = "Русский",
            description = stringResource(R.string.select_language_screen_russian)
        ) {
            selectedLanguageState = SelectLanguage.Russian
        }
        AcksyLanguageSelectWidget(
            modifier = Modifier.fillMaxWidth(),
            isSelect = selectedLanguageState == SelectLanguage.English,
            title = "English",
            description = stringResource(R.string.select_language_screen_english)
        ) {
            selectedLanguageState = SelectLanguage.English
        }
    }
}