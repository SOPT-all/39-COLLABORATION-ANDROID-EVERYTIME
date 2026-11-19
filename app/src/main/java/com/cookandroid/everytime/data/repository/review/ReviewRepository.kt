package com.cookandroid.everytime.data.repository.review

import com.cookandroid.everytime.data.dto.review.ReviewListResponse

interface ReviewRepository {
    suspend fun getKeywordReviews(
        sectionId: Long,
        keywordId: Long,
        sort: String? = null,
        page: Int? = null,
        size: Int? = null,
    ): ReviewListResponse
}
