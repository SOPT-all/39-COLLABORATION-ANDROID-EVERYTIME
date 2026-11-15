package com.cookandroid.everytime.core.designsystem.component.button

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.cookandroid.everytime.R
import com.cookandroid.everytime.core.designsystem.theme.EverytimeTheme
import com.cookandroid.mjhackathon.core.util.noRippleClickable

enum class EverytimeBottomNavigationTabType(
    @get:StringRes val labelResId: Int,
    @get:DrawableRes val iconResId: Int,
    val minHeight: Dp,
    val iconLabelSpacing: Dp
) {
    OVERVIEW(
        labelResId = R.string.bottom_navigation_tab_overview,
        iconResId = R.drawable.ic_review,
        minHeight = 45.dp,
        iconLabelSpacing = 2.dp
    ),
    REVIEW(
        labelResId = R.string.bottom_navigation_tab_review,
        iconResId = R.drawable.ic_overview,
        minHeight = 47.dp,
        iconLabelSpacing = 0.dp
    ),
    EXAM_INFO(
        labelResId = R.string.bottom_navigation_tab_exam_info,
        iconResId = R.drawable.ic_search,
        minHeight = 43.dp,
        iconLabelSpacing = 0.dp
    )
}

@Composable
fun EverytimeBottomNavigationTab(
    type: EverytimeBottomNavigationTabType,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val colors = EverytimeTheme.colors
    val backgroundColor = if (selected) colors.primary100 else Color.Transparent

    Column(
        modifier = modifier
            .noRippleClickable(onClick = onClick)
            .widthIn(min = 53.dp)
            .heightIn(min = type.minHeight)
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(start = 4.dp, top = 4.dp, end = 4.dp, bottom = 3.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(
            space = type.iconLabelSpacing,
            alignment = Alignment.CenterVertically
        )
    ) {
        Icon(
            painter = painterResource(id = type.iconResId),
            contentDescription = null,
            modifier = Modifier.size(24.dp),
            tint = colors.neutral900
        )

        Text(
            text = stringResource(id = type.labelResId),
            color = colors.neutral900,
            style = EverytimeTheme.typography.caption4,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun EverytimeBottomNavigationTabPreview() {
    EverytimeTheme {
        var selectedTab by remember {
            mutableStateOf(EverytimeBottomNavigationTabType.REVIEW)
        }

        Row(
            modifier = Modifier.padding(12.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            EverytimeBottomNavigationTabType.entries.forEach { type ->
                EverytimeBottomNavigationTab(
                    type = type,
                    selected = type == selectedTab,
                    onClick = { selectedTab = type }
                )
            }
        }
    }
}
