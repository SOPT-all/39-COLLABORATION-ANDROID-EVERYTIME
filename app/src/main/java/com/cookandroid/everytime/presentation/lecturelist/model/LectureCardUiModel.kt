package com.cookandroid.everytime.presentation.lecturelist.model

import com.cookandroid.everytime.presentation.lecturelist.component.card.EverytimeDotState

data class LectureCardUiModel(
    val courseId: Long,
    val sectionId: Long,
    val departmentId: Long,
    val courseName: String,
    val professor: String,
    val courseTimeAndRoom: String,
    val categoryName: String,
    val creditLabel: String,
    val courseCode: String,
    val savedCountLabel: String,
    val capacityLabel: String,
    val recommendedGradeLabel: String,
    val target: String,
    val score: Float,
    val dotState: EverytimeDotState
)
