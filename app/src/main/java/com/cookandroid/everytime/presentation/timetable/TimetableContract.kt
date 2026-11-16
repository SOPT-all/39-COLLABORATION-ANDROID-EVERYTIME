package com.cookandroid.everytime.presentation.timetable

data class TimetableSlot(
    val dayIndex: Int,
    val hour: Int,
)

data class TimetableUiState(
    val timeLabel: String = "월, 9~10시",
    val selectedSlot: TimetableSlot? = null,
)

sealed interface TimetableSideEffect {
    data object NavigateBack : TimetableSideEffect
    data class Apply(val label: String) : TimetableSideEffect
}
