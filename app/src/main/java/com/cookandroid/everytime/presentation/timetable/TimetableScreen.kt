package com.cookandroid.everytime.presentation.timetable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.cookandroid.everytime.core.designsystem.theme.EverytimeTheme

@Composable
fun TimetableRoute(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: TimetableViewModel = hiltViewModel(),
) {
    TimetableScreen(
        onBackClick = onBackClick,
        modifier = modifier
    )
}

@Composable
fun TimetableScreen(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Text(text = "시간표")

        TextButton(
            onClick = onBackClick,
            modifier = Modifier.align(Alignment.TopStart)
        ) {
            Text(text = "뒤로가기")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun TimetableScreenPreview() {
    EverytimeTheme {
        TimetableScreen(onBackClick = {})
    }
}
