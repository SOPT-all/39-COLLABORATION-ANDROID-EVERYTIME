package com.cookandroid.everytime.core.designsystem.component.button

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cookandroid.everytime.R
import com.cookandroid.everytime.core.designsystem.theme.EverytimeTheme

@Composable
fun EverytimeIconRightButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val colors = EverytimeTheme.colors
    val shape = RoundedCornerShape(8.dp)
    val interactionSource = remember { MutableInteractionSource() }

    Row(
        modifier = modifier
            .clip(shape)
            .background(colors.neutral100)
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                role = Role.Button,
                onClick = onClick
            )
            .padding(7.dp),
        horizontalArrangement = Arrangement.spacedBy(
            space = 2.dp,
            alignment = Alignment.CenterHorizontally
        ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text,
            style = EverytimeTheme.typography.body2,
            color = colors.neutral800,
            maxLines = 1
        )

        Icon(
            painter = painterResource(id = R.drawable.ic_down),
            contentDescription = null,
            modifier = Modifier.size(15.dp),
            tint = colors.neutral400
        )
    }
}

@Preview(showBackground = true, widthDp = 320)
@Composable
private fun EverytimeIconRightButtonPreview() {
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
                EverytimeIconRightButton(
                    text = "버튼",
                    onClick = { shortClickCount++ }
                )

                EverytimeIconRightButton(
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