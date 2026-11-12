package com.cookandroid.everytime.core.designsystem.component.timetable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cookandroid.everytime.core.designsystem.theme.EverytimeTheme

enum class EverytimeTimetableBlockColor {
    SALMON,
    LIME,
    ORANGE,
    GRAY,
    OLIVE
}

@Composable
fun EverytimeTimetableBlock(
    lectureName: String,
    color: EverytimeTimetableBlockColor,
    modifier: Modifier = Modifier,
    classroom: String? = null
) {
    val backgroundColor = when (color) {
        EverytimeTimetableBlockColor.SALMON -> EverytimeTheme.colors.timetableCoral
        EverytimeTimetableBlockColor.LIME -> EverytimeTheme.colors.timetableGreen
        EverytimeTimetableBlockColor.ORANGE -> EverytimeTheme.colors.timetableOrange
        EverytimeTimetableBlockColor.GRAY -> EverytimeTheme.colors.timetableGray
        EverytimeTimetableBlockColor.OLIVE -> EverytimeTheme.colors.timetableOlive
    }

    Column(
        modifier = modifier
            .clipToBounds()
            .background(backgroundColor)
            .padding(4.dp),
        verticalArrangement = Arrangement.spacedBy(1.dp)
    ) {
        Text(
            text = lectureName,
            color = EverytimeTheme.colors.white,
            style = EverytimeTheme.typography.body5
        )

        if (!classroom.isNullOrBlank()) {
            Text(
                text = classroom,
                color = EverytimeTheme.colors.white,
                style = EverytimeTheme.typography.caption5
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun EverytimeTimetableBlockPreview() {
    EverytimeTheme {

        Row(
            modifier = Modifier.padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            EverytimeTimetableBlockColor.entries.forEach { color ->
                EverytimeTimetableBlock(
                    lectureName = "수업명",
                    color = color,
                    modifier = Modifier.size(width = 68.dp, height = 88.dp),
                    classroom = "강의실"
                )
            }
        }
    }
}
