package com.cookandroid.everytime.presentation.review

import com.cookandroid.everytime.presentation.review.component.model.ReviewUiModel

data class ReviewUiState(
    val sectionId: Long? = null,
    val review: ReviewUiModel = ReviewUiModel(),
    val selectedKeywordId: Long? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
)
