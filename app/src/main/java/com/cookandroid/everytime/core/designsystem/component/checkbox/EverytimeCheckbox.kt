package com.cookandroid.everytime.core.designsystem.component.checkbox

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cookandroid.everytime.R
import com.cookandroid.everytime.core.designsystem.theme.EverytimeTheme
import com.cookandroid.everytime.core.util.noRippleClickable

@Composable
fun EverytimeCheckbox(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    val shape = RoundedCornerShape(4.dp)

    val boxStyle = if (checked) {
        Modifier.background(color = EverytimeTheme.colors.primary900, shape = shape)
    } else {
        Modifier.border(width = 1.dp, color = EverytimeTheme.colors.neutral300, shape = shape)
    }

    Icon(
        painter = painterResource(id = R.drawable.ic_checkbox),
        contentDescription = null,
        modifier = modifier
            .noRippleClickable(onClick = { onCheckedChange(!checked) })
            .then(boxStyle)
            .padding(horizontal = 4.dp, vertical = 5.dp),
        tint = if (checked) {
            EverytimeTheme.colors.white
        } else {
            Color.Transparent
        }
    )
}

@Composable
@Preview(showBackground = true)
private fun EverytimeCheckboxPreview() {
    EverytimeTheme {
        var firstChecked by remember { mutableStateOf(false) }
        var secondChecked by remember { mutableStateOf(true) }

        Row(
            modifier = Modifier
                .background(EverytimeTheme.colors.white)
                .padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            EverytimeCheckbox(
                checked = firstChecked,
                onCheckedChange = { firstChecked = it }
            )

            EverytimeCheckbox(
                checked = secondChecked,
                onCheckedChange = { secondChecked = it }
            )
        }
    }
}
