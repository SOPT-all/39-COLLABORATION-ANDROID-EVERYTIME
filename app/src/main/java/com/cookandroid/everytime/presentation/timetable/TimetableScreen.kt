package com.cookandroid.everytime.presentation.timetable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.cookandroid.everytime.core.designsystem.theme.EverytimeTheme
import com.cookandroid.everytime.presentation.timetable.component.timetable.EverytimeTimetableSelectionGrid
import com.cookandroid.everytime.presentation.timetable.component.timetable.TimetableSlot
import com.cookandroid.everytime.presentation.timetable.component.topbar.EverytimeTimetableTopbar

@Composable
fun TimetableRoute(
    onNavigateBack: () -> Unit,
    onApply: (String?) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: TimetableViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()
    val currentOnNavigateBack by rememberUpdatedState(onNavigateBack)
    val currentOnApply by rememberUpdatedState(onApply)

    LaunchedEffect(viewModel) {
        viewModel.sideEffect.collect { sideEffect ->
            when (sideEffect) {
                TimetableSideEffect.NavigateBack -> currentOnNavigateBack()
                is TimetableSideEffect.Apply -> currentOnApply(sideEffect.label)
            }
        }
    }

    TimetableScreen(
        uiState = uiState,
        onSlotClick = viewModel::onSlotClick,
        onBackClick = viewModel::onBackClick,
        onApplyClick = viewModel::onApplyClick,
        modifier = modifier
    )
}

@Composable
fun TimetableScreen(
    uiState: TimetableUiState,
    onSlotClick: (TimetableSlot) -> Unit,
    onBackClick: () -> Unit,
    onApplyClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.fillMaxSize()) {
        EverytimeTimetableTopbar(
            text = "시간",
            onBackClick = onBackClick,
            onApplyClick = onApplyClick
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(state = rememberScrollState())
                .padding(bottom = 16.dp)
        ) {
            Text(
                text = "선택한 시간 내에 개설된 과목만 검색합니다.",
                color = EverytimeTheme.colors.neutral400,
                style = EverytimeTheme.typography.caption1,
                modifier = Modifier.padding(start = 24.dp, top = 2.dp, end = 24.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            EverytimeTimetableSelectionGrid(
                highlightedSlots = uiState.highlightedSlots,
                selectedSlot = uiState.selectedSlot,
                onSlotClick = onSlotClick,
                modifier = Modifier.padding(horizontal = 12.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun TimetableScreenPreview() {
    var uiState by remember {
        mutableStateOf(TimetableUiState(selectedSlot = TimetableSlot(dayIndex = 0, hour = 8)))
    }

    EverytimeTheme {
        TimetableScreen(
            uiState = uiState,
            onSlotClick = { slot ->
                val selectedSlot = if (uiState.selectedSlot == slot) null else slot
                uiState = uiState.copy(selectedSlot = selectedSlot)
            },
            onBackClick = {},
            onApplyClick = {}
        )
    }
}
