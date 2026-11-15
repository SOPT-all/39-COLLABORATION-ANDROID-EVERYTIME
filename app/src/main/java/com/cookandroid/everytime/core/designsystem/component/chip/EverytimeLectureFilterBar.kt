package com.cookandroid.everytime.core.designsystem.component.chip

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
    majorValue: String,
    searchValue: String,
    sortValue: String,
    timeValue: String,
    isMajorActive: Boolean,
    isSearchActive: Boolean,
    isSortActive: Boolean,
    isTimeActive: Boolean,
    onMajorClick: () -> Unit,
    onSearchClick: () -> Unit,
    onSortClick: () -> Unit,
    onTimeClick: () -> Unit,
    onMajorClearClick: () -> Unit,
    onSearchClearClick: () -> Unit,
    onSortClearClick: () -> Unit,
    onTimeClearClick: () -> Unit,
    modifier: Modifier = Modifier
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
            value = majorValue,
            isActive = isMajorActive,
            onClick = onMajorClick,
            onClearClick = onMajorClearClick
        )
        EverytimeFilterChip(
            label = "검색어:",
            value = searchValue,
            isActive = isSearchActive,
            onClick = onSearchClick,
            onClearClick = onSearchClearClick
        )
        EverytimeFilterChip(
            label = "정렬:",
            value = sortValue,
            isActive = isSortActive,
            onClick = onSortClick,
            onClearClick = onSortClearClick
        )
        EverytimeFilterChip(
            label = "시간:",
            value = timeValue,
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
            modifier = Modifier.padding(vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(space = 16.dp)
        ) {
            EverytimeLectureFilterBar(
                majorValue = "교양필수",
                searchValue = "없음",
                sortValue = "기본순",
                timeValue = "전체",
                isMajorActive = true,
                isSearchActive = false,
                isSortActive = false,
                isTimeActive = false,
                onMajorClick = {},
                onSearchClick = {},
                onSortClick = {},
                onTimeClick = {},
                onMajorClearClick = {},
                onSearchClearClick = {},
                onSortClearClick = {},
                onTimeClearClick = {}
            )
            EverytimeLectureFilterBar(
                majorValue = "전체",
                searchValue = "창의적 사고와 문제 해결",
                sortValue = "강의평 많은 순",
                timeValue = "월요일 오전 9시~12시",
                isMajorActive = false,
                isSearchActive = true,
                isSortActive = true,
                isTimeActive = true,
                onMajorClick = {},
                onSearchClick = {},
                onSortClick = {},
                onTimeClick = {},
                onMajorClearClick = {},
                onSearchClearClick = {},
                onSortClearClick = {},
                onTimeClearClick = {}
            )
        }
    }
}
