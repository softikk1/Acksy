package dev.softikk.acksy.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.softikk.acksy.domain.enums.AccentColor
import dev.softikk.acksy.domain.enums.Languages
import dev.softikk.acksy.domain.enums.Theme
import dev.softikk.acksy.domain.models.Response
import dev.softikk.acksy.domain.repository.SettingsRepository
import dev.softikk.acksy.ui.states.AuthState
import dev.softikk.acksy.ui.states.SettingsState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SettingsViewModel(private val settingsRepository: SettingsRepository) : ViewModel() {
    private val _state = MutableStateFlow<SettingsState>(SettingsState.Loading)
    val state = _state.asStateFlow()

    fun resetState() {
        _state.value = SettingsState.Loading
    }

    fun getSettings() {
        viewModelScope.launch {
            when (val result = settingsRepository.getSettings()) {
                is Response.Success -> {
                    _state.value = SettingsState.Success(result.value)
                }
                is Response.Failed -> {
                    _state.value = SettingsState.Error(result.value)
                }
            }
        }
    }

    fun refreshSettings(
        language: Languages, theme: Theme, accentColor: AccentColor
    ) {
        viewModelScope.launch {
            when (val result = settingsRepository.refreshSettings(
                language = language,
                theme = theme,
                accentColor = accentColor
            )) {
                is Response.Success -> {
                    _state.value = SettingsState.Success(result.value)
                }
                is Response.Failed -> {
                    _state.value = SettingsState.Error(result.value)
                }
            }
        }
    }
}