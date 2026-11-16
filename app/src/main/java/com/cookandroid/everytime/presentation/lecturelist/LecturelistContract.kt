package com.cookandroid.everytime.presentation.lecturelist

import com.cookandroid.everytime.presentation.lecturelist.component.model.LecturelistCourse
import com.cookandroid.everytime.presentation.lecturelist.component.model.LecturelistTimetableBlock

data class LecturelistUiState(
    val majorValue: String? = null,
    val hideTaken: Boolean = false,
    val hideRestricted: Boolean = false,
    val timetableBlocks: List<LecturelistTimetableBlock> = emptyList(),
    val courses: List<LecturelistCourse> = emptyList(),
    val expandedCourseIds: Set<Long> = emptySet(),
    val expandedSectionCodes: Set<String> = emptySet(),
)
