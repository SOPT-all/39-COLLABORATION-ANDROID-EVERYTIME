package com.cookandroid.everytime.core.designsystem.component.button

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.cookandroid.everytime.core.designsystem.theme.EverytimeTheme

enum class EverytimeSecondaryButtonType(
    val verticalPadding: Dp
) {
    MD_WHITE(7.dp),
    SM_WHITE(5.dp),
    SM_RED(5.dp)
}

@Composable
fun EverytimeSecondaryButton(
    text: String,
    type: EverytimeSecondaryButtonType,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val colors = EverytimeTheme.colors
    val shape = RoundedCornerShape(100.dp)  // 버튼 모서리 반경

    val backgroundColor = when (type) {
        EverytimeSecondaryButtonType.MD_WHITE,
        EverytimeSecondaryButtonType.SM_WHITE -> colors.white

        EverytimeSecondaryButtonType.SM_RED -> colors.primary900
    }

    val textColor = when (type) {
        EverytimeSecondaryButtonType.MD_WHITE,
        EverytimeSecondaryButtonType.SM_WHITE -> colors.neutral800

        EverytimeSecondaryButtonType.SM_RED -> colors.white
    }

    val borderModifier = when (type) {
        EverytimeSecondaryButtonType.MD_WHITE,
        EverytimeSecondaryButtonType.SM_WHITE -> Modifier.border(
            width = 1.dp,
            color = colors.neutral300,
            shape = shape
        )

        EverytimeSecondaryButtonType.SM_RED -> Modifier
    }

    Box(
        modifier = modifier
            .clip(shape)
            .background(backgroundColor)
            .then(borderModifier)
            .clickable(onClick = onClick)
            .padding(
                horizontal = 12.dp,
                vertical = type.verticalPadding
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = EverytimeTheme.typography.caption2,
            color = textColor,
            maxLines = 1
        )
    }
}


@Preview(showBackground = true, widthDp = 320)
@Composable
private fun EverytimeSecondaryButtonPreview() {
    EverytimeTheme {
        Column(
            modifier = Modifier
                .background(EverytimeTheme.colors.white)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            EverytimeSecondaryButtonType.entries.forEach { type ->
                var shortClickCount by remember { mutableIntStateOf(0) }
                var longClickCount by remember { mutableIntStateOf(0) }

                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = type.name,
                        style = EverytimeTheme.typography.body4,
                        color = EverytimeTheme.colors.neutral900
                    )

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        EverytimeSecondaryButton(
                            text = "버튼",
                            type = type,
                            onClick = { shortClickCount++ }
                        )

                        EverytimeSecondaryButton(
                            text = "시간표에 추가",
                            type = type,
                            onClick = { longClickCount++ }
                        )
                    }

                    Text(
                        text = "짧은 문구: ${shortClickCount}회 / 긴 문구: ${longClickCount}회",
                        style = EverytimeTheme.typography.caption2,
                        color = EverytimeTheme.colors.neutral800
                    )
                }
            }
        }
    }
}