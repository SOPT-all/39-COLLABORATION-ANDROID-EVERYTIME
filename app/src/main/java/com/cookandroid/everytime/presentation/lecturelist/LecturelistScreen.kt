package com.cookandroid.everytime.presentation.lecturelist

import androidx.compose.foundation.layout.Arrangement
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
fun LecturelistRoute(
    onLectureClick: () -> Unit,
    onNavigateToTimetable: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: LecturelistViewModel = hiltViewModel(),
) {
    LecturelistScreen(
        onLectureClick = onLectureClick,
        onNavigateToTimetable = onNavigateToTimetable,
        modifier = modifier
    )
}

@Composable
fun LecturelistScreen(
    onLectureClick: () -> Unit,
    onNavigateToTimetable: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = "강의 목록")

        Button(onClick = onLectureClick) {
            Text(text = "샘플 강의")
        }

        Button(onClick = onNavigateToTimetable) {
            Text(text = "시간표 열기")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun LecturelistScreenPreview() {
    EVERYTIMETheme {
        LecturelistScreen(
            onLectureClick = {},
            onNavigateToTimetable = {}
        )
    }
}
