package com.cookandroid.everytime.core.designsystem.component.card

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cookandroid.everytime.core.designsystem.theme.EverytimeTheme

enum class EverytimeDotState {
    GREEN,
    ORANGE
}

@Composable
fun EverytimeDot(
    state: EverytimeDotState,
    modifier: Modifier = Modifier
) {
    val dotColor = when (state) {
        EverytimeDotState.GREEN -> EverytimeTheme.colors.stateGreen
        EverytimeDotState.ORANGE -> EverytimeTheme.colors.stateOrange
    }

    Box(
        modifier = modifier
            .size(5.dp)
            .background(color = dotColor, shape = CircleShape),
    )
}

@Composable
@Preview
private fun EverytimeDotPreview() {
    EverytimeTheme {
        Column {
            EverytimeDot(
                state = EverytimeDotState.GREEN,
                modifier = Modifier.padding(9.dp)
            )

            EverytimeDot(
                state = EverytimeDotState.ORANGE,
                modifier = Modifier.padding(9.dp)
            )
        }
    }
}
