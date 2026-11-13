package com.cookandroid.everytime.core.designsystem.component.button

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
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
import com.cookandroid.everytime.core.util.noRippleClickable

enum class EverytimeSecondaryCircleButtonType(
    val verticalPadding: Dp
) {
    MD_WHITE(7.dp),
    SM_WHITE(5.dp),
    SM_RED(5.dp)
}

@Composable
fun EverytimeSecondaryCircleButton(
    text: String,
    type: EverytimeSecondaryCircleButtonType,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val shape = CircleShape  // 캡슐 모양

    val backgroundColor = when (type) {
        EverytimeSecondaryCircleButtonType.MD_WHITE,
        EverytimeSecondaryCircleButtonType.SM_WHITE -> EverytimeTheme.colors.white

        EverytimeSecondaryCircleButtonType.SM_RED -> EverytimeTheme.colors.primary900
    }

    val textColor = when (type) {
        EverytimeSecondaryCircleButtonType.MD_WHITE,
        EverytimeSecondaryCircleButtonType.SM_WHITE -> EverytimeTheme.colors.neutral800

        EverytimeSecondaryCircleButtonType.SM_RED -> EverytimeTheme.colors.white
    }

    val borderModifier = when (type) {
        EverytimeSecondaryCircleButtonType.MD_WHITE,
        EverytimeSecondaryCircleButtonType.SM_WHITE -> Modifier.border(
            width = 1.dp,
            color = EverytimeTheme.colors.neutral300,
            shape = shape
        )

        EverytimeSecondaryCircleButtonType.SM_RED -> Modifier
    }

    Box(
        modifier = modifier
            .clip(shape)
            .background(backgroundColor)
            .then(borderModifier)
            .noRippleClickable(onClick = onClick)
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
private fun EverytimeSecondaryCircleButtonPreview() {
    EverytimeTheme {
        Column(
            modifier = Modifier
                .background(EverytimeTheme.colors.white)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            EverytimeSecondaryCircleButtonType.entries.forEach { type ->
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
                        EverytimeSecondaryCircleButton(
                            text = "버튼",
                            type = type,
                            onClick = { shortClickCount++ }
                        )

                        EverytimeSecondaryCircleButton(
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
