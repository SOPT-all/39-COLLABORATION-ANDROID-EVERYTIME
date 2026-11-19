package com.cookandroid.everytime.presentation.review.component.model

import com.cookandroid.everytime.data.dto.review.ReviewListResponse

data class ReviewUiModel(
    val lectureName: String = "",
    val averageScore: Int = 0,
    val keywords: List<ReviewKeywordUiModel> = emptyList(),
    val totalReviewCount: Long = 0L,
    val reviews: List<ReviewItemUiModel> = emptyList(),
) {
    companion object {
        fun from(response: ReviewListResponse): ReviewUiModel = ReviewUiModel(
            lectureName = response.lecture.courseName,
            averageScore = response.lecture.averageGradePoint,
            keywords = response.keywords.sortedBy { it.keywordId }.map { keyword ->
                ReviewKeywordUiModel(
                    id = keyword.keywordId,
                    keyword = keyword.name,
                    count = keyword.count,
                )
            },
            totalReviewCount = response.totalReviewCount,
            reviews = response.reviews.content.distinctBy { it.reviewId }.map { review ->
                ReviewItemUiModel(
                    id = review.reviewId,
                    score = review.gradePoint,
                    semesterLabel = "${review.year.toString().takeLast(2)}년 ${review.semester} 수강자",
                    content = review.content,
                )
            },
        )
    }
}
