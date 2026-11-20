package com.cookandroid.everytime.presentation.timetable

import com.cookandroid.everytime.presentation.timetable.component.timetable.TimetableSlot
import com.cookandroid.everytime.presentation.timetable.component.timetable.TimetableSlotColor
import com.cookandroid.everytime.presentation.timetable.component.timetable.expandedGridDays
import java.util.Locale

data class TimetableUiState(
    val selectedSlots: Set<TimetableSlot> = emptySet(),
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
    }
) {
    val timeLabel: String?
        get() {
            if (selectedSlots.isEmpty()) return null

            val firstDay = selectedSlots.minOf { it.dayIndex }
            val lastDay = selectedSlots.maxOf { it.dayIndex }
            val dayLabel = if (firstDay == lastDay) {
                expandedGridDays[firstDay]
            } else {
                "${expandedGridDays[firstDay]}~${expandedGridDays[lastDay]}"
            }
            val startHour = selectedSlots.minOf { it.hour }
            val endHour = selectedSlots.maxOf { it.hour } + 1

            return "$dayLabel, $startHour~${endHour}시"
        }

    val timeQueries: List<String>
        get() {
            val dayCodes = listOf("MON", "TUE", "WED", "THU", "FRI", "SAT", "SUN")

            return selectedSlots
                .groupBy { it.dayIndex }
                .toSortedMap()
                .map { (dayIndex, slots) ->
                    val startHour = slots.minOf { it.hour }
                    val endHour = slots.maxOf { it.hour } + 1

                    String.format(
                        Locale.ROOT,
                        "%s-%02d:00-%02d:00",
                        dayCodes[dayIndex],
                        startHour,
                        endHour
                    )
                }
        }
}

sealed interface TimetableSideEffect {
    data object NavigateBack : TimetableSideEffect

    data class Apply(
        val label: String?,
        val times: List<String>
    ) : TimetableSideEffect
}
