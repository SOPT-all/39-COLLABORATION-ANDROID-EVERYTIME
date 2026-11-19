package com.cookandroid.everytime.presentation.review

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cookandroid.everytime.data.repository.review.ReviewRepository
import com.cookandroid.everytime.presentation.review.component.model.ReviewUiModel
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

    fun loadReviews(sectionId: Long) {
        val currentState = _uiState.value
        if (currentState.sectionId == sectionId && currentState.errorMessage == null) return

        reviewJob?.cancel()
        _uiState.value = ReviewUiState(sectionId = sectionId)
        requestReviews(sectionId = sectionId)
    }

    fun onKeywordClick(keywordId: Long?) {
        val currentState = _uiState.value
        val sectionId = currentState.sectionId ?: return
        val selectedKeywordId = if (
            currentState.selectedKeywordId == keywordId && currentState.errorMessage == null
        ) null else keywordId
        if (currentState.selectedKeywordId == selectedKeywordId && currentState.errorMessage == null) return

        reviewJob?.cancel()
        _uiState.value = currentState.copy(
            selectedKeywordId = selectedKeywordId,
            review = currentState.review.copy(reviews = emptyList()),
            errorMessage = null,
        )
        requestReviews(sectionId = sectionId, keywordId = selectedKeywordId)
    }

    private fun requestReviews(sectionId: Long, keywordId: Long? = null) {
        reviewJob = viewModelScope.launch {
            try {
                val response = if (keywordId == null) {
                    reviewRepository.getReviews(sectionId = sectionId)
                } else {
                    reviewRepository.getKeywordReviews(sectionId = sectionId, keywordId = keywordId)
                }
                ensureActive()
                if (_uiState.value.sectionId != sectionId || _uiState.value.selectedKeywordId != keywordId) return@launch

                _uiState.value = ReviewUiState(
                    sectionId = response.lecture.sectionId,
                    review = ReviewUiModel.from(response),
                    selectedKeywordId = keywordId,
                )
            } catch (exception: CancellationException) {
                throw exception
            } catch (exception: Exception) {
                ensureActive()
                if (_uiState.value.sectionId != sectionId || _uiState.value.selectedKeywordId != keywordId) return@launch

                _uiState.update {
                    it.copy(
                        errorMessage = exception.message ?: "강의평을 불러오지 못했습니다.",
                    )
                }
            }
        }
    }
}
