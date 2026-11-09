package com.cookandroid.everytime.presentation.timetable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.cookandroid.everytime.core.designsystem.theme.EVERYTIMETheme

@Composable
fun TimetableRoute(
    modifier: Modifier = Modifier,
    viewModel: TimetableViewModel = hiltViewModel(),
) {
    TimetableScreen(modifier = modifier)
}

@Composable
fun TimetableScreen(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Text(text = "시간표")
    }
}

@Preview(showBackground = true)
@Composable
private fun TimetableScreenPreview() {
    EVERYTIMETheme {
        TimetableScreen()
    }
}
