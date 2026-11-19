package com.cookandroid.everytime.presentation.lecturelist

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cookandroid.everytime.data.repository.lecturelist.LecturelistRepository
import com.cookandroid.everytime.presentation.lecturelist.component.model.toModel
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
class LecturelistViewModel @Inject constructor(
    private val repository: LecturelistRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(LecturelistUiState())
    val uiState = _uiState.asStateFlow()

    private var loadJob: Job? = null
    private var lastLoadSucceeded = false

    fun selectCategory(category: LectureCategory?) {
        _uiState.update { it.copy(category = category) }
        loadCourses()
    }

    fun setHideTaken(checked: Boolean) {
        _uiState.update { it.copy(hideTaken = checked) }
        loadCourses()
    }

    fun setHideRestricted(checked: Boolean) {
        _uiState.update { it.copy(hideRestricted = checked) }
        loadCourses()
    }

    fun setTimes(times: List<String>) {
        val sameTimes = _uiState.value.times == times
        val isLoading = loadJob?.isActive == true

        if (sameTimes && (isLoading || lastLoadSucceeded)) return

        _uiState.update { it.copy(times = times.toList()) }
        loadCourses()
    }

    fun setCourseExpanded(courseId: Long, expanded: Boolean) {
        _uiState.update { state ->
            val ids = state.expandedCourseIds
            state.copy(
                expandedCourseIds = if (expanded) ids + courseId else ids - courseId,
            )
        }
    }

    fun toggleSection(courseCode: String) {
        _uiState.update { state ->
            val codes = state.expandedSectionCodes
            state.copy(
                expandedSectionCodes =
                    if (courseCode in codes) codes - courseCode else codes + courseCode,
            )
        }
    }

    private fun loadCourses() {
        loadJob?.cancel()
        lastLoadSucceeded = false

        val filters = _uiState.value
        _uiState.update { it.copy(courses = emptyList()) }

        loadJob = viewModelScope.launch {
            try {
                val response = repository.getCourses(
                    categoryId = filters.category?.categoryId,
                    departmentId = filters.departmentId,
                    hideTaken = filters.hideTaken.takeIf { it },
                    hideRestricted = filters.hideRestricted.takeIf { it },
                    times = filters.times.takeIf { it.isNotEmpty() },
                )
                val courses = response.courses.map { it.toModel() }

                ensureActive()
                _uiState.update { it.copy(courses = courses) }
                lastLoadSucceeded = true
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                Log.e("LecturelistViewModel", "강의 목록 조회 실패", e)
            }
        }
    }
}
