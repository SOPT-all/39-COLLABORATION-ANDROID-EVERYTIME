package com.cookandroid.everytime.presentation.lecturelist

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.cookandroid.everytime.core.designsystem.theme.EverytimeTheme
import com.cookandroid.everytime.presentation.lecturelist.component.accordion.EverytimeAccordion
import com.cookandroid.everytime.presentation.lecturelist.component.card.EverytimeLectureCard
import com.cookandroid.everytime.presentation.lecturelist.component.checkboxfilterbar.EverytimeCheckboxFilter
import com.cookandroid.everytime.presentation.lecturelist.component.lecturefilterbar.EverytimeLectureFilterBar
import com.cookandroid.everytime.presentation.lecturelist.component.timetable.EverytimeCompactTimetableGrid
import com.cookandroid.everytime.presentation.lecturelist.component.timetable.EverytimeTimetableBlock
import com.cookandroid.everytime.presentation.lecturelist.component.topbar.EverytimeLecturelistTopbar

@Composable
fun LecturelistRoute(
    timeValue: String?,
    onNavigateToReview: () -> Unit,
    onNavigateToTimetable: () -> Unit,
    onTimeClearClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: LecturelistViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()

    LecturelistScreen(
        uiState = uiState,
        timeValue = timeValue,
        onNavigateToReview = onNavigateToReview,
        onNavigateToTimetable = onNavigateToTimetable,
        onMajorClearClick = viewModel::clearMajorFilter,
        onTimeClearClick = onTimeClearClick,
        onHideTakenChange = viewModel::setHideTaken,
        onHideRestrictedChange = viewModel::setHideRestricted,
        onCourseExpandedChange = viewModel::setCourseExpanded,
        onSectionClick = viewModel::toggleSection,
        modifier = modifier
    )
}

@Composable
fun LecturelistScreen(
    uiState: LecturelistUiState,
    timeValue: String?,
    onNavigateToReview: () -> Unit,
    onNavigateToTimetable: () -> Unit,
    onMajorClearClick: () -> Unit,
    onTimeClearClick: () -> Unit,
    onHideTakenChange: (Boolean) -> Unit,
    onHideRestrictedChange: (Boolean) -> Unit,
    onCourseExpandedChange: (Long, Boolean) -> Unit,
    onSectionClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val timeColumnWidth = 19.dp
    val headerHeight = 18.dp
    val rowHeight = 43.dp

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = EverytimeTheme.colors.white)
    ) {
        EverytimeLecturelistTopbar()

        Spacer(modifier = Modifier.height(6.dp))

        BoxWithConstraints(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
                .height(218.dp)
                .border(1.dp, color = EverytimeTheme.colors.neutral200, shape = RoundedCornerShape(12.dp))
                .clip(RoundedCornerShape(12.dp))
                .verticalScroll(rememberScrollState()),
        ) {
            val dayWidth = (maxWidth - timeColumnWidth) / 5f
            EverytimeCompactTimetableGrid()

            uiState.timetableBlocks.forEach { block ->
                EverytimeTimetableBlock(
                    lectureName = block.lectureName,
                    classroom = block.classroom,
                    color = block.color,
                    modifier = Modifier
                        .offset(
                            x = timeColumnWidth + dayWidth * block.dayIndex.toFloat(),
                            y = headerHeight + rowHeight * block.startRow,
                        )
                        .size(width = dayWidth, height = rowHeight * block.rowSpan),
                )
            }
        }

        Spacer(modifier = Modifier.height(13.dp))

        EverytimeLectureFilterBar(
            onMajorClick = {}, //여기선 시스템 모달을 열어 선택하게 할 예정
            onTimeClick = onNavigateToTimetable,
            onMajorClearClick = onMajorClearClick,
            onTimeClearClick = onTimeClearClick,
            majorValue = uiState.majorValue,
            timeValue = timeValue,
            isMajorActive = uiState.majorValue != null,
            isTimeActive = timeValue != null,
        )

        Spacer(modifier = Modifier.height(12.dp))

        EverytimeCheckboxFilter(
            hideCompleted = uiState.hideTaken,
            hideRestricted = uiState.hideRestricted,
            onHideCompletedChange = onHideTakenChange,
            onHideRestrictedChange = onHideRestrictedChange,
            modifier = Modifier.padding(start = 20.dp)
        )

        Spacer(modifier = Modifier.height(12.dp))

        LazyColumn(modifier = Modifier.fillMaxWidth().weight(1f)) {
            items(uiState.courses, key = { it.courseId }) { course ->
                val sections = course.sections

                if (sections.size > 1) {
                    EverytimeAccordion(
                        title = course.courseName,
                        sectionCount = sections.size,
                        expanded = course.courseId in uiState.expandedCourseIds,
                        onExpandedChange = { onCourseExpandedChange(course.courseId, it) },
                    ) {
                        sections.forEach { card ->
                            key(card.courseCode) {
                                EverytimeLectureCard(
                                    lecture = card,
                                    expanded = card.courseCode in uiState.expandedSectionCodes,
                                    onCardClick = { onSectionClick(card.courseCode) },
                                    onReviewClick = onNavigateToReview,
                                )
                            }
                        }
                    }
                } else {
                    sections.singleOrNull()?.let { card ->
                        EverytimeLectureCard(
                            lecture = card,
                            expanded = card.courseCode in uiState.expandedSectionCodes,
                            onCardClick = { onSectionClick(card.courseCode) },
                            onReviewClick = onNavigateToReview,
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun LecturelistScreenPreview() {
    EverytimeTheme {
        LecturelistScreen(
            uiState = dummyLecturelistUiState.copy(majorValue = "교양필수"),
            timeValue = "월 9~10시",
            onNavigateToReview = {},
            onNavigateToTimetable = {},
            onMajorClearClick = {},
            onTimeClearClick = {},
            onHideTakenChange = {},
            onHideRestrictedChange = {},
            onCourseExpandedChange = { _, _ -> },
            onSectionClick = {},
        )
    }
}
