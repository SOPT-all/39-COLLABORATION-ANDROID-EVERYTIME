package com.cookandroid.everytime.core.designsystem.component.topbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cookandroid.everytime.R
import com.cookandroid.everytime.core.designsystem.component.button.EverytimePrimaryButton
import com.cookandroid.everytime.core.designsystem.theme.EverytimeTheme
import com.cookandroid.everytime.core.util.noRippleClickable

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
            //chip 구현 이후 반영 예정
            Box(
                modifier = Modifier
                    .size(width = 40.dp, height = 14.dp)
                    .background(color = EverytimeTheme.colors.neutral200)
            )

            Box(
                modifier = Modifier
                    .size(width = 40.dp, height = 14.dp)
                    .background(color = EverytimeTheme.colors.neutral200)
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
