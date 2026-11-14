package com.cookandroid.everytime.core.designsystem.component.chip

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
        horizontalArrangement = Arrangement.spacedBy(
            space = 6.dp,
            alignment = Alignment.CenterHorizontally
        ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            style = EverytimeTheme.typography.caption2,
            color = if (isActive) {
                EverytimeTheme.colors.neutral700
            } else {
                EverytimeTheme.colors.neutral800
            },
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
            Image(
                painter = painterResource(id = R.drawable.ic_close_mini),
                contentDescription = "필터 초기화",
                modifier = Modifier
                    .size(14.dp)
                    .noRippleClickable(onClick = onClearClick)
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
            EverytimeFilterChipPreviewItem(
                title = "미설정 / 짧은 문구",
                value = "없음",
                isActive = false
            )
            EverytimeFilterChipPreviewItem(
                title = "설정됨 / 짧은 문구",
                value = "교양",
                isActive = true
            )
            EverytimeFilterChipPreviewItem(
                title = "미설정 / 긴 문구 배치 확인",
                value = "치이카와 짱귀여움",
                isActive = false
            )
            EverytimeFilterChipPreviewItem(
                title = "설정됨 / 긴 문구",
                value = "우사기의 창의적 문제해결",
                isActive = true
            )
        }
    }
}

@Composable
private fun EverytimeFilterChipPreviewItem(
    title: String,
    value: String,
    isActive: Boolean
) {
    var chipClickCount by remember { mutableIntStateOf(0) }
    var clearClickCount by remember { mutableIntStateOf(0) }

    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = title,
            style = EverytimeTheme.typography.body2,
            color = EverytimeTheme.colors.neutral900
        )

        EverytimeFilterChip(
            label = "검색어:",
            value = value,
            isActive = isActive,
            onClick = { chipClickCount++ },
            onClearClick = { clearClickCount++ }
        )

        Text(
            text = "칩 클릭: ${chipClickCount}회 / 초기화 클릭: ${clearClickCount}회",
            style = EverytimeTheme.typography.caption2,
            color = EverytimeTheme.colors.neutral800
        )
    }
}
