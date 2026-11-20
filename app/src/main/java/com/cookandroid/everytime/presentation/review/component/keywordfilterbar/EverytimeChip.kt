package com.cookandroid.everytime.presentation.review.component.keywordfilterbar

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cookandroid.everytime.core.designsystem.theme.EverytimeTheme
import com.cookandroid.everytime.core.util.noRippleClickable

@Composable
fun EverytimeChip(
    keyword: String,
    count: String,
    modifier: Modifier = Modifier,
    isSelected: Boolean = false,
    onClick: () -> Unit
) {
    val shape = CircleShape
    val backgroundColor =
        if (isSelected) EverytimeTheme.colors.black else EverytimeTheme.colors.white
    val textColor =
        if (isSelected) EverytimeTheme.colors.neutral50 else EverytimeTheme.colors.neutral800

    Text(
        text = "${keyword} (${count})",
        modifier = modifier
            .clip(shape = shape)
            .background(backgroundColor)
            .border(width = 1.dp, color = EverytimeTheme.colors.neutral300, shape = shape)
            .noRippleClickable(onClick = onClick)
            .padding(horizontal = 13.dp, vertical = 8.dp),
        color = textColor,
        style = EverytimeTheme.typography.caption1,
        maxLines = 1
    )
}

@Preview(showBackground = true)
@Composable
private fun EverytimeChipPreview() {
    EverytimeTheme {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            EverytimeChip(
                keyword = "키워드",
                count = "1",
                onClick = {},
            )
            EverytimeChip(
                keyword = "키워드",
                count = "1",
                isSelected = true,
                onClick = {},
            )
        }
    }
}
