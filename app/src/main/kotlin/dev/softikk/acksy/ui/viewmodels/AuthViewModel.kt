package dev.softikk.acksy.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.softikk.acksy.domain.models.Response
import dev.softikk.acksy.domain.repository.AuthRepository
import dev.softikk.acksy.ui.states.AuthState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.uuid.Uuid

class AuthViewModel(
    private val authRepository: AuthRepository
) : ViewModel() {
    private val _state = MutableStateFlow<AuthState>(AuthState.Loading)
    val state = _state.asStateFlow()

    fun resetState() {
        _state.value = AuthState.Loading
    }

    fun sendCodeEmail(email: String) {
        viewModelScope.launch {
            when (val result = authRepository.sendCodeEmail(email)) {
                is Response.Success -> {
                    _state.value = AuthState.Success(Unit)
                }

                is Response.Failed -> {
                    _state.value = AuthState.Error(result.value)
                }
            }
        }
    }

    fun confirmCodeEmail(email: String, code: String) {
        viewModelScope.launch {
            when (val result = authRepository.confirmCodeEmail(
                email = email,
                code = code
            )) {
                is Response.Success -> {
                    _state.value = AuthState.Success(result.value)
                }
                is Response.Failed -> {
                    _state.value = AuthState.Error(result.value)
                }
            }
        }
    }

    fun login(email: String, tempToken: Uuid) {
        viewModelScope.launch {
            when (val result = authRepository.login(
                email = email,
                tempToken = tempToken
            )) {
                is Response.Success -> {
                    _state.value = AuthState.Success(result.value)
                }
                is Response.Failed -> {
                    _state.value = AuthState.Error(result.value)
                }
            }
        }
    }

    fun register(email: String, tempToken: Uuid, username: String) {
        viewModelScope.launch {
            when (val result = authRepository.register(
                email = email,
                tempToken = tempToken,
                username = username
            )) {
                is Response.Success -> {
                    _state.value = AuthState.Success(result.value)
                }
                is Response.Failed -> {
                    _state.value = AuthState.Error(result.value)
                }
            }
        }
    }
}