package com.cookandroid.everytime.presentation.timetable

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

    fun onSlotClick(slot: TimetableSlot) {
        _uiState.update { state ->
            val selectedSlot = if (state.selectedSlot == slot) null else slot
            state.copy(selectedSlot = selectedSlot)
        }
    }

    fun onBackClick() {
        viewModelScope.launch {
            _sideEffect.send(TimetableSideEffect.NavigateBack)
        }
    }

    fun onApplyClick() {
        viewModelScope.launch {
            _sideEffect.send(TimetableSideEffect.Apply(label = uiState.value.timeLabel))
        }
    }
}
