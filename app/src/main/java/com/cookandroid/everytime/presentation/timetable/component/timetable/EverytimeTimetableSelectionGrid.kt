package com.cookandroid.everytime.presentation.timetable.component.timetable

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cookandroid.everytime.core.designsystem.theme.EverytimeTheme
import com.cookandroid.everytime.core.util.noRippleClickable

data class TimetableSlot(
    val dayIndex: Int,
    val hour: Int
) {
    val timeLabel: String
        get() = "${expandedGridDays[dayIndex]}, $hour~${hour + 1}시"
}

enum class TimetableSlotColor {
    CORAL,
    GREEN,
    ORANGE
}

@Composable
fun EverytimeTimetableSelectionGrid(
    highlightedSlots: Map<TimetableSlot, TimetableSlotColor>,
    selectedSlot: TimetableSlot?,
    onSlotClick: (TimetableSlot) -> Unit,
    modifier: Modifier = Modifier
) {
    val layout = expandedGridLayout

    Box(modifier = modifier) {
        EverytimeExpandedTimetableGrid()

        Column(
            modifier = Modifier
                .matchParentSize()
                .clip(RoundedCornerShape(layout.cornerRadius))
        ) {
            Spacer(modifier = Modifier.height(layout.headerHeight))

            layout.hours.forEach { hourLabel ->
                val hour = hourLabel.toInt()

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(layout.rowHeight)
                ) {
                    Spacer(modifier = Modifier.width(layout.timeColumnWidth))

                    repeat(layout.days.size) { dayIndex ->
                        val slot = TimetableSlot(dayIndex = dayIndex, hour = hour)
                        val isSelected = slot == selectedSlot
                        val cellModifier = if (isSelected) {
                            Modifier
                                .background(color = EverytimeTheme.colors.neutral400)
                                .border(width = 1.dp, color = EverytimeTheme.colors.neutral200)
                        } else {
                            val color = when (highlightedSlots[slot]) {
                                TimetableSlotColor.CORAL ->
                                    EverytimeTheme.colors.timetableCoral.copy(alpha = 0.4f)
                                TimetableSlotColor.GREEN ->
                                    EverytimeTheme.colors.timetableGreen.copy(alpha = 0.4f)
                                TimetableSlotColor.ORANGE ->
                                    EverytimeTheme.colors.timetableOrange.copy(alpha = 0.4f)
                                null -> null
                            }
                            if (color == null) Modifier else Modifier.background(color = color)
                        }

                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxHeight()
                                .then(cellModifier)
                                .noRippleClickable(onClick = { onSlotClick(slot) })
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun EverytimeTimetableSelectionGridPreview() {
    EverytimeTheme {
        EverytimeTimetableSelectionGrid(
            highlightedSlots = mapOf(
                TimetableSlot(dayIndex = 0, hour = 9) to TimetableSlotColor.CORAL,
                TimetableSlot(dayIndex = 2, hour = 12) to TimetableSlotColor.GREEN,
                TimetableSlot(dayIndex = 4, hour = 12) to TimetableSlotColor.ORANGE
            ),
            selectedSlot = null,
            onSlotClick = {}
        )
    }
}
