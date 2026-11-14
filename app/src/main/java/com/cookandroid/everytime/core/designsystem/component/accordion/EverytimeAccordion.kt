package com.cookandroid.everytime.core.designsystem.component.accordion

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cookandroid.everytime.R
import com.cookandroid.everytime.core.designsystem.theme.EverytimeTheme
import com.cookandroid.everytime.core.util.noRippleClickable

@Composable
fun EverytimeAccordion(
    title: String,
    sectionCount: Int,
    modifier: Modifier = Modifier,
    initiallyExpanded: Boolean = false,
    content: @Composable () -> Unit
) {
    var expanded by rememberSaveable { mutableStateOf(initiallyExpanded) }

    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        AccordionHeader(
            title = title,
            sectionCount = sectionCount,
            expanded = expanded,
            onExpand = { expanded = true }
        )

        if (expanded) {
            content()
            AccordionBottomSection(onClick = { expanded = false })
        }
    }
}

@Composable
private fun AccordionHeader(
    title: String,
    sectionCount: Int,
    expanded: Boolean,
    onExpand: () -> Unit
) {
    val textStyle = EverytimeTheme.typography.body2
    val headerClick = if (expanded) {
        Modifier
    } else {
        Modifier.noRippleClickable(onClick = onExpand)
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(EverytimeTheme.colors.neutral50)
            .then(headerClick)
            .padding(horizontal = 24.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                color = EverytimeTheme.colors.neutral900,
                style = textStyle,
                maxLines = 1,
            )
            Text(
                text = "(총${sectionCount}분반)",
                color = EverytimeTheme.colors.neutral700,
                style = textStyle,
                maxLines = 1
            )
        }

        if (!expanded) {
            AccordionAction(expanded = false)
        }
    }
}

@Composable
private fun AccordionBottomSection(onClick: () -> Unit) {
    AccordionAction(
        expanded = true,
        modifier = Modifier
            .fillMaxWidth()
            .background(EverytimeTheme.colors.neutral200)
            .noRippleClickable(onClick = onClick)
            .padding(vertical = 14.dp, horizontal = 24.dp)
    )
}

@Composable
private fun AccordionAction(
    expanded: Boolean,
    modifier: Modifier = Modifier
) {
    val color = EverytimeTheme.colors.timetableCoral
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.End),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_collapse),
            contentDescription = null,
            modifier = Modifier
                .size(16.dp)
                .rotate(if (expanded) 0f else 180f),
            tint = color
        )
        Text(
            text = if (expanded) "접기" else "펼치기",
            color = color,
            style = EverytimeTheme.typography.body2
        )
    }
}

@Preview(showBackground = true, widthDp = 343)
@Composable
private fun EverytimeAccordionPreview() {
    EverytimeTheme {
        Column(
            modifier = Modifier.background(color = EverytimeTheme.colors.neutral900),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            EverytimeAccordion(
                title = "AI와창의적문제해결",
                sectionCount = 3
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(1.dp)) {
                    repeat(3) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(96.dp)
                                .background(color = EverytimeTheme.colors.black)
                        )
                    }
                }
            }

            EverytimeAccordion(
                title = "AI와창의적문제해결",
                sectionCount = 3,
                initiallyExpanded = true
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(1.dp)) {
                    repeat(3) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(96.dp)
                                .background(color = EverytimeTheme.colors.neutral700)
                        )
                    }
                }
            }
        }
    }
}
