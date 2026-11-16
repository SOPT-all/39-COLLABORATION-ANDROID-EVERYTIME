package com.cookandroid.everytime.presentation.timetable

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cookandroid.everytime.presentation.timetable.component.timetable.TimetableSlot
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

    fun onSlotClick(slot: TimetableSlot) {
        _uiState.update { state ->
            val selectedSlot = if (state.selectedSlot == slot) null else slot
            state.copy(selectedSlot = selectedSlot)
        }
    }

    fun onBackClick() {
        sendExitSideEffect(TimetableSideEffect.NavigateBack)
    }

    fun onApplyClick() {
        val label = _uiState.value.selectedSlot?.timeLabel
        sendExitSideEffect(TimetableSideEffect.Apply(label = label))
    }

    private fun sendExitSideEffect(sideEffect: TimetableSideEffect) {
        viewModelScope.launch {
            if (exitRequested) return@launch

            exitRequested = true
            _sideEffect.send(sideEffect)
        }
    }
}
