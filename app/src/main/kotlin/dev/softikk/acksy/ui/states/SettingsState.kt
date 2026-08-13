package dev.softikk.acksy.ui.states

import dev.softikk.acksy.domain.models.ErrorModel

sealed interface SettingsState {
    data class Success<T>(val value: T) : SettingsState
    data class Error(val errorModel: ErrorModel) : SettingsState
    object Loading : SettingsState
}