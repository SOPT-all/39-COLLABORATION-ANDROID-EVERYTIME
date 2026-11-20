package com.cookandroid.everytime.presentation.lecturelist

import com.cookandroid.everytime.presentation.lecturelist.model.LecturelistCourse
import com.cookandroid.everytime.presentation.lecturelist.model.LecturelistTimetableBlockUiModel
import com.cookandroid.everytime.presentation.lecturelist.component.timetable.EverytimeTimetableBlockColor

data class LecturelistUiState(
    val departmentId: Long? = null,
    val times: List<String> = emptyList(),
    val category: LectureCategory? = null,
    val hideTaken: Boolean = false,
    val hideRestricted: Boolean = false,
    val timetableBlocks: List<LecturelistTimetableBlockUiModel> = listOf(
        LecturelistTimetableBlockUiModel(
            lectureName = "교내인턴",
            color = EverytimeTimetableBlockColor.CORAL,
            dayIndex = 0,
            startRow = 1f,
            rowSpan = 5f
        ),
        LecturelistTimetableBlockUiModel(
            lectureName = "사진디자인",
            color = EverytimeTimetableBlockColor.GREEN,
            dayIndex = 2,
            startRow = 3f,
            rowSpan = 2f + 40f / 60f,
            classroom = "조형예술관-306"
        ),
        LecturelistTimetableBlockUiModel(
            lectureName = "인적성검사와 취업실전",
            color = EverytimeTimetableBlockColor.ORANGE,
            dayIndex = 4,
            startRow = 3f,
            rowSpan = 2f + 40f / 60f
        )
    ),
    val courses: List<LecturelistCourse> = emptyList(),
    val expandedCourseIds: Set<Long> = emptySet(),
    val expandedSectionCodes: Set<String> = emptySet()
)

enum class LectureCategory(val categoryId: Long, val label: String) {
    MAJOR_REQUIRED(1L, "전공필수"),
    MAJOR_ELECTIVE(2L, "전공선택"),
    COMMON_LIBERAL_ARTS(3L, "공통교양"),
    CORE_LIBERAL_ARTS(4L, "핵심교양"),
    GENERAL_LIBERAL_ARTS(5L, "일반교양")
}
