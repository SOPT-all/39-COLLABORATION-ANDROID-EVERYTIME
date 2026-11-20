package com.cookandroid.everytime.presentation.review

import com.cookandroid.everytime.presentation.review.model.ReviewUiModel

data class ReviewUiState(
    val sectionId: Long? = null,
    val review: ReviewUiModel = ReviewUiModel(),
    val selectedKeywordId: Long? = null,
)
