package com.cookandroid.everytime.presentation.timetable.mapper

import com.cookandroid.everytime.presentation.timetable.component.timetable.TimetableSlot
import com.cookandroid.everytime.presentation.timetable.component.timetable.expandedGridDays
import com.cookandroid.everytime.presentation.timetable.component.timetable.expandedGridHours
import java.util.Locale

data class TimetableSelectionResult(
    val selectedSlots: Set<TimetableSlot>,
    val label: String?,
    val times: List<String>,
)

private data class TimetableRange(
    val dayIndex: Int,
    val startHour: Int,
    val endHour: Int,
)

object TimetableSelectionMapper {
    fun createResult(
        individuallySelectedSlots: Set<TimetableSlot>,
        selectedDayIndices: Set<Int>,
        selectedHours: Set<Int>,
    ): TimetableSelectionResult {
        val selectedSlots = buildSet {
            addAll(individuallySelectedSlots)

            selectedDayIndices.forEach { dayIndex ->
                expandedGridHours.forEach { hour ->
                    add(TimetableSlot(dayIndex = dayIndex, hour = hour))
                }
            }

            selectedHours.forEach { hour ->
                expandedGridDays.indices.forEach { dayIndex ->
                    add(TimetableSlot(dayIndex = dayIndex, hour = hour))
                }
            }
        }

        val ranges = createRanges(selectedSlots)
        val dayLabels = listOf("월", "화", "수", "목", "금", "토", "일")
        val dayCodes = listOf("MON", "TUE", "WED", "THU", "FRI", "SAT", "SUN")

        val label = ranges
            .groupBy { it.dayIndex }
            .toSortedMap()
            .map { (dayIndex, dayRanges) ->
                val times = dayRanges.joinToString(", ") { range ->
                    "${range.startHour}~${range.endHour}"
                }
                "${dayLabels[dayIndex]} ${times}시"
            }
            .takeIf { it.isNotEmpty() }
            ?.joinToString(" · ")

        val times = ranges.map { range ->
            String.format(
                Locale.ROOT,
                "%s-%02d:00-%02d:00",
                dayCodes[range.dayIndex],
                range.startHour,
                range.endHour,
            )
        }

        return TimetableSelectionResult(
            selectedSlots = selectedSlots,
            label = label,
            times = times,
        )
    }

    private fun createRanges(
        selectedSlots: Set<TimetableSlot>,
    ): List<TimetableRange> {
        return selectedSlots
            .groupBy { it.dayIndex }
            .toSortedMap()
            .flatMap { (dayIndex, slots) ->
                val hours = slots
                    .map { it.hour }
                    .distinct()
                    .sorted()

                if (hours.isEmpty()) {
                    return@flatMap emptyList()
                }

                val ranges = mutableListOf<TimetableRange>()
                var startHour = hours.first()
                var endHour = startHour + 1

                hours.drop(1).forEach { hour ->
                    if (hour == endHour) {
                        endHour += 1
                    } else {
                        ranges += TimetableRange(
                            dayIndex = dayIndex,
                            startHour = startHour,
                            endHour = endHour,
                        )
                        startHour = hour
                        endHour = hour + 1
                    }
                }

                ranges += TimetableRange(
                    dayIndex = dayIndex,
                    startHour = startHour,
                    endHour = endHour,
                )

                ranges
            }
    }
}
