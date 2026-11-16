package com.cookandroid.everytime.presentation.lecturelist.component.lecturefilterbar

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cookandroid.everytime.core.designsystem.theme.EverytimeTheme

@Composable
fun EverytimeLectureFilterBar(
    onTimeClick: () -> Unit,
    onMajorClearClick: () -> Unit,
    onTimeClearClick: () -> Unit,
    modifier: Modifier = Modifier,
    majorValue: String? = null,
    timeValue: String? = null,
    isMajorActive: Boolean = false,
    isTimeActive: Boolean = false,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .horizontalScroll(
                state = rememberScrollState(),
                overscrollEffect = null
            )
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(space = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        EverytimeFilterChip(
            label = "전공/영역:",
            value = majorValue ?: "없음",
            isActive = isMajorActive,
            onClick = {},
            onClearClick = onMajorClearClick
        )

        EverytimeFilterChip(
            label = "검색어:",
            value = "없음",
            isActive = false,
            onClick = {},
            onClearClick = {}
        )

        EverytimeFilterChip(
            label = "정렬:",
            value = "없음",
            isActive = false,
            onClick = {},
            onClearClick = {}
        )

        EverytimeFilterChip(
            label = "시간:",
            value = timeValue ?: "전체",
            isActive = isTimeActive,
            onClick = onTimeClick,
            onClearClick = onTimeClearClick
        )
    }
}

@Preview(showBackground = true, widthDp = 375)
@Composable
private fun EverytimeLectureFilterBarPreview() {
    EverytimeTheme {
        Column(
            modifier = Modifier
                .background(color = EverytimeTheme.colors.white)
                .padding(vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(space = 16.dp)
        ) {
            EverytimeLectureFilterBar(
                onTimeClick = {},
                onMajorClearClick = {},
                onTimeClearClick = {},
                isMajorActive = true
            )
            EverytimeLectureFilterBar(
                onTimeClick = {},
                onMajorClearClick = {},
                onTimeClearClick = {},
                isMajorActive = false,
                isTimeActive = true
            )
        }
    }
}
