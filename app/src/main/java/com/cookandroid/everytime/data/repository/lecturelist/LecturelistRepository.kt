package com.cookandroid.everytime.data.repository.lecturelist

import com.cookandroid.everytime.data.dto.lecturelist.response.CourseListResponse

interface LecturelistRepository {
    suspend fun getCourses(
        categoryId: Long? = null,
        departmentId: Long? = null,
        hideTaken: Boolean? = null,
        hideRestricted: Boolean? = null,
        times: List<String>? = null,
        page: Int? = null,
        size: Int? = null,
    ): CourseListResponse
}
