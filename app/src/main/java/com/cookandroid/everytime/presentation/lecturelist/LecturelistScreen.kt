package com.cookandroid.everytime.presentation.lecturelist

import android.R.attr.rowHeight
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.semantics.Role
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
import com.cookandroid.everytime.presentation.lecturelist.component.timetable.compactGridLayout
import com.cookandroid.everytime.presentation.lecturelist.component.topbar.EverytimeLecturelistTopbar

@Composable
fun LecturelistRoute(
    timeValue: String?,
    onNavigateToReview: (Long) -> Unit,
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
        onCategorySelected = viewModel::selectCategory,
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
    onNavigateToReview: (Long) -> Unit,
    onNavigateToTimetable: () -> Unit,
    onCategorySelected: (LectureCategory?) -> Unit,
    onTimeClearClick: () -> Unit,
    onHideTakenChange: (Boolean) -> Unit,
    onHideRestrictedChange: (Boolean) -> Unit,
    onCourseExpandedChange: (Long, Boolean) -> Unit,
    onSectionClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var showCategoryDialog by rememberSaveable { mutableStateOf(false) }
    val gridLayout = compactGridLayout

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
                .height(height = 218.dp)
                .border(width = 1.dp, color = EverytimeTheme.colors.neutral200, shape = RoundedCornerShape(12.dp))
                .clip(RoundedCornerShape(size = 12.dp))
                .verticalScroll(rememberScrollState()),
        ) {
            val dayWidth = (maxWidth - gridLayout.timeColumnWidth) / 5f
            EverytimeCompactTimetableGrid()

            uiState.timetableBlocks.forEach { block ->
                EverytimeTimetableBlock(
                    lectureName = block.lectureName,
                    classroom = block.classroom,
                    color = block.color,
                    modifier = Modifier
                        .offset(
                            x = gridLayout.timeColumnWidth + dayWidth * block.dayIndex.toFloat(),
                            y = gridLayout.headerHeight + gridLayout.rowHeight * block.startRow,
                        )
                        .size(width = dayWidth, height = gridLayout.rowHeight * block.rowSpan),
                )
            }
        }

        Spacer(modifier = Modifier.height(13.dp))

        EverytimeLectureFilterBar(
            onMajorClick = { showCategoryDialog = true },
            onTimeClick = onNavigateToTimetable,
            onMajorClearClick = { onCategorySelected(null) },
            onTimeClearClick = onTimeClearClick,
            majorValue = uiState.category?.label,
            timeValue = timeValue,
            isMajorActive = uiState.category != null,
            isTimeActive = timeValue != null,
            modifier = Modifier.padding(start = 16.dp)
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
                                    { onNavigateToReview(course.courseId) }                                )
                            }
                        }
                    }
                } else {
                    sections.singleOrNull()?.let { card ->
                        EverytimeLectureCard(
                            lecture = card,
                            expanded = card.courseCode in uiState.expandedSectionCodes,
                            onCardClick = { onSectionClick(card.courseCode) },
                            { onNavigateToReview(course.courseId) }                        )
                    }
                }
            }
        }
    }

    if (showCategoryDialog) {
        LectureCategoryDialog(
            selectedCategory = uiState.category,
            onCategorySelected = { category ->
                onCategorySelected(category)
                showCategoryDialog = false
            },
            onDismiss = { showCategoryDialog = false },
        )
    }
}

@Composable
private fun LectureCategoryDialog(
    selectedCategory: LectureCategory?,
    onCategorySelected: (LectureCategory?) -> Unit,
    onDismiss: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("전공/영역") },
        text = {
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .selectableGroup(),
            ) {
                val categories = listOf<LectureCategory?>(null) + LectureCategory.entries
                categories.forEach { category ->
                    val selected = category == selectedCategory
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(min = 48.dp)
                            .selectable(
                                selected = selected,
                                role = Role.RadioButton,
                                onClick = { onCategorySelected(category) },
                            ),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        RadioButton(selected = selected, onClick = null)
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(category?.label ?: "없음")
                    }
                }
            }
        },
        confirmButton = {
            TextButton(onClick = onDismiss) { Text("취소") }
        },
    )
}

@Preview(showBackground = true)
@Composable
private fun LecturelistScreenPreview() {
    EverytimeTheme {
        LecturelistScreen(
            uiState = dummyLecturelistUiState,
            timeValue = "월 9~10시",
            onNavigateToReview = {},
            onNavigateToTimetable = {},
            onCategorySelected = {},
            onTimeClearClick = {},
            onHideTakenChange = {},
            onHideRestrictedChange = {},
            onCourseExpandedChange = { _, _ -> },
            onSectionClick = {},
        )
    }
}
