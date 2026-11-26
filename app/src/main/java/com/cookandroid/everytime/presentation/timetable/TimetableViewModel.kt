package com.cookandroid.everytime.presentation.timetable

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cookandroid.everytime.presentation.timetable.component.timetable.TimetableSlot
import com.cookandroid.everytime.presentation.timetable.mapper.TimetableSelectionMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class TimetableViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(TimetableUiState())
    val uiState = _uiState.asStateFlow()

    private val _sideEffect = Channel<TimetableSideEffect>(Channel.BUFFERED)
    val sideEffect = _sideEffect.receiveAsFlow()

    private var exitRequested = false

    fun onDayClick(dayIndex: Int) {
        updateSelection { state ->
            val days = state.selectedDayIndices

            state.copy(
                selectedDayIndices = if (dayIndex in days) {
                    days - dayIndex
                } else {
                    days + dayIndex
                },
            )
        }
    }

    fun onHourClick(hour: Int) {
        updateSelection { state ->
            val hours = state.selectedHours

            state.copy(
                selectedHours = if (hour in hours) {
                    hours - hour
                } else {
                    hours + hour
                },
            )
        }
    }

    fun onSlotClick(slot: TimetableSlot) {
        updateSelection { state ->
            val slots = state.individuallySelectedSlots

            state.copy(
                individuallySelectedSlots = if (slot in slots) {
                    slots - slot
                } else {
                    slots + slot
                },
            )
        }
    }

    fun onBackClick() {
        sendExitSideEffect(TimetableSideEffect.NavigateBack)
    }

    fun onApplyClick() {
        val state = _uiState.value
        val result = createSelectionResult(state)

        sendExitSideEffect(
            TimetableSideEffect.Apply(
                label = result.label,
                times = result.times,
            ),
        )
    }

    private fun updateSelection(
        transform: (TimetableUiState) -> TimetableUiState,
    ) {
        _uiState.update { current ->
            val updated = transform(current)
            val result = createSelectionResult(updated)

            updated.copy(selectedSlots = result.selectedSlots)
        }
    }

    private fun createSelectionResult(
        state: TimetableUiState,
    ) = TimetableSelectionMapper.createResult(
        individuallySelectedSlots = state.individuallySelectedSlots,
        selectedDayIndices = state.selectedDayIndices,
        selectedHours = state.selectedHours,
    )

    private fun sendExitSideEffect(sideEffect: TimetableSideEffect) {
        viewModelScope.launch {
            if (exitRequested) return@launch

            exitRequested = true
            _sideEffect.send(sideEffect)
        }
    }
}
