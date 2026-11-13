package com.cookandroid.everytime.core.designsystem.component.button

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.cookandroid.everytime.R
import com.cookandroid.everytime.core.designsystem.theme.EverytimeTheme
import com.cookandroid.mjhackathon.core.util.noRippleClickable

enum class EverytimeIconLeftButtonSize {
    LG,
    SM
}

private data class IconLeftButtonStyle(
    val shape: RoundedCornerShape,
    val minHeight: Dp,
    val padding: PaddingValues,
    val iconSpacing: Dp,
    val containerColor: Color,
    val contentColor: Color,
    val textStyle: TextStyle,
    val shadow: Shadow? = null
)

@Composable
fun EverytimeIconLeftButton(
    text: String,
    size: EverytimeIconLeftButtonSize,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    @DrawableRes iconResId: Int? = null,
    iconSize: Dp = when (size) {
        EverytimeIconLeftButtonSize.LG -> 18.dp
        EverytimeIconLeftButtonSize.SM -> 12.dp
    }
) {
    val colors = EverytimeTheme.colors
    val typography = EverytimeTheme.typography

    val style = when (size) {
        EverytimeIconLeftButtonSize.LG -> IconLeftButtonStyle(
            shape = CircleShape,
            minHeight = 45.dp,
            padding = PaddingValues(horizontal = 16.dp, vertical = 10.dp),
            iconSpacing = 7.dp,
            containerColor = colors.primary900,
            contentColor = colors.white,
            textStyle = typography.body2,
            shadow = Shadow(
                radius = 4.dp,
                color = colors.black,
                offset = DpOffset(x = 0.dp, y = 2.dp),
                alpha = 0.18f
            )
        )

        EverytimeIconLeftButtonSize.SM -> IconLeftButtonStyle(
            shape = RoundedCornerShape(4.dp),
            minHeight = 22.dp,
            padding = PaddingValues(horizontal = 10.dp, vertical = 5.dp),
            iconSpacing = 3.dp,
            containerColor = colors.neutral50,
            contentColor = colors.neutral400,
            textStyle = typography.caption4
        )
    }

    val shadowModifier = style.shadow?.let { shadow ->
        Modifier.dropShadow(shape = style.shape, shadow = shadow)
    } ?: Modifier

    Row(
        modifier = modifier
            .minimumInteractiveComponentSize()
            .then(shadowModifier)
            .clip(style.shape)
            .background(style.containerColor)
            .noRippleClickable(onClick = onClick)
            .semantics { role = Role.Button }
            .heightIn(min = style.minHeight)
            .padding(style.padding),
        horizontalArrangement = Arrangement.spacedBy(
            space = style.iconSpacing,
            alignment = Alignment.CenterHorizontally
        ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (iconResId != null) {
            Icon(
                painter = painterResource(id = iconResId),
                contentDescription = null,
                modifier = Modifier.size(iconSize),
                tint = style.contentColor
            )
        }

        Text(
            text = text,
            color = style.contentColor,
            style = style.textStyle,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun EverytimeIconLeftButtonPreview() {
    var lastClicked by remember { mutableStateOf("없음") }

    EverytimeTheme {
        Column(
            modifier = Modifier
                .background(EverytimeTheme.colors.white)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                EverytimeIconLeftButton(
                    text = "버튼",
                    size = EverytimeIconLeftButtonSize.LG,
                    onClick = { lastClicked = "LG버튼" },
                    iconResId = R.drawable.ic_graphic
                )

                EverytimeIconLeftButton(
                    text = "평가하기",
                    size = EverytimeIconLeftButtonSize.LG,
                    onClick = { lastClicked = "평가하기" },
                    iconResId = R.drawable.ic_edit,
                    iconSize = 24.dp
                )
            }

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                EverytimeIconLeftButton(
                    text = "추천",
                    size = EverytimeIconLeftButtonSize.SM,
                    onClick = { lastClicked = "추천" },
                    iconResId = R.drawable.ic_like
                )

                EverytimeIconLeftButton(
                    text = "신고",
                    size = EverytimeIconLeftButtonSize.SM,
                    onClick = { lastClicked = "신고" }
                )
            }

            Text(
                text = "마지막 클릭: $lastClicked",
                style = EverytimeTheme.typography.caption1,
                color = EverytimeTheme.colors.neutral800
            )
        }
    }
}
