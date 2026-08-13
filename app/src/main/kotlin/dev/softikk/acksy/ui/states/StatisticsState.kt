package dev.softikk.acksy.ui.states

import dev.softikk.acksy.domain.models.ErrorModel

sealed interface StatisticsState {
    data class Success<T>(val value: T) : StatisticsState
    data class Error(val errorModel: ErrorModel) : StatisticsState
    object Loading : StatisticsState
}