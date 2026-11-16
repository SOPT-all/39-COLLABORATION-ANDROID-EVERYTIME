package com.cookandroid.everytime.presentation.review.component.card

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cookandroid.everytime.core.designsystem.component.button.EverytimeSecondaryRectangleButton
import com.cookandroid.everytime.core.designsystem.component.button.EverytimeSecondaryRectangleButtonType
import com.cookandroid.everytime.core.designsystem.component.score.EverytimeScore
import com.cookandroid.everytime.core.designsystem.component.score.EverytimeScoreType
import com.cookandroid.everytime.core.designsystem.theme.EverytimeTheme

data class ReviewCardUiModel(
    val score: Float,
    val semesterLabel: String,
    val content: String,
)

@Composable
fun EverytimeReviewCard(
    review: ReviewCardUiModel,
    modifier: Modifier = Modifier,
    highlightedKeyword: String? = null,
) {
    val highlightColor = EverytimeTheme.colors.pointYellow
    val annotatedContent = buildAnnotatedString {
        append(review.content)

        if (!highlightedKeyword.isNullOrEmpty()) {
            var start = review.content.indexOf(highlightedKeyword)
            while (start >= 0) {
                addStyle(
                    style = SpanStyle(background = highlightColor),
                    start = start,
                    end = start + highlightedKeyword.length,
                )
                start = review.content.indexOf(
                    string = highlightedKeyword,
                    startIndex = start + highlightedKeyword.length,
                )
            }
        }
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(color = EverytimeTheme.colors.white)
            .padding(all = 16.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top,
        ) {
            Column(
                modifier = Modifier,
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                EverytimeScore(
                    score = review.score,
                    type = EverytimeScoreType.Small,
                )

                Text(
                    text = review.semesterLabel,
                    color = EverytimeTheme.colors.neutral400,
                    style = EverytimeTheme.typography.caption4,
                )
            }

            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                EverytimeSecondaryRectangleButton(
                    text = "추천",
                    onClick = {}, //기능없음
                    type = EverytimeSecondaryRectangleButtonType.ICON,
                )

                EverytimeSecondaryRectangleButton(
                    text = "신고",
                    onClick = {}, //기능없음
                )
            }
        }

        Text(
            text = annotatedContent,
            color = EverytimeTheme.colors.neutral800,
            style = EverytimeTheme.typography.body4,
        )
    }
}

@Preview(name = "강의평 카드", showBackground = true, widthDp = 375)
@Composable
private fun EverytimeReviewCardPreview() {
    EverytimeTheme {
        EverytimeReviewCard(
            review = ReviewCardUiModel(
                score = 5f,
                semesterLabel = "26년 2학기 수강자",
                content = "교수님께서 좋으셔서 듣기 좋았어요!\n" +
                    "물론 풀강을.. 하시지만 안하실때도 있기도 하고 너무 친절하게 알려주셔서 만족했습니다!",
            ),
        )
    }
}
