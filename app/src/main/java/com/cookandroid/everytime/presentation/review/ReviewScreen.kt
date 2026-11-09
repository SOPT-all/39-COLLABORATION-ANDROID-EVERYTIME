package com.cookandroid.everytime.presentation.review

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
fun ReviewRoute(
    modifier: Modifier = Modifier,
    viewModel: ReviewViewModel = hiltViewModel(),
) {
    // TODO: ViewModel의 상태와 이벤트를 Screen에 연결합니다.
    ReviewScreen(modifier = modifier)
}

@Composable
fun ReviewScreen(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Text(text = "강의평")
    }
}

@Preview(showBackground = true)
@Composable
private fun ReviewScreenPreview() {
    EVERYTIMETheme {
        ReviewScreen()
    }
}
