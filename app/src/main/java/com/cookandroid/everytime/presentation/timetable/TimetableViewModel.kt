package com.cookandroid.everytime.presentation.timetable

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cookandroid.everytime.presentation.timetable.component.timetable.TimetableSlot
import com.cookandroid.everytime.presentation.timetable.component.timetable.expandedGridDays
import com.cookandroid.everytime.presentation.timetable.component.timetable.expandedGridHours
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TimetableViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(TimetableUiState())
    val uiState = _uiState.asStateFlow()

    private val _sideEffect = Channel<TimetableSideEffect>(Channel.BUFFERED)
    val sideEffect = _sideEffect.receiveAsFlow()

    private var exitRequested = false

    fun onDayClick(dayIndex: Int) {
        val slots = expandedGridHours.map { hour ->
            TimetableSlot(dayIndex = dayIndex, hour = hour)
        }.toSet()
        toggleSelection(slots)
    }

    fun onHourClick(hour: Int) {
        val slots = expandedGridDays.indices.map { dayIndex ->
            TimetableSlot(dayIndex = dayIndex, hour = hour)
        }.toSet()
        toggleSelection(slots)
    }

    fun onBackClick() {
        sendExitSideEffect(TimetableSideEffect.NavigateBack)
    }

    fun onApplyClick() {
        sendExitSideEffect(TimetableSideEffect.Apply)
    }

    private fun toggleSelection(slots: Set<TimetableSlot>) {
        _uiState.update { state ->
            state.copy(selectedSlots = if (state.selectedSlots == slots) emptySet() else slots)
        }
    }

    private fun sendExitSideEffect(sideEffect: TimetableSideEffect) {
        viewModelScope.launch {
            if (exitRequested) return@launch

            exitRequested = true
            _sideEffect.send(sideEffect)
        }
    }
}
