package com.cookandroid.everytime.presentation.lecturelist

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.cookandroid.everytime.core.designsystem.theme.EverytimeTheme

@Composable
fun LecturelistRoute(
    modifier: Modifier = Modifier,
    viewModel: LecturelistViewModel = hiltViewModel(),
) {
    LecturelistScreen(modifier = modifier)
}

@Composable
fun LecturelistScreen(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Text(text = "강의 목록")
    }
}

@Preview(showBackground = true)
@Composable
private fun LecturelistScreenPreview() {
    EverytimeTheme {
        LecturelistScreen()
    }
}
