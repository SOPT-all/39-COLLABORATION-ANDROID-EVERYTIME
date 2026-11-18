package com.cookandroid.everytime.presentation.review

import com.cookandroid.everytime.presentation.review.component.model.ReviewItemUiModel
import com.cookandroid.everytime.presentation.review.component.model.ReviewKeywordUiModel

data class ReviewUiState(
    val sectionId: Long? = null,
    val lectureName: String = "",
    val averageScore: Int = 0,
    val selectedKeywordId: Long? = null,
    val keywords: List<ReviewKeywordUiModel> = emptyList(),
    val totalReviewCount: Long = 0L,
    val reviews: List<ReviewItemUiModel> = emptyList(),
    val page: Int = 0,
    val hasNext: Boolean = false,
    val isLoading: Boolean = false,
    val isLoadingMore: Boolean = false,
    val errorMessage: String? = null,
    val loadMoreErrorMessage: String? = null,
)
