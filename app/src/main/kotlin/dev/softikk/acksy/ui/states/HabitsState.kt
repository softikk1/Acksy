package dev.softikk.acksy.ui.states

import dev.softikk.acksy.domain.models.ErrorModel

sealed interface HabitsState {
    data class Success<T>(val value: T) : HabitsState
    data class Error(val errorModel: ErrorModel) : HabitsState
    object Loading : HabitsState
}