package com.cookandroid.everytime.data.repository.review

import com.cookandroid.everytime.data.dto.review.ReviewListResponse

interface ReviewRepository {
    suspend fun getReviews(
        sectionId: Long,
        sort: String = "LATEST",
        page: Int = 1,
        size: Int = 10,
    ): ReviewListResponse
}
