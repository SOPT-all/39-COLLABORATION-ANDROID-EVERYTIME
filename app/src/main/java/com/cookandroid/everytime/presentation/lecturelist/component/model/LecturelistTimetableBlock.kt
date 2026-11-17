package com.cookandroid.everytime.presentation.lecturelist.component.model

import com.cookandroid.everytime.presentation.lecturelist.component.timetable.EverytimeTimetableBlockColor

data class LecturelistTimetableBlock(
    val lectureName: String,
    val color: EverytimeTimetableBlockColor,
    val dayIndex: Int,
    val startRow: Float,
    val rowSpan: Float,
    val classroom: String? = null,
)
