package com.cookandroid.everytime.data.service.review

import com.cookandroid.everytime.data.dto.base.BaseResponse
import com.cookandroid.everytime.data.dto.review.ReviewListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ReviewService {
    @GET("api/v1/sections/{sectionId}/reviews")
    suspend fun getReviews(
        @Path("sectionId") sectionId: Long,
        @Query("sort") sort: String? = null,
        @Query("page") page: Int? = null,
        @Query("size") size: Int? = null,
    ): BaseResponse<ReviewListResponse>

    @GET("api/v1/sections/{sectionId}/reviews/keywords/{keywordId}")
    suspend fun getKeywordReviews(
        @Path("sectionId") sectionId: Long,
        @Path("keywordId") keywordId: Long,
        @Query("sort") sort: String? = null,
        @Query("page") page: Int? = null,
        @Query("size") size: Int? = null,
    ): BaseResponse<ReviewListResponse>
}
