package com.cookandroid.everytime.presentation.lecturelist.model

import com.cookandroid.everytime.presentation.lecturelist.component.timetable.EverytimeTimetableBlockColor

data class LecturelistTimetableBlockUiModel(
    val lectureName: String,
    val color: EverytimeTimetableBlockColor,
    val dayIndex: Int,
    val startRow: Float,
    val rowSpan: Float,
    val classroom: String? = null
)
