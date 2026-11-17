package com.cookandroid.everytime.presentation.lecturelist

import com.cookandroid.everytime.presentation.lecturelist.component.model.LecturelistCourse
import com.cookandroid.everytime.presentation.lecturelist.component.model.LecturelistTimetableBlock

data class LecturelistUiState(
    val category: LectureCategory? = null,
    val hideTaken: Boolean = false,
    val hideRestricted: Boolean = false,
    val timetableBlocks: List<LecturelistTimetableBlock> = emptyList(),
    val courses: List<LecturelistCourse> = emptyList(),
    val expandedCourseIds: Set<Long> = emptySet(),
    val expandedSectionCodes: Set<String> = emptySet(),
)

enum class LectureCategory(val categoryId: Long, val label: String) {
    MAJOR_REQUIRED(1L, "전공필수"),
    MAJOR_ELECTIVE(2L, "전공선택"),
    COMMON_LIBERAL_ARTS(3L, "공통교양"),
    CORE_LIBERAL_ARTS(4L, "핵심교양"),
    GENERAL_LIBERAL_ARTS(5L, "일반교양"),
}
