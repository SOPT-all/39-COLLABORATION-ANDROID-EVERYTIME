package com.cookandroid.everytime.presentation.review.component.bottomnavigation

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.cookandroid.everytime.R
import com.cookandroid.everytime.core.designsystem.theme.EverytimeTheme
import com.cookandroid.everytime.core.util.noRippleClickable

enum class EverytimeBottomNavigationTabType(
    val label: String,
    @DrawableRes val iconResId: Int,
    val iconLabelSpacing: Dp
) {
    OVERVIEW(
        label = "개요",
        iconResId = R.drawable.ic_review,
        iconLabelSpacing = 2.dp
    ),
    REVIEW(
        label = "강의평",
        iconResId = R.drawable.ic_overview,
        iconLabelSpacing = 0.dp
    ),
    EXAM_INFO(
        label = "시험정보",
        iconResId = R.drawable.ic_search,
        iconLabelSpacing = 0.dp
    )
}

@Composable
fun EverytimeBottomNavigation(
    modifier: Modifier = Modifier
) {
    var selectedTab by rememberSaveable {
        mutableStateOf(EverytimeBottomNavigationTabType.REVIEW)
    }

    Row(
        modifier
            .fillMaxWidth()
            .height(74.dp)
            .dropShadow(
                shape = RectangleShape,
                shadow = Shadow(
                    radius = 10.5.dp,
                    color = Color.Black.copy(alpha = 0.13f)
                ),
            )
            .background(color = EverytimeTheme.colors.white)
            .padding(vertical = 6.dp),
        horizontalArrangement = Arrangement.spacedBy(
            space = 70.dp,
            alignment = Alignment.CenterHorizontally
        ),
        verticalAlignment = Alignment.Top
    ) {
        EverytimeBottomNavigationTabType.entries.forEach { type ->
            EverytimeBottomNavigationTab(
                type = type,
                selected = type == selectedTab,
                onClick = { }
            )
        }
    }
}

@Composable
private fun EverytimeBottomNavigationTab(
    type: EverytimeBottomNavigationTabType,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val backgroundColor = if (selected) EverytimeTheme.colors.primary100 else Color.Transparent
    val contentColor = EverytimeTheme.colors.neutral900

    Column(
        modifier = modifier
            .noRippleClickable(onClick = onClick)
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(start = 10.dp, top = 4.dp, end = 10.dp, bottom = 3.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(space = type.iconLabelSpacing)
    ) {
        Icon(
            painter = painterResource(id = type.iconResId),
            contentDescription = null,
            modifier = Modifier.size(24.dp),
            tint = contentColor
        )

        Text(
            text = type.label,
            color = contentColor,
            style = EverytimeTheme.typography.caption4,
            maxLines = 1
        )
    }
}

@Preview(showBackground = true, widthDp = 375, heightDp = 700)
@Composable
private fun EverytimeBottomNavigationPreview() {
    EverytimeTheme {
        Box(modifier = Modifier.fillMaxSize()) {
            EverytimeBottomNavigation(modifier = Modifier.align(Alignment.BottomCenter))
        }
    }
}
