package com.cookandroid.everytime.core.designsystem.component.icon

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.cookandroid.everytime.R
import com.cookandroid.everytime.core.designsystem.theme.EverytimeTheme

enum class EverytimeIconType(
    @DrawableRes val resourceId: Int,
    val size: Dp
) {
    SEARCH(R.drawable.ic_search, 24.dp),

    // 피그마 컴포넌트 이름 기준으로 REVIEW는 정보, OVERVIEW는 겹친 별 모양
    REVIEW(R.drawable.ic_review, 24.dp),
    OVERVIEW(R.drawable.ic_overview, 24.dp),

    CLOSE(R.drawable.ic_close, 24.dp),
    BACK(R.drawable.ic_back, 24.dp),
    COLLAPSE(R.drawable.ic_collapse, 24.dp),
    EDIT(R.drawable.ic_edit, 24.dp),
    CLOSE_MINI(R.drawable.ic_close_mini, 14.dp),
    LIKE(R.drawable.ic_like, 12.dp),

    // 22dp 프레임 안에 5dp 원이 배치된 원본 리소스
    DOT_ORANGE(R.drawable.ic_dot_orange, 22.dp),
    DOT_GREEN(R.drawable.ic_dot_green, 22.dp),

    STAR(R.drawable.ic_star, 11.dp)
}

@Composable
fun EverytimeIcon(
    type: EverytimeIconType,
    contentDescription: String?,
    modifier: Modifier = Modifier
) {
    val colors = EverytimeTheme.colors

    val iconTint = when (type) {
        EverytimeIconType.SEARCH,
        EverytimeIconType.REVIEW,
        EverytimeIconType.OVERVIEW -> colors.neutral900

        EverytimeIconType.CLOSE,
        EverytimeIconType.BACK,
        EverytimeIconType.COLLAPSE -> colors.black

        EverytimeIconType.EDIT -> colors.white
        EverytimeIconType.LIKE -> colors.neutral400
        EverytimeIconType.DOT_ORANGE -> colors.stateOrange
        EverytimeIconType.DOT_GREEN -> colors.stateGreen
        EverytimeIconType.STAR -> colors.ratingActive

        // 회색 원과 흰색 X가 함께 있어 XML 원본 색상 유지
        EverytimeIconType.CLOSE_MINI -> Color.Unspecified
    }

    Icon(
        painter = painterResource(id = type.resourceId),
        contentDescription = contentDescription,
        modifier = modifier.size(type.size),
        tint = iconTint
    )
}



@Preview(showBackground = true, widthDp = 300)
@Composable
private fun EverytimeIconPreview() {
    EverytimeTheme {
        val colors = EverytimeTheme.colors

        Column(
            modifier = Modifier
                .background(colors.white)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            EverytimeIconType.entries.forEach { type ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    // 흰색 작성 아이콘의 확인을 위한 Preview 전용 배경
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .background(
                                if (type == EverytimeIconType.EDIT) {
                                    colors.neutral800
                                } else {
                                    Color.Transparent
                                }
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        EverytimeIcon(
                            type = type,
                            contentDescription = null
                        )
                    }

                    Text(
                        text = "${type.name} · ${type.size.value.toInt()}dp",
                        style = EverytimeTheme.typography.body4,
                        color = colors.neutral900
                    )
                }
            }
        }
    }
}