package com.cookandroid.everytime.presentation.lecturelist.component.topbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cookandroid.everytime.R
import com.cookandroid.everytime.core.designsystem.component.button.EverytimeSecondaryCircleButton
import com.cookandroid.everytime.core.designsystem.component.button.EverytimeSecondaryCircleButtonType
import com.cookandroid.everytime.core.designsystem.theme.EverytimeTheme

@Composable
fun EverytimeLecturelistTopbar(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(color = EverytimeTheme.colors.white)
            .padding(horizontal = 16.dp, vertical = 14.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_close),
            contentDescription = null
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            EverytimeSecondaryCircleButton(
                text = "마법사",
                type = EverytimeSecondaryCircleButtonType.SM_WHITE,
                onClick = { }
            )

            EverytimeSecondaryCircleButton(
                text = "직접 추가",
                type = EverytimeSecondaryCircleButtonType.SM_WHITE,
                onClick = { }
            )
        }
    }
}

@Preview
@Composable
private fun EverytimeLecturelistTopbarPreview() {
    EverytimeTheme {
        EverytimeLecturelistTopbar()
    }
}
