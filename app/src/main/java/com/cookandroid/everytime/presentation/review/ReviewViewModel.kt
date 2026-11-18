package com.cookandroid.everytime.presentation.review

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cookandroid.everytime.data.dto.review.ReviewListResponse
import com.cookandroid.everytime.data.repository.review.ReviewRepository
import com.cookandroid.everytime.presentation.review.component.model.ReviewItemUiModel
import com.cookandroid.everytime.presentation.review.component.model.ReviewKeywordUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Job
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class ReviewViewModel @Inject constructor(
    private val reviewRepository: ReviewRepository,
) : ViewModel() {
    private val _uiState = MutableStateFlow(ReviewUiState())
    val uiState = _uiState.asStateFlow()
    private var reviewJob: Job? = null

    fun loadReviews(sectionId: Long?) {
        val currentState = _uiState.value
        if (currentState.sectionId == sectionId && currentState.errorMessage == null) return

        reviewJob?.cancel()
        // 목록 API 통합 전에는 실제 분반 ID가 없으므로 요청하지 않는다.
        if (sectionId == null) {
            _uiState.value = ReviewUiState()
            return
        }
        _uiState.value = ReviewUiState(sectionId = sectionId, isLoading = true)
        requestReviews(sectionId = sectionId, page = 1)
    }

    fun loadNextPage() {
        val state = _uiState.value
        val sectionId = state.sectionId ?: return
        if (state.isLoading || state.isLoadingMore || state.page == 0 ||
            !state.hasNext || state.loadMoreErrorMessage != null
        ) return

        _uiState.update { it.copy(isLoadingMore = true) }
        requestReviews(sectionId = sectionId, page = state.page + 1)
    }

    fun retryNextPage() {
        val state = _uiState.value
        if (state.isLoading || state.isLoadingMore || state.loadMoreErrorMessage == null) return

        _uiState.update { it.copy(loadMoreErrorMessage = null) }
        loadNextPage()
    }

    private fun requestReviews(sectionId: Long, page: Int) {
        reviewJob = viewModelScope.launch {
            try {
                val response = reviewRepository.getReviews(sectionId = sectionId, page = page)
                ensureActive()
                if (_uiState.value.sectionId != sectionId) return@launch

                val nextState = response.toUiState()
                _uiState.update { current ->
                    nextState.copy(
                        reviews = if (page == 1) {
                            nextState.reviews
                        } else {
                            (current.reviews + nextState.reviews).distinctBy { it.id }
                        },
                    )
                }
            } catch (exception: CancellationException) {
                throw exception
            } catch (exception: Exception) {
                ensureActive()
                if (_uiState.value.sectionId != sectionId) return@launch

                _uiState.update {
                    if (page == 1) {
                        it.copy(
                            isLoading = false,
                            errorMessage = exception.message ?: "강의평을 불러오지 못했습니다.",
                        )
                    } else {
                        it.copy(
                            isLoadingMore = false,
                            loadMoreErrorMessage = exception.message ?: "다음 강의평을 불러오지 못했습니다.",
                        )
                    }
                }
            }
        }
    }
}

private fun ReviewListResponse.toUiState(): ReviewUiState = ReviewUiState(
    sectionId = lecture.sectionId,
    lectureName = lecture.courseName,
    averageScore = lecture.averageGradePoint,
    keywords = keywords.sortedBy { it.keywordId }.map { keyword ->
        ReviewKeywordUiModel(
            id = keyword.keywordId,
            keyword = keyword.name,
            count = keyword.count,
        )
    },
    totalReviewCount = totalReviewCount,
    reviews = reviews.content.distinctBy { it.reviewId }.map { review ->
        ReviewItemUiModel(
            id = review.reviewId,
            score = review.gradePoint,
            semesterLabel = "${review.year.toString().takeLast(2)}년 ${review.semester} 수강자",
            content = review.content,
        )
    },
    page = reviews.page,
    hasNext = reviews.hasNext,
)
