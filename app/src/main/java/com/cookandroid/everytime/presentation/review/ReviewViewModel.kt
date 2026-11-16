package com.cookandroid.everytime.presentation.review

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class ReviewViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(ReviewUiState())
    val uiState = _uiState.asStateFlow()

    private val _sideEffect = Channel<ReviewSideEffect>(Channel.BUFFERED)
    val sideEffect = _sideEffect.receiveAsFlow()

    fun onKeywordClick(keyword: String?) {
        _uiState.update { it.copy(selectedKeyword = keyword) }
    }

    fun onBackClick() {
        viewModelScope.launch {
            _sideEffect.send(ReviewSideEffect.NavigateBack)
        }
    }
}
