package com.cookandroid.everytime.presentation.timetable

import com.cookandroid.everytime.presentation.timetable.component.timetable.TimetableSlot
import com.cookandroid.everytime.presentation.timetable.component.timetable.TimetableSlotColor

data class TimetableUiState(
    val selectedSlot: TimetableSlot? = null,
    val highlightedSlots: Map<TimetableSlot, TimetableSlotColor> = buildMap {
        (9..14).forEach { hour ->
            put(TimetableSlot(dayIndex = 0, hour = hour), TimetableSlotColor.CORAL)
        }
        (12..14).forEach { hour ->
            put(TimetableSlot(dayIndex = 2, hour = hour), TimetableSlotColor.GREEN)
        }
        (12..14).forEach { hour ->
            put(TimetableSlot(dayIndex = 4, hour = hour), TimetableSlotColor.ORANGE)
        }
    },
)

sealed interface TimetableSideEffect {
    data object NavigateBack : TimetableSideEffect
    data class Apply(val label: String?) : TimetableSideEffect
}
