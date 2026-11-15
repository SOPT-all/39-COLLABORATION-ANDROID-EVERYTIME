package com.cookandroid.everytime.core.designsystem.component.button

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.cookandroid.everytime.R
import com.cookandroid.everytime.core.designsystem.theme.EverytimeTheme
import com.cookandroid.everytime.core.util.noRippleClickable

@Composable
fun EverytimeIconLeftButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .dropShadow(
                shape = CircleShape,
                shadow = Shadow(
                    radius = 4.dp,
                    color = EverytimeTheme.colors.black,
                    offset = DpOffset(x = 0.dp, y = 2.dp),
                    alpha = 0.18f
                )
            )
            .background(
                color = EverytimeTheme.colors.primary900,
                shape = CircleShape
            )
            .noRippleClickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 10.dp),
        horizontalArrangement = Arrangement.spacedBy(space = 7.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_edit),
            contentDescription = null,
            modifier = Modifier.size(24.dp),
            tint = EverytimeTheme.colors.white
        )

        Text(
            text = text,
            color = EverytimeTheme.colors.white,
            style = EverytimeTheme.typography.body2,
            maxLines = 1
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun EverytimeIconLeftButtonPreview() {
    EverytimeTheme {
        Box(
            modifier = Modifier
                .background(color = EverytimeTheme.colors.white)
                .padding(16.dp)
        ) {
            EverytimeIconLeftButton(
                text = "평가하기",
                onClick = {}
            )
        }
    }
}
