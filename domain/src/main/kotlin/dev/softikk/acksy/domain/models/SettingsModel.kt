package dev.softikk.acksy.domain.models

import dev.softikk.acksy.domain.enums.AccentColor
import dev.softikk.acksy.domain.enums.Languages
import dev.softikk.acksy.domain.enums.Theme

data class SettingsModel(
    val theme: Theme, val language: Languages, val accentColor: AccentColor
)
