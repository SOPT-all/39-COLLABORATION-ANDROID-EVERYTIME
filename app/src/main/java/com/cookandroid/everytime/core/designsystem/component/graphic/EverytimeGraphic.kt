package com.cookandroid.everytime.core.designsystem.component.graphic

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cookandroid.everytime.R
import com.cookandroid.everytime.core.designsystem.theme.EverytimeTheme

@Composable
fun EverytimeGraphic(
    modifier: Modifier = Modifier
) {
    Icon(
        painter = painterResource(id = R.drawable.ic_graphic),
        contentDescription = null,
        modifier = modifier.size(60.dp),
        tint = EverytimeTheme.colors.neutral300
    )
}


@Preview(showBackground = true)
@Composable
private fun EverytimeGraphicPreview() {
    EverytimeTheme {
        EverytimeGraphic()
    }
}