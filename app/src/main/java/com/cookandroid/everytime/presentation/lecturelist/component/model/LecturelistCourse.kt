package com.cookandroid.everytime.presentation.lecturelist.component.model

import com.cookandroid.everytime.data.dto.lecturelist.response.CourseResponse
import com.cookandroid.everytime.presentation.lecturelist.component.card.EverytimeDotState

data class LecturelistCourse(
    val courseId: Long,
    val courseName: String,
    val categoryId: Long,
    val sections: List<LectureCardUiModel>,
)

private fun CourseResponse.toModel(): LecturelistCourse =
    LecturelistCourse(
        courseId = courseId,
        courseName = courseName,
        categoryId = categoryId,
        sections = sections.map { section ->
            LectureCardUiModel(
                courseId = courseId,
                courseName = courseName,
                professor = section.professor,
                courseTimeAndRoom = section.timeText,
                categoryName = categoryName,
                creditLabel = "${credit}학점",
                courseCode = "$courseCode-${section.sectionNo}",
                savedCountLabel = section.savedCount.toString(),
                capacityLabel = section.capacity.toString(),
                recommendedGradeLabel = recommendedGrade?.let { "${it}학년" }.orEmpty(),
                target = targetText.orEmpty(),
                score = section.averageGradePoint.toFloat(),
                dotState = EverytimeDotState.GREEN,
            )
        },
    )
