package com.cookandroid.everytime.presentation.timetable.component.timetable

import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cookandroid.everytime.core.designsystem.theme.EverytimeTheme

private val expandedGridLayout =
    _root_ide_package_.com.cookandroid.everytime.core.designsystem.component.timetable.TimetableGridLayout(
        days = _root_ide_package_.com.cookandroid.everytime.core.designsystem.component.timetable.timetableWeekDays + listOf(
            "토",
            "일"
        ),
        hours = (8..23).map { hour -> hour.toString() },
        timeColumnWidth = 44.dp,
        headerHeight = 36.dp,
        rowHeight = 36.dp,
        cornerRadius = 11.dp
    )

@Composable
fun EverytimeExpandedTimetableGrid(
    modifier: Modifier = Modifier
) {
    _root_ide_package_.com.cookandroid.everytime.core.designsystem.component.timetable.TimetableGrid(
        layout = expandedGridLayout,
        labelColor = EverytimeTheme.colors.neutral800,
        labelStyle = EverytimeTheme.typography.caption1,
        hourAlignment = Alignment.Center,
        modifier = modifier
    )
}

@Composable
@Preview(showBackground = true)
private fun EverytimeExpandedTimetableGridPreview() {
    EverytimeTheme {
        EverytimeExpandedTimetableGrid(modifier = Modifier.width(351.dp))
    }
}
