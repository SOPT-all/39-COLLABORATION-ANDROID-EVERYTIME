package com.cookandroid.everytime.core.designsystem.component.checkbox

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cookandroid.everytime.R
import com.cookandroid.everytime.core.designsystem.theme.EverytimeTheme

@Composable
fun EverytimeCheckbox(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    val colors = EverytimeTheme.colors
    val shape = RoundedCornerShape(4.dp)

    val boxStyle = if (checked) {
        Modifier.background(color = colors.primary900, shape = shape)
    } else {
        Modifier.border(width = 1.dp, color = colors.neutral300, shape = shape)
    }

    Box(
        modifier = modifier
            .toggleable(
                value = checked,
                interactionSource = null,
                indication = null,
                role = Role.Checkbox,
                onValueChange = onCheckedChange
            )
            .minimumInteractiveComponentSize()
            .size(16.dp)
            .then(boxStyle),
        contentAlignment = Alignment.Center
    ) {
        if (checked) {
            Icon(
                painter = painterResource(id = R.drawable.ic_checkbox),
                contentDescription = null,
                modifier = Modifier.size(width = 8.dp, height = 6.dp),
                tint = colors.white
            )
        }
    }
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
