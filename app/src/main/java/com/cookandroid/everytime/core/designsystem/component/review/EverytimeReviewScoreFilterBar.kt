package com.cookandroid.everytime.core.designsystem.component.review

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cookandroid.everytime.core.designsystem.component.button.EverytimeIconRightButton
import com.cookandroid.everytime.core.designsystem.component.card.EverytimeScore
import com.cookandroid.everytime.core.designsystem.component.card.EverytimeScoreType
import com.cookandroid.everytime.core.designsystem.theme.EverytimeTheme

@Composable
fun EverytimeReviewScoreFilterBar(
    score: Float,
    onFilterClick: () -> Unit,
    onSortClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(color = Color.Transparent)
            .padding(start = 12.dp, end = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = String.format("%.2f", score),
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                style = EverytimeTheme.typography.header1,
                color = EverytimeTheme.colors.neutral900,
                maxLines = 1
            )

            EverytimeScore(
                score = score,
                type = EverytimeScoreType.Large
            )
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(space = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            EverytimeIconRightButton(
                text = "전체",
                onClick = onFilterClick
            )

            EverytimeIconRightButton(
                text = "등록순",
                onClick = onSortClick
            )
        }
    }
}

@Preview(showBackground = true, widthDp = 375)
@Composable
private fun EverytimeReviewScoreFilterBarPreview() {
    EverytimeTheme {
        var filterClickCount by remember { mutableIntStateOf(0) }
        var sortClickCount by remember { mutableIntStateOf(0) }

        Column(
            modifier = Modifier
                .background(color = EverytimeTheme.colors.white)
                .padding(vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            EverytimeReviewScoreFilterBar(
                score = 3.67f,
                onFilterClick = { filterClickCount++ },
                onSortClick = { sortClickCount++ }
            )

            Text(
                text = "필터 클릭: ${filterClickCount}회 / 정렬 클릭: ${sortClickCount}회",
                modifier = Modifier.padding(horizontal = 16.dp),
                style = EverytimeTheme.typography.caption2,
                color = EverytimeTheme.colors.neutral800
            )
        }
    }
}
