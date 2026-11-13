package com.cookandroid.everytime.core.designsystem.component.button

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cookandroid.everytime.R
import com.cookandroid.everytime.core.designsystem.theme.EverytimeTheme
import com.cookandroid.mjhackathon.core.util.noRippleClickable

enum class EverytimeSecondaryRectangleButtonType {
    TEXT_ONLY,
    LEFT_ICON
}

@Composable
fun EverytimeSecondaryRectangleButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    type: EverytimeSecondaryRectangleButtonType =
        EverytimeSecondaryRectangleButtonType.TEXT_ONLY
) {
    val shape = RoundedCornerShape(4.dp)

    Row(
        modifier = modifier
            .clip(shape)
            .background(EverytimeTheme.colors.neutral50)
            .noRippleClickable(onClick = onClick)
            .semantics { role = Role.Button }
            .padding(
                horizontal = 10.dp,
                vertical = 5.dp
            ),
        horizontalArrangement = Arrangement.spacedBy(
            space = 3.dp,
            alignment = Alignment.CenterHorizontally
        ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (type == EverytimeSecondaryRectangleButtonType.LEFT_ICON) {
            Icon(
                painter = painterResource(id = R.drawable.ic_like),
                contentDescription = null,
                modifier = Modifier.size(12.dp),
                tint = EverytimeTheme.colors.neutral400
            )
        }

        Text(
            text = text,
            style = EverytimeTheme.typography.caption4,
            color = EverytimeTheme.colors.neutral400,
            maxLines = 1
        )
    }
}

@Preview(showBackground = true, widthDp = 320)
@Composable
private fun EverytimeSecondaryRectangleButtonPreview() {
    EverytimeTheme {
        Column(
            modifier = Modifier
                .background(EverytimeTheme.colors.white)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            EverytimeSecondaryRectangleButtonType.entries.forEach { type ->
                var shortClickCount by remember { mutableIntStateOf(0) }
                var longClickCount by remember { mutableIntStateOf(0) }

                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = type.name,
                        style = EverytimeTheme.typography.caption2,
                        color = EverytimeTheme.colors.neutral800
                    )

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        EverytimeSecondaryRectangleButton(
                            text = "버튼",
                            onClick = { shortClickCount++ },
                            type = type
                        )

                        EverytimeSecondaryRectangleButton(
                            text = "긴 문구 확인",
                            onClick = { longClickCount++ },
                            type = type
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