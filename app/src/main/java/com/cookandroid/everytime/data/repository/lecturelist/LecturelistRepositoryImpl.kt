package com.cookandroid.everytime.data.repository.lecturelist

import com.cookandroid.everytime.data.dto.lecturelist.response.CourseListResponse
import com.cookandroid.everytime.data.service.lecturelist.LecturelistService
import jakarta.inject.Inject

class LecturelistRepositoryImpl @Inject constructor(
    private val lecturelistService: LecturelistService,
) : LecturelistRepository {
    override suspend fun getCourses(
        categoryId: Long?,
        departmentId: Long?,
        semester: String?,
        hideTaken: Boolean?,
        hideRestricted: Boolean?,
        times: List<String>?,
        sort: String?,
        page: Int?,
        size: Int?
    ): CourseListResponse =
        lecturelistService.getCourses(
            categoryId = categoryId,
            departmentId = departmentId,
            semester = semester,
            hideTaken = hideTaken,
            hideRestricted = hideRestricted,
            times = times,
            sort = sort,
            page = page,
            size = size,
        ).data
}
