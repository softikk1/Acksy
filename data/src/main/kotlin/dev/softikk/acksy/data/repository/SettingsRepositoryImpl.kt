package dev.softikk.acksy.data.repository

import dev.softikk.acksy.data.sources.SettingsRemoteSource
import dev.softikk.acksy.dev.softikk.acksy.enums.AccentColor
import dev.softikk.acksy.dev.softikk.acksy.enums.Languages
import dev.softikk.acksy.dev.softikk.acksy.enums.Theme
import dev.softikk.acksy.domain.models.ErrorModel
import dev.softikk.acksy.domain.models.Response
import dev.softikk.acksy.domain.models.SettingsModel
import dev.softikk.acksy.domain.repository.SettingsRepository
import dev.softikk.acksy.domain.enums.AccentColor as AccentColorC
import dev.softikk.acksy.domain.enums.Languages as LanguagesC
import dev.softikk.acksy.domain.enums.Theme as ThemeC

class SettingsRepositoryImpl(private val settingsSource: SettingsRemoteSource) :
    SettingsRepository {
    override suspend fun getSettings(): Response<SettingsModel, ErrorModel> {
        return when (val result = settingsSource.getSettings()) {
            is Response.Success -> {
                val settingsAppModel = result.value.settings
                Response.Success(
                    SettingsModel(
                        theme = when (settingsAppModel.theme) {
                            Theme.System -> ThemeC.System
                            Theme.Light -> ThemeC.Light
                            Theme.Dark -> ThemeC.Dark
                        }, language = when (settingsAppModel.language) {
                            Languages.Russian -> LanguagesC.Russian
                            Languages.English -> LanguagesC.English
                        }, accentColor = when (settingsAppModel.accentColor) {
                            AccentColor.Main -> AccentColorC.Main
                            AccentColor.SmartDay -> AccentColorC.SmartDay
                            AccentColor.Red -> AccentColorC.Red
                            AccentColor.Blue -> AccentColorC.Blue
                            AccentColor.Orange -> AccentColorC.Orange
                            AccentColor.Green -> AccentColorC.Green
                            AccentColor.Purple -> AccentColorC.Purple
                        }
                    )
                )
            }

            is Response.Failed -> {
                result
            }
        }
    }

    override suspend fun refreshSettings(
        language: LanguagesC, theme: ThemeC, accentColor: AccentColorC
    ): Response<Unit, ErrorModel> {
        return settingsSource.refreshSettings(
            language = when (language) {
                LanguagesC.Russian -> Languages.Russian
                LanguagesC.English -> Languages.English
            }, theme = when (theme) {
                ThemeC.System -> Theme.System
                ThemeC.Light -> Theme.Light
                ThemeC.Dark -> Theme.Dark
            }, accentColor = when (accentColor) {
                AccentColorC.Main -> AccentColor.Main
                AccentColorC.SmartDay -> AccentColor.SmartDay
                AccentColorC.Red -> AccentColor.Red
                AccentColorC.Blue -> AccentColor.Blue
                AccentColorC.Orange -> AccentColor.Orange
                AccentColorC.Green -> AccentColor.Green
                AccentColorC.Purple -> AccentColor.Purple
            }
        )
    }
}