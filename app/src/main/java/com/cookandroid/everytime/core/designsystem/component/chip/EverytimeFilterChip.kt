package com.cookandroid.everytime.core.designsystem.component.chip

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cookandroid.everytime.R
import com.cookandroid.everytime.core.designsystem.theme.EverytimeTheme
import com.cookandroid.everytime.core.util.noRippleClickable

@Composable
fun EverytimeFilterChip(
    label: String,
    value: String,
    isActive: Boolean,
    onClick: () -> Unit,
    onClearClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .background(
                color = EverytimeTheme.colors.neutral50,
                shape = CircleShape
            )
            .noRippleClickable(onClick = onClick)
            .padding(horizontal = 10.dp, vertical = 9.dp),
        horizontalArrangement = Arrangement.spacedBy(space = 6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            style = EverytimeTheme.typography.caption2,
            color = EverytimeTheme.colors.neutral700,
            maxLines = 1
        )

        Text(
            text = value,
            style = EverytimeTheme.typography.body2,
            color = if (isActive) {
                EverytimeTheme.colors.primary900
            } else {
                EverytimeTheme.colors.neutral700
            },
            maxLines = 1
        )

        if (isActive) {
            Icon(
                painter = painterResource(id = R.drawable.ic_close_mini),
                contentDescription = null,
                modifier = Modifier
                    .size(14.dp)
                    .noRippleClickable(onClick = onClearClick),
                tint = Color.Unspecified
            )
        }
    }
}

@Preview(showBackground = true, widthDp = 375)
@Composable
private fun EverytimeFilterChipPreview() {
    EverytimeTheme {
        Column(
            modifier = Modifier
                .background(color = EverytimeTheme.colors.white)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            EverytimeFilterChip(
                label = "검색어:",
                value = "없음",
                isActive = false,
                onClick = {},
                onClearClick = {}
            )
            EverytimeFilterChip(
                label = "검색어:",
                value = "교양",
                isActive = true,
                onClick = {},
                onClearClick = {}
            )
            EverytimeFilterChip(
                label = "검색어:",
                value = "치이카와 짱귀여움",
                isActive = false,
                onClick = {},
                onClearClick = {}
            )
            EverytimeFilterChip(
                label = "검색어:",
                value = "우사기의 창의적 문제해결",
                isActive = true,
                onClick = {},
                onClearClick = {}
            )
        }
    }
}
