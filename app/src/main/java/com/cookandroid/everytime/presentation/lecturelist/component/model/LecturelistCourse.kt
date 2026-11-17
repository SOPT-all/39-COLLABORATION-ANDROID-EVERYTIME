package com.cookandroid.everytime.presentation.lecturelist.component.model

data class LecturelistCourse(
    val courseId: Long,
    val courseName: String,
    val categoryId: Long,
    val sections: List<LectureCardUiModel>,
)
