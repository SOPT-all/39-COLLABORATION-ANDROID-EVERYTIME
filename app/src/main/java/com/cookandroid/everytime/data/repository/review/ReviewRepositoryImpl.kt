package com.cookandroid.everytime.data.repository.review

import com.cookandroid.everytime.data.dto.review.response.ReviewListResponse
import com.cookandroid.everytime.data.service.review.ReviewService
import jakarta.inject.Inject

class ReviewRepositoryImpl @Inject constructor(
    private val reviewService: ReviewService,
) : ReviewRepository {
    override suspend fun getReviews(
        sectionId: Long,
        sort: String?,
        page: Int?,
        size: Int?
    ): ReviewListResponse = reviewService.getReviews(
        sectionId = sectionId,
        sort = sort,
        page = page,
        size = size
    ).data

    override suspend fun getKeywordReviews(
        sectionId: Long,
        keywordId: Long,
        sort: String?,
        page: Int?,
        size: Int?
    ): ReviewListResponse = reviewService.getKeywordReviews(
        sectionId = sectionId,
        keywordId = keywordId,
        sort = sort,
        page = page,
        size = size
    ).data
}
