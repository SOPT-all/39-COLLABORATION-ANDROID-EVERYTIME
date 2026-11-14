package com.cookandroid.everytime.core.designsystem.component.button

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cookandroid.everytime.core.designsystem.theme.EverytimeTheme
import com.cookandroid.everytime.core.util.noRippleClickable

@Composable
fun EverytimePrimaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Text(
        text = text,
        modifier = modifier
            .background(color = EverytimeTheme.colors.primary900, shape = CircleShape)
            .noRippleClickable(onClick = onClick)
            .padding(vertical = 7.dp, horizontal = 13.dp),
        color = EverytimeTheme.colors.white,
        maxLines = 1,
        style = EverytimeTheme.typography.body3
    )
}

@Preview
@Composable
private fun EverytimePrimaryButtonPreview() {
    EverytimeTheme {
        Column(
            modifier = Modifier.padding(all = 8.dp)
        ) {
            EverytimePrimaryButton(
                text = "버튼",
                onClick = {}
            )
        }
    }
}
