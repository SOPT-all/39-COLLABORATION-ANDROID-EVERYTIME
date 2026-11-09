package com.cookandroid.everytime.presentation.timetable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.cookandroid.everytime.core.designsystem.theme.EVERYTIMETheme

@Composable
fun TimetableRoute(
    onNavigateToLectureList: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: TimetableViewModel = hiltViewModel(),
) {
    TimetableScreen(
        onNavigateToLectureList = onNavigateToLectureList,
        modifier = modifier
    )
}

@Composable
fun TimetableScreen(
    onNavigateToLectureList: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = "시간표")

        Button(onClick = onNavigateToLectureList) {
            Text(text = "강의 목록 열기")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun TimetableScreenPreview() {
    EVERYTIMETheme {
        TimetableScreen(onNavigateToLectureList = {})
    }
}
