package com.cookandroid.everytime.presentation.lecturelist.component.model

data class LecturelistCourse(
    val courseId: Long,
    val courseName: String,
    val sections: List<LectureCardUiModel>,
)
