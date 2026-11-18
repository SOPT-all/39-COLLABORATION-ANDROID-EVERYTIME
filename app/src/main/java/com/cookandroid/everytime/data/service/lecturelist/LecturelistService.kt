package com.cookandroid.everytime.data.service.lecturelist

import com.cookandroid.everytime.data.dto.base.BaseResponse
import com.cookandroid.everytime.data.dto.lecturelist.response.CourseListResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface LecturelistService {
    @Headers("X-Member-Id: 1")
    @GET("api/v1/courses")
    suspend fun getCourses(
        @Query("categoryId") categoryId: Long? = null,
        @Query("departmentId") departmentId: Long? = null,
        @Query("semester") semester: String? = null,
        @Query("hideTaken") hideTaken: Boolean? = null,
        @Query("hideRestricted") hideRestricted: Boolean? = null,
        @Query("times") times: List<String>? = null,
        @Query("sort") sort: String? = null,
        @Query("page") page: Int? = null,
        @Query("size") size: Int? = null
    ): BaseResponse<CourseListResponse>
}
