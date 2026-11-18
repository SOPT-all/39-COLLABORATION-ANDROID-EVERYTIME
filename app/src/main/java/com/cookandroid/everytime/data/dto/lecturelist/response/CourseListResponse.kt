package com.cookandroid.everytime.data.dto.lecturelist.response

import kotlinx.serialization.Serializable

@Serializable
data class CourseListResponse(
    val courses: List<CourseResponse>,
    val page: Int,
    val size: Int,
    val totalElements: Long,
    val totalPages: Int,
    val hasNext: Boolean,
)

@Serializable
data class CourseResponse(
    val courseId: Long,
    val courseCode: String,
    val courseName: String,
    val semester: String,
    val credit: Int,
    val departmentId: Long,
    val departmentName: String,
    val categoryId: Long,
    val categoryName: String,
    val recommendedGrade: Int? = null,
    val targetGrade: Int? = null,
    val targetText: String? = null,
    val sections: List<CourseSectionResponse>,
)

@Serializable
data class CourseSectionResponse(
    val sectionId: Long,
    val sectionNo: String,
    val professor: String,
    val capacity: Int? = null,
    val savedCount: Int,
    val averageGradePoint: Int,
    val times: List<CourseTimeResponse>,
    val timeText: String,
)

@Serializable
data class CourseTimeResponse(
    val dayOfWeek: String,
    val startTime: String,
    val endTime: String,
    val classroom: String,
)
