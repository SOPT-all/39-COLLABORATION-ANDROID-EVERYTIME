package com.cookandroid.everytime.presentation.timetable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.cookandroid.everytime.core.designsystem.theme.EverytimeTheme
import com.cookandroid.everytime.presentation.timetable.component.timetable.EverytimeTimetableSelectionGrid
import com.cookandroid.everytime.presentation.timetable.component.timetable.TimetableSlot
import com.cookandroid.everytime.presentation.timetable.component.timetable.expandedGridDays
import com.cookandroid.everytime.presentation.timetable.component.topbar.EverytimeTimetableTopbar

@Composable
fun TimetableRoute(
    onNavigateBack: () -> Unit,
    onApply: (String?, List<String>) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: TimetableViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(viewModel) {
        viewModel.sideEffect.collect { sideEffect ->
            when (sideEffect) {
                TimetableSideEffect.NavigateBack -> onNavigateBack()
                is TimetableSideEffect.Apply -> onApply(sideEffect.label, sideEffect.times)
            }
        }
    }

    TimetableScreen(
        uiState = uiState,
        onDayClick = viewModel::onDayClick,
        onHourClick = viewModel::onHourClick,
        onNavigateBack = viewModel::onBackClick,
        onApply = viewModel::onApplyClick,
        modifier = modifier
    )
}

@Composable
fun TimetableScreen(
    uiState: TimetableUiState,
    onDayClick: (Int) -> Unit,
    onHourClick: (Int) -> Unit,
    onNavigateBack: () -> Unit,
    onApply: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxSize()) {
        EverytimeTimetableTopbar(
            text = "시간",
            onBackClick = onNavigateBack,
            onApplyClick = onApply
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(state = rememberScrollState())
                .padding(bottom = 16.dp)
        ) {
            Spacer(modifier = Modifier.height(2.dp))

            Text(
                text = "선택한 시간 내에 개설된 과목만 검색합니다.\n요일 혹은 시간을 누르면 일괄 선택할 수 있습니다.",
                color = EverytimeTheme.colors.neutral400,
                style = EverytimeTheme.typography.caption1,
                modifier = Modifier.padding(horizontal = 24.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            EverytimeTimetableSelectionGrid(
                highlightedSlots = uiState.highlightedSlots,
                selectedSlots = uiState.selectedSlots,
                onDayClick = onDayClick,
                onHourClick = onHourClick,
                modifier = Modifier.padding(horizontal = 12.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun TimetableScreenPreview() {
    EverytimeTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            TimetableScreen(
                uiState = TimetableUiState(
                    selectedSlots = expandedGridDays.indices.map { dayIndex ->
                        TimetableSlot(dayIndex = dayIndex, hour = 9)
                    }.toSet(),
                ),
                onDayClick = {},
                onHourClick = {},
                onNavigateBack = {},
                onApply = {}
            )
        }
    }
}
