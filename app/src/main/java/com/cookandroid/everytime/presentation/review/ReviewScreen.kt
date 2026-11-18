package com.cookandroid.everytime.presentation.review

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
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
    sectionId: Long?,
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ReviewViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(sectionId) {
        viewModel.loadReviews(sectionId = sectionId)
    }

    ReviewScreen(
        uiState = uiState,
        onNavigateBack = onNavigateBack,
        onKeywordClick = {}, // 칩별 API 연결 전까지 전체 조회만 표시
        modifier = modifier
    )
}

@Composable
fun ReviewScreen(
    uiState: ReviewUiState,
    onNavigateBack: () -> Unit,
    onKeywordClick: (Long?) -> Unit,
    modifier: Modifier = Modifier,
) {
    var reviewButtonHeightPx by remember { mutableIntStateOf(0) }
    val reviewListBottomPadding = with(LocalDensity.current) { reviewButtonHeightPx.toDp() }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = EverytimeTheme.colors.white),
    ) {
        EverytimeReviewTopbar(
            text = uiState.lectureName,
            onBackClick = onNavigateBack,
        )

        Spacer(modifier = Modifier.height(2.dp))

        EverytimeReviewScoreFilterBar(score = uiState.averageScore)

        Spacer(modifier = Modifier.height(20.dp))

        EverytimeReviewKeywordFilterBar(
            keywords = uiState.keywords,
            totalCount = uiState.totalReviewCount,
            selectedKeywordId = uiState.selectedKeywordId,
            onKeywordClick = onKeywordClick,
            modifier = Modifier.padding(horizontal = 16.dp),
        )

        Spacer(modifier = Modifier.height(4.dp))

        Box(modifier = Modifier.weight(1f)) {
            ReviewList(
                reviews = uiState.reviews,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 8.dp),
                contentPadding = PaddingValues(bottom = reviewListBottomPadding),
            )

            EverytimeIconLeftButton(
                text = "평가하기",
                onClick = {}, // 기획상 동작 없는 목업 버튼
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .onSizeChanged { reviewButtonHeightPx = it.height }
                    .padding(bottom = 8.dp),
            )
        }

        EverytimeBottomNavigation()
    }
}

@Preview(showBackground = true, widthDp = 375, heightDp = 730)
@Composable
private fun ReviewScreenPreview() {
    EverytimeTheme {
        ReviewScreen(
            uiState = reviewPreviewUiState,
            onNavigateBack = {},
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
            uiState = reviewPreviewUiState,
            onNavigateBack = {},
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
            uiState = reviewPreviewUiState.copy(selectedKeywordId = 1L),
            onNavigateBack = {},
            onKeywordClick = {},
            modifier = Modifier.safeDrawingPadding(),
        )
    }
}
