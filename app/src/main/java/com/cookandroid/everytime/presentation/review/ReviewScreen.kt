package com.cookandroid.everytime.presentation.review

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.cookandroid.everytime.core.designsystem.component.button.EverytimeIconLeftButton
import com.cookandroid.everytime.core.designsystem.theme.EverytimeTheme
import com.cookandroid.everytime.presentation.review.component.bottomnavigation.EverytimeBottomNavigation
import com.cookandroid.everytime.presentation.review.component.card.ReviewList
import com.cookandroid.everytime.presentation.review.component.filterrow.EverytimeReviewScoreFilterBar
import com.cookandroid.everytime.presentation.review.component.keywordfilterbar.EverytimeReviewKeywordFilterBar
import com.cookandroid.everytime.presentation.review.component.topbar.EverytimeReviewTopbar

@Composable
fun ReviewRoute(
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ReviewViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()
    val currentOnNavigateBack by rememberUpdatedState(onNavigateBack)

    LaunchedEffect(viewModel) {
        viewModel.sideEffect.collect { sideEffect ->
            when (sideEffect) {
                ReviewSideEffect.NavigateBack -> currentOnNavigateBack()
            }
        }
    }

    ReviewScreen(
        uiState = uiState,
        onBackClick = viewModel::onBackClick,
        onKeywordClick = viewModel::onKeywordClick,
        modifier = modifier
    )
}

@Composable
fun ReviewScreen(
    uiState: ReviewUiState,
    onBackClick: () -> Unit,
    onKeywordClick: (String?) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = EverytimeTheme.colors.white),
    ) {
        EverytimeReviewTopbar(
            text = uiState.lectureName,
            onBackClick = onBackClick,
        )

        Spacer(modifier = Modifier.height(2.dp))

        EverytimeReviewScoreFilterBar(score = uiState.averageScore)

        Spacer(modifier = Modifier.height(20.dp))

        EverytimeReviewKeywordFilterBar(
            keywords = uiState.keywords,
            totalCount = uiState.reviews.size,
            selectedKeyword = uiState.selectedKeyword,
            onKeywordClick = onKeywordClick,
        )

        Spacer(modifier = Modifier.height(4.dp))

        Box(modifier = Modifier.weight(1f)) {
            ReviewList(
                reviews = uiState.visibleReviews,
                selectedKeyword = uiState.selectedKeyword,
                modifier = Modifier.fillMaxSize(),
            )

            EverytimeIconLeftButton(
                text = "평가하기",
                onClick = {}, // 기획상 동작 없는 목업 버튼
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 8.dp),
            )
        }

        EverytimeBottomNavigation()
    }
}

@Preview(showBackground = true, widthDp = 375, heightDp = 730)
@Composable
private fun ReviewScreenPreview() {
    var selectedKeyword by remember { mutableStateOf<String?>(null) }

    EverytimeTheme {
        ReviewScreen(
            uiState = ReviewUiState(selectedKeyword = selectedKeyword),
            onBackClick = {},
            onKeywordClick = { selectedKeyword = it },
            modifier = Modifier.safeDrawingPadding(),
        )
    }
}

@Preview(showBackground = true, widthDp = 375, heightDp = 730)
@Composable
private fun ReviewScreenEmptyPreview() {
    EverytimeTheme {
        ReviewScreen(
            uiState = ReviewUiState(averageScore = 0f, reviews = emptyList()),
            onBackClick = {},
            onKeywordClick = {},
            modifier = Modifier.safeDrawingPadding(),
        )
    }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 640)
@Composable
private fun ReviewScreenNarrowPreview() {
    EverytimeTheme {
        ReviewScreen(
            uiState = ReviewUiState(),
            onBackClick = {},
            onKeywordClick = {},
            modifier = Modifier.safeDrawingPadding(),
        )
    }
}

@Preview(showBackground = true, widthDp = 375, heightDp = 730)
@Composable
private fun ReviewScreenKeywordSelectedPreview() {
    EverytimeTheme {
        ReviewScreen(
            uiState = ReviewUiState(selectedKeyword = "교수님"),
            onBackClick = {},
            onKeywordClick = {},
            modifier = Modifier.safeDrawingPadding(),
        )
    }
}
