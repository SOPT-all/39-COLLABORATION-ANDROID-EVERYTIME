package com.cookandroid.everytime.presentation.review

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cookandroid.everytime.data.repository.review.ReviewRepository
import com.cookandroid.everytime.presentation.review.model.toModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Job
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class ReviewViewModel @Inject constructor(
    private val reviewRepository: ReviewRepository,
) : ViewModel() {
    private val _uiState = MutableStateFlow(ReviewUiState())
    val uiState = _uiState.asStateFlow()

    private var reviewJob: Job? = null
    private var lastLoadSucceeded = false

    fun loadReviews(sectionId: Long) {
        val currentState = _uiState.value
        val isLoading = reviewJob?.isActive == true
        if (currentState.sectionId == sectionId && (isLoading || lastLoadSucceeded)) return

        reviewJob?.cancel()
        _uiState.value = ReviewUiState(sectionId = sectionId)
        requestReviews(sectionId = sectionId)
    }

    fun onKeywordClick(keywordId: Long?) {
        val currentState = _uiState.value
        val sectionId = currentState.sectionId ?: return
        val isLoading = reviewJob?.isActive == true
        val selectedKeywordId = if (
            currentState.selectedKeywordId == keywordId && (isLoading || lastLoadSucceeded)
        ) null else keywordId
        if (currentState.selectedKeywordId == selectedKeywordId && (isLoading || lastLoadSucceeded)) return

        reviewJob?.cancel()
        _uiState.value = currentState.copy(
            selectedKeywordId = selectedKeywordId,
            review = currentState.review.copy(reviews = emptyList()),
        )
        requestReviews(sectionId = sectionId, keywordId = selectedKeywordId)
    }

    private fun requestReviews(sectionId: Long, keywordId: Long? = null) {
        lastLoadSucceeded = false
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
                    review = response.toModel(),
                    selectedKeywordId = keywordId,
                )
                lastLoadSucceeded = true
            } catch (exception: CancellationException) {
                throw exception
            } catch (_: Exception) {
                ensureActive()
                if (_uiState.value.sectionId != sectionId || _uiState.value.selectedKeywordId != keywordId) return@launch

                lastLoadSucceeded = false
            }
        }
    }
}
