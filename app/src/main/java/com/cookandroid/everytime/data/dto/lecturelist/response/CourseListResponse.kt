package com.cookandroid.everytime.data.dto.lecturelist.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CourseListResponse(
    @SerialName("courses")
    val courses: List<CourseResponse>,
    @SerialName("page")
    val page: Int,
    @SerialName("size")
    val size: Int,
    @SerialName("totalElements")
    val totalElements: Long,
    @SerialName("totalPages")
    val totalPages: Int,
    @SerialName("hasNext")
    val hasNext: Boolean
)

@Serializable
data class CourseResponse(
    @SerialName("courseId")
    val courseId: Long,
    @SerialName("courseCode")
    val courseCode: String,
    @SerialName("courseName")
    val courseName: String,
    @SerialName("semester")
    val semester: String,
    @SerialName("credit")
    val credit: Int,
    @SerialName("departmentId")
    val departmentId: Long,
    @SerialName("departmentName")
    val departmentName: String,
    @SerialName("categoryId")
    val categoryId: Long,
    @SerialName("categoryName")
    val categoryName: String,
    @SerialName("recommendedGrade")
    val recommendedGrade: Int? = null,
    @SerialName("targetGrade")
    val targetGrade: Int? = null,
    @SerialName("targetText")
    val targetText: String? = null,
    @SerialName("sections")
    val sections: List<CourseSectionResponse>
)

@Serializable
data class CourseSectionResponse(
    @SerialName("sectionId")
    val sectionId: Long,
    @SerialName("sectionNo")
    val sectionNo: String,
    @SerialName("professor")
    val professor: String,
    @SerialName("capacity")
    val capacity: Int? = null,
    @SerialName("savedCount")
    val savedCount: Int,
    @SerialName("averageGradePoint")
    val averageGradePoint: Int,
    @SerialName("times")
    val times: List<CourseTimeResponse>,
    @SerialName("timeText")
    val timeText: String,
)

@Serializable
data class CourseTimeResponse(
    @SerialName("dayOfWeek")
    val dayOfWeek: String,
    @SerialName("startTime")
    val startTime: String,
    @SerialName("endTime")
    val endTime: String,
    @SerialName("classroom")
    val classroom: String? = null
)
