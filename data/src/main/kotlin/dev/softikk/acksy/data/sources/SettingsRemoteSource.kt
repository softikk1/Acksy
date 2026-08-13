package dev.softikk.acksy.data.sources

import dev.softikk.acksy.domain.models.ErrorModel
import dev.softikk.acksy.domain.models.Response
import dev.softikk.acksy.dev.softikk.acksy.entities.settings.GetSettingsRespondDto
import dev.softikk.acksy.dev.softikk.acksy.enums.AccentColor
import dev.softikk.acksy.dev.softikk.acksy.enums.Languages
import dev.softikk.acksy.dev.softikk.acksy.enums.Theme

interface SettingsRemoteSource {
    suspend fun getSettings(): Response<GetSettingsRespondDto, ErrorModel>
    suspend fun refreshSettings(
        language: Languages,
        theme: Theme,
        accentColor: AccentColor
    ): Response<Unit, ErrorModel>
}