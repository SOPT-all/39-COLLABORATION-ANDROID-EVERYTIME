package com.cookandroid.everytime.core.designsystem.component.card

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cookandroid.everytime.R
import kotlin.math.roundToInt

enum class EverytimeScoreType(
    @DrawableRes val filled: Int,
    @DrawableRes val half: Int,
    @DrawableRes val empty: Int,
) {
    Small(
        filled = R.drawable.ic_smallstar_fill,
        half = R.drawable.ic_smallstar_half,
        empty = R.drawable.ic_smallstar_empty,
    ),
    Large(
        filled = R.drawable.ic_largestar_fill,
        half = R.drawable.ic_largestar_half,
        empty = R.drawable.ic_largestar_empty,
    ),
}

@Composable
fun EverytimeScore(
    score: Float,
    type: EverytimeScoreType,
    modifier: Modifier = Modifier,
) {
    val halfSteps = (score.coerceIn(0f, 5f) * 2).roundToInt()
    val starSpacing = if (type == EverytimeScoreType.Small) 1.dp else 0.dp

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(starSpacing)
    ) {
        repeat(5) { index ->
            val starRes = when (halfSteps - index * 2) {
                in 2..10 -> type.filled
                1 -> type.half
                else -> type.empty
            }

            Image(
                painter = painterResource(starRes),
                contentDescription = null,
            )
        }
    }
}

@Preview(name = "ScoreSmall", showBackground = true)
@Composable
private fun EverytimeScoreSmallPreview() {
    EverytimeScorePreviewContent(size = EverytimeScoreType.Small)
}

@Preview(name = "ScoreLarge", showBackground = true)
@Composable
private fun EverytimeScoreLargePreview() {
    EverytimeScorePreviewContent(size = EverytimeScoreType.Large)
}

@Composable
private fun EverytimeScorePreviewContent(size: EverytimeScoreType) {
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        repeat(11) { step ->
            EverytimeScore(score = step / 2f, type = size)
        }
    }
}
