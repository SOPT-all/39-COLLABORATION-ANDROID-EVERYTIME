package com.cookandroid.everytime.presentation.lecturelist.component.checkboxfilterbar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cookandroid.everytime.core.designsystem.theme.EverytimeTheme

@Composable
fun EverytimeCheckboxFilter(
    hideCompleted: Boolean,
    hideRestricted: Boolean,
    onHideCompletedChange: (Boolean) -> Unit,
    onHideRestrictedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    val textColor = EverytimeTheme.colors.neutral900
    val textStyle = EverytimeTheme.typography.caption1

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(space = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(space = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            EverytimeCheckbox(
                checked = hideCompleted,
                onCheckedChange = onHideCompletedChange
            )

            Text(
                text = "이수한 강의 숨기기",
                color = textColor,
                style = textStyle
            )
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(space = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            EverytimeCheckbox(
                checked = hideRestricted,
                onCheckedChange = onHideRestrictedChange
            )

            Text(
                text = "수강 제한 강의 숨기기",
                color = textColor,
                style = textStyle
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun EverytimeCheckboxFilterPreview() {
    EverytimeTheme {
        var hideCompleted by rememberSaveable { mutableStateOf(false) }
        var hideRestricted by rememberSaveable { mutableStateOf(true) }

        EverytimeCheckboxFilter(
            hideCompleted = hideCompleted,
            hideRestricted = hideRestricted,
            onHideCompletedChange = { hideCompleted = it },
            onHideRestrictedChange = { hideRestricted = it },
        )
    }
}
