package com.cookandroid.everytime.presentation.lecturelist.model

import com.cookandroid.everytime.data.dto.lecturelist.response.CourseResponse
import com.cookandroid.everytime.presentation.lecturelist.component.card.EverytimeDotState

data class LecturelistCourse(
    val courseId: Long,
    val departmentId: Long,
    val courseName: String,
    val categoryId: Long,
    val sections: List<LectureCardUiModel>
)

fun CourseResponse.toModel(): LecturelistCourse =
    LecturelistCourse(
        courseId = courseId,
        departmentId = departmentId,
        courseName = courseName,
        categoryId = categoryId,
        sections = sections.map { section ->
            LectureCardUiModel(
                courseId = courseId,
                sectionId = section.sectionId,
                departmentId = departmentId,
                courseName = courseName,
                professor = section.professor,
                courseTimeAndRoom = section.timeText,
                categoryName = categoryName,
                creditLabel = "${credit}학점",
                courseCode = "$courseCode-${section.sectionNo}",
                savedCountLabel = section.savedCount.toString(),
                capacityLabel = section.capacity?.toString() ?: "N",
                recommendedGradeLabel = recommendedGrade?.let { "${it}학년" }.orEmpty(),
                target = targetText.orEmpty(),
                score = section.averageGradePoint.toFloat(),
                dotState = EverytimeDotState.GREEN
            )
        },
    )
