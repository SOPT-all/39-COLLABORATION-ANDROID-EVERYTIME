package com.cookandroid.everytime.presentation.review.component.keywordfilterbar

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cookandroid.everytime.core.designsystem.theme.EverytimeTheme
import com.cookandroid.everytime.presentation.review.ReviewUiState
import com.cookandroid.everytime.presentation.review.component.model.ReviewKeywordUiModel

@Composable
fun EverytimeReviewKeywordFilterBar(
    keywords: List<ReviewKeywordUiModel>,
    totalCount: Int,
    selectedKeyword: String?,
    onKeywordClick: (String?) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .horizontalScroll(state = rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(space = 5.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        EverytimeChip(
            keyword = "전체",
            count = totalCount.toString(),
            isSelected = selectedKeyword == null,
            onClick = { onKeywordClick(null) },
        )

        keywords.forEach { item ->
            EverytimeChip(
                keyword = item.keyword,
                count = item.count.toString(),
                isSelected = selectedKeyword == item.keyword,
                onClick = { onKeywordClick(item.keyword) },
            )
        }
    }
}

@Preview(showBackground = true, widthDp = 375)
@Composable
private fun EverytimeReviewKeywordFilterBarPreview() {
    val uiState = ReviewUiState()

    EverytimeTheme {
        Column(
            modifier = Modifier.padding(vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(space = 16.dp),
        ) {
            EverytimeReviewKeywordFilterBar(
                keywords = uiState.keywords,
                totalCount = uiState.reviews.size,
                selectedKeyword = null,
                onKeywordClick = {},
            )

            EverytimeReviewKeywordFilterBar(
                keywords = uiState.keywords,
                totalCount = uiState.reviews.size,
                selectedKeyword = "교수님",
                onKeywordClick = {},
            )
        }
    }
}
