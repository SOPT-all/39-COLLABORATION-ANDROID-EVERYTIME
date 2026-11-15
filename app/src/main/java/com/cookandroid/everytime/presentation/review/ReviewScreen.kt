package com.cookandroid.everytime.presentation.review

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
fun ReviewRoute(
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ReviewViewModel = hiltViewModel(),
) {
    // TODO: ViewModel의 상태와 이벤트를 Screen에 연결합니다.
    ReviewScreen(
        onNavigateBack = onNavigateBack,
        modifier = modifier
    )
}

@Composable
fun ReviewScreen(
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Text(text = "강의평")

        TextButton(
            onClick = onNavigateBack,
            modifier = Modifier.align(Alignment.TopStart)
        ) {
            Text(text = "뒤로가기")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ReviewScreenPreview() {
    EverytimeTheme {
        ReviewScreen(onNavigateBack = {})
    }
}
