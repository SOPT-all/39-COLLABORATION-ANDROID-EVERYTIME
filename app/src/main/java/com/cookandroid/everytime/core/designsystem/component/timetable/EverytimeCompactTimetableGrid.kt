package com.cookandroid.everytime.core.designsystem.component.timetable

import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cookandroid.everytime.core.designsystem.theme.EverytimeTheme

private val compactGridLayout = TimetableGridLayout(
    days = timetableWeekDays,
    hours = listOf("9", "10", "11", "12", "1"),
    timeColumnWidth = 19.dp,
    headerHeight = 18.dp,
    rowHeight = 43.dp,
    cornerRadius = 12.dp,
    lastRowHeight = 30.dp
)

@Composable
fun EverytimeCompactTimetableGrid(
    modifier: Modifier = Modifier
) {
    TimetableGrid(
        layout = compactGridLayout,
        labelColor = EverytimeTheme.colors.timetableRose,
        labelStyle = EverytimeTheme.typography.caption5,
        hourAlignment = Alignment.TopEnd,
        modifier = modifier,
        hourLabelTopPadding = 1.dp,
        hourLabelEndPadding = 1.dp,
    )
}

@Composable
@Preview(showBackground = true)
private fun EverytimeCompactTimetableGridPreview() {
    EverytimeTheme {
        EverytimeCompactTimetableGrid(modifier = Modifier.width(359.dp))
    }
}
