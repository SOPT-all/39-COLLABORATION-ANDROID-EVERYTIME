package com.cookandroid.everytime.core.designsystem.component.button

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cookandroid.everytime.core.designsystem.theme.EverytimeTheme

@Composable
fun EverytimeSecondaryButton2(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val colors = EverytimeTheme.colors
    val shape = RoundedCornerShape(4.dp)

    Box(
        modifier = modifier
            .clip(shape)
            .background(colors.neutral50)
            .clickable(
                role = Role.Button,
                onClick = onClick
            )
            .padding(
                horizontal = 10.dp,
                vertical = 5.dp
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = EverytimeTheme.typography.caption4,
            color = colors.neutral400,
            maxLines = 1
        )
    }
}

@Preview(showBackground = true, widthDp = 320)
@Composable
private fun EverytimeSecondaryButton2Preview() {
    var shortClickCount by remember { mutableIntStateOf(0) }
    var longClickCount by remember { mutableIntStateOf(0) }

    EverytimeTheme {
        Column(
            modifier = Modifier
                .background(EverytimeTheme.colors.white)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                EverytimeSecondaryButton2(
                    text = "버튼",
                    onClick = { shortClickCount++ }
                )

                EverytimeSecondaryButton2(
                    text = "긴 문구 확인",
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