package com.cookandroid.everytime.core.designsystem.component.button

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.cookandroid.everytime.R
import com.cookandroid.everytime.core.designsystem.theme.EverytimeTheme
import com.cookandroid.mjhackathon.core.util.noRippleClickable

@Composable
fun EverytimeIconLeftButton(
    text: String,
    @DrawableRes iconResId: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    iconSize: Dp = 18.dp
) {
    val colors = EverytimeTheme.colors

    Row(
        modifier = modifier
            .minimumInteractiveComponentSize()
            .dropShadow(
                shape = CircleShape,
                shadow = Shadow(
                    radius = 4.dp,
                    color = colors.black,
                    offset = DpOffset(x = 0.dp, y = 2.dp),
                    alpha = 0.18f
                )
            )
            .clip(CircleShape)
            .background(colors.primary900)
            .noRippleClickable(onClick = onClick)
            .semantics { role = Role.Button }
            .heightIn(min = 45.dp)
            .padding(horizontal = 16.dp, vertical = 10.dp),
        horizontalArrangement = Arrangement.spacedBy(
            space = 7.dp,
            alignment = Alignment.CenterHorizontally
        ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = iconResId),
            contentDescription = null,
            modifier = Modifier.size(iconSize),
            tint = colors.white
        )

        Text(
            text = text,
            color = colors.white,
            style = EverytimeTheme.typography.body2,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun EverytimeIconLeftButtonPreview() {
    var lastClicked by remember { mutableStateOf("없음") }

    EverytimeTheme {
        Column(
            modifier = Modifier
                .background(EverytimeTheme.colors.white)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                EverytimeIconLeftButton(
                    text = "버튼",
                    iconResId = R.drawable.ic_graphic,
                    onClick = { lastClicked = "버튼" }
                )

                EverytimeIconLeftButton(
                    text = "평가하기",
                    iconResId = R.drawable.ic_edit,
                    onClick = { lastClicked = "평가하기" },
                    iconSize = 24.dp
                )
            }

            Text(
                text = "마지막 클릭: $lastClicked",
                style = EverytimeTheme.typography.caption1,
                color = EverytimeTheme.colors.neutral800
            )
        }
    }
}