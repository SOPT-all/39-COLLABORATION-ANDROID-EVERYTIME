package com.cookandroid.everytime.presentation.review.component.card

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cookandroid.everytime.core.designsystem.theme.EverytimeTheme
import com.cookandroid.everytime.presentation.review.ReviewItemUiModel

@Composable
fun ReviewList(
    reviews: List<ReviewItemUiModel>,
    selectedKeyword: String?,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(horizontal = 8.dp),
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = contentPadding,
        overscrollEffect = null,
    ) {
        items(items = reviews, key = { it.id }) { review ->
            EverytimeReviewCard(
                review = ReviewCardUiModel(
                    score = review.score.toFloat(),
                    semesterLabel = review.semesterLabel,
                    content = review.content,
                ),
                highlightedKeyword = selectedKeyword,
            )

            HorizontalDivider(
                thickness = 1.dp,
                color = EverytimeTheme.colors.neutral200,
            )
        }
    }
}
