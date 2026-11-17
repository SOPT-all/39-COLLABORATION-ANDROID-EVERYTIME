package com.cookandroid.everytime.presentation.review

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

@HiltViewModel
class ReviewViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(ReviewUiState())
    val uiState = _uiState.asStateFlow()

    fun onKeywordClick(keyword: String?) {
        _uiState.update { it.copy(selectedKeyword = keyword) }
    }
}
