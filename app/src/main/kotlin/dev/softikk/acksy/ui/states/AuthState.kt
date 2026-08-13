package dev.softikk.acksy.ui.states

import dev.softikk.acksy.domain.models.ErrorModel

sealed interface AuthState {
    data class Success<T>(val value: T) : AuthState
    data class Error(val errorModel: ErrorModel) : AuthState
    object Loading : AuthState
}