package com.cookandroid.everytime.data.service.review

import com.cookandroid.everytime.data.dto.base.BaseResponse
import com.cookandroid.everytime.data.dto.review.ReviewListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ReviewService {
    @GET("api/v1/sections/{sectionId}/reviews/keywords/{keywordId}")
    suspend fun getReviewsByKeyword(
        @Path("sectionId") sectionId: Long,
        @Path("keywordId") keywordId: Long,
        @Query("sort") sort: String = "LATEST",
        @Query("page") page: Int = 1,
        @Query("size") size: Int = 10,
    ): BaseResponse<ReviewListResponse>
}
