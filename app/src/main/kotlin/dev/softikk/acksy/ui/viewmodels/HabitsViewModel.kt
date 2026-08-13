package dev.softikk.acksy.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.softikk.acksy.domain.models.Response
import dev.softikk.acksy.domain.models.habits.HabitScheduleModel
import dev.softikk.acksy.domain.repository.HabitsRepository
import dev.softikk.acksy.ui.states.AuthState
import dev.softikk.acksy.ui.states.HabitsState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.uuid.Uuid

class HabitsViewModel(private val habitsRepository: HabitsRepository) : ViewModel() {
    private val _state = MutableStateFlow<HabitsState>(HabitsState.Loading)
    val state = _state.asStateFlow()

    fun resetState() {
        _state.value = HabitsState.Loading
    }

    fun getHabits(userId: Uuid, timeZone: String) {
        viewModelScope.launch {
            when (val result = habitsRepository.getHabits(
                userId = userId, timeZone = timeZone
            )) {
                is Response.Success -> {
                    _state.value = HabitsState.Success(result.value)
                }
                is Response.Failed -> {
                    _state.value = HabitsState.Error(result.value)
                }
            }
        }
    }

    fun createHabit(title: String, schedule: HabitScheduleModel) {
        viewModelScope.launch {
            when (val result = habitsRepository.createHabit(
                title = title, schedule = schedule
            )) {
                is Response.Success -> {
                    _state.value = HabitsState.Success(result.value)
                }
                is Response.Failed -> {
                    _state.value = HabitsState.Error(result.value)
                }
            }
        }
    }

    fun confirmHabit(habitId: Uuid) {
        viewModelScope.launch {
            when (val result = habitsRepository.confirmHabit(habitId)) {
                is Response.Success -> {
                    _state.value = HabitsState.Success(result.value)
                }
                is Response.Failed -> {
                    _state.value = HabitsState.Error(result.value)
                }
            }
        }
    }

    fun deleteHabit(habitId: Uuid) {
        viewModelScope.launch {
            when (val result = habitsRepository.deleteHabit(habitId)) {
                is Response.Success -> {
                    _state.value = HabitsState.Success(result.value)
                }
                is Response.Failed -> {
                    _state.value = HabitsState.Error(result.value)
                }
            }
        }
    }

    fun habitDetails(habitId: Uuid) {
        viewModelScope.launch {
            when (val result = habitsRepository.habitDetails(habitId)) {
                is Response.Success -> {
                    _state.value = HabitsState.Success(result.value)
                }
                is Response.Failed -> {
                    _state.value = HabitsState.Error(result.value)
                }
            }
        }
    }

    fun deleteConfirmHabit(
        habitId: Uuid, confirmId: Uuid
    ) {
        viewModelScope.launch {
            when (val result = habitsRepository.deleteConfirmHabit(
                habitId = habitId,
                confirmId = confirmId
            )) {
                is Response.Success -> {
                    _state.value = HabitsState.Success(result.value)
                }
                is Response.Failed -> {
                    _state.value = HabitsState.Error(result.value)
                }
            }
        }
    }
}