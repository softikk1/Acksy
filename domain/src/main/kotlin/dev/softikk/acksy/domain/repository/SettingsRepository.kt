package dev.softikk.acksy.domain.repository

import dev.softikk.acksy.domain.enums.AccentColor
import dev.softikk.acksy.domain.enums.Languages
import dev.softikk.acksy.domain.enums.Theme
import dev.softikk.acksy.domain.models.ErrorModel
import dev.softikk.acksy.domain.models.Response
import dev.softikk.acksy.domain.models.SettingsModel

interface SettingsRepository {
    suspend fun getSettings(): Response<SettingsModel, ErrorModel>
    suspend fun refreshSettings(
        language: Languages, theme: Theme, accentColor: AccentColor
    ): Response<Unit, ErrorModel>
}