package dev.softikk.acksy.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.softikk.acksy.domain.models.Response
import dev.softikk.acksy.domain.repository.StatisticsRepository
import dev.softikk.acksy.ui.states.AuthState
import dev.softikk.acksy.ui.states.StatisticsState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class StatisticsViewModel(private val statisticsRepository: StatisticsRepository) : ViewModel() {
    private val _state = MutableStateFlow<StatisticsState>(StatisticsState.Loading)
    val state = _state.asStateFlow()

    fun resetState() {
        _state.value = StatisticsState.Loading
    }

    fun getStatistics(timeZone: String) {
        viewModelScope.launch {
            when (val result = statisticsRepository.getStatistics(timeZone)) {
                is Response.Success -> {
                    _state.value = StatisticsState.Success(result.value)
                }
                is Response.Failed -> {
                    _state.value = StatisticsState.Error(result.value)
                }
            }
        }
    }
}