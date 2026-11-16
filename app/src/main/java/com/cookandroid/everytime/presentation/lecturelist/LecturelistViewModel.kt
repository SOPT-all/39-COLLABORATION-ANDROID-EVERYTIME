package com.cookandroid.everytime.presentation.lecturelist

import androidx.lifecycle.ViewModel
import com.cookandroid.everytime.presentation.lecturelist.component.model.LecturelistCourse
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

@HiltViewModel
class LecturelistViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(dummyLecturelistUiState)
    val uiState = _uiState.asStateFlow()

    fun clearMajorFilter() {
        _uiState.update { it.copy(majorValue = null) }
    }

    fun setHideTaken(checked: Boolean) {
        _uiState.update { state ->
            state.copy(
                hideTaken = checked,
                courses = filterCourses(checked, state.hideRestricted),
            )
        }
    }

    fun setHideRestricted(checked: Boolean) {
        _uiState.update { state ->
            state.copy(
                hideRestricted = checked,
                courses = filterCourses(state.hideTaken, checked),
            )
        }
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

    private fun filterCourses(hideTaken: Boolean, hideRestricted: Boolean): List<LecturelistCourse> =
        dummyLecturelistUiState.courses.filter { course ->
            (!hideTaken || course.courseId !in dummyTakenCourseIds) &&
                    (!hideRestricted || course.courseId !in dummyRestrictedCourseIds)
        }
}
