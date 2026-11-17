package com.cookandroid.everytime.presentation.lecturelist

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

@HiltViewModel
class LecturelistViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(dummyLecturelistUiState)
    val uiState = _uiState.asStateFlow()

    fun selectCategory(category: LectureCategory?) {
        _uiState.update { applyFilters(it.copy(category = category)) }
    }

    fun setHideTaken(checked: Boolean) {
        _uiState.update { applyFilters(it.copy(hideTaken = checked)) }
    }

    fun setHideRestricted(checked: Boolean) {
        _uiState.update { applyFilters(it.copy(hideRestricted = checked)) }
    }

    fun setCourseExpanded(courseId: Long, expanded: Boolean) {
        _uiState.update { state ->
            val ids = state.expandedCourseIds
            state.copy(expandedCourseIds = if (expanded) ids + courseId else ids - courseId)
        }
    }

    fun toggleSection(courseCode: String) {
        _uiState.update { state ->
            val codes = state.expandedSectionCodes
            state.copy(
                expandedSectionCodes = if (courseCode in codes) codes - courseCode else codes + courseCode,
            )
        }
    }

    private fun applyFilters(state: LecturelistUiState): LecturelistUiState {
        val categoryId = state.category?.categoryId
        val courses = dummyLecturelistUiState.courses.filter { course ->
            (categoryId == null || course.categoryId == categoryId) &&
                    (!state.hideTaken || course.courseId !in dummyTakenCourseIds) &&
                    (!state.hideRestricted || course.courseId !in dummyRestrictedCourseIds)
        }
        return state.copy(courses = courses)
    }
}
