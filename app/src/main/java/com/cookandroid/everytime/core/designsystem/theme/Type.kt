package com.cookandroid.everytime.core.designsystem.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.cookandroid.everytime.R

val pretendardFontFamily = FontFamily(
    Font(R.font.pretendard_regular, FontWeight.Normal),
    Font(R.font.pretendard_medium, FontWeight.Medium),
    Font(R.font.pretendard_semibold, FontWeight.SemiBold),
    Font(R.font.pretendard_bold, FontWeight.Bold)
)

// Figma의 Auto 행간은 고정 수치를 지정하지 않고 폰트 기본 메트릭을 사용
private fun typographyStyle(
    fontSize: TextUnit,
    fontWeight: FontWeight,
    letterSpacing: TextUnit = 0.em,
    lineHeight: TextUnit = TextUnit.Unspecified
): TextStyle = TextStyle(
    fontFamily = pretendardFontFamily,
    fontSize = fontSize,
    fontWeight = fontWeight,
    letterSpacing = letterSpacing,
    lineHeight = lineHeight,
    // Android의 추가 폰트 패딩은 제외
    platformStyle = PlatformTextStyle(
        includeFontPadding = false
    ),
    // 지정한 행간의 여유 공간은 위아래로 배분하고 첫 줄과 마지막 줄에서도 유지
    lineHeightStyle = LineHeightStyle(
        alignment = LineHeightStyle.Alignment.Center,
        trim = LineHeightStyle.Trim.None
    )
)

// 리디자인 DS의 13개 스타일
// 화면에서는 EverytimeTheme.typography를 통해 사용
@Immutable
data class EverytimeTypography(
    // Header
    val header1: TextStyle = typographyStyle(
        fontSize = 17.sp,
        fontWeight = FontWeight.SemiBold
    ),

    // SubHeader
    val subHeader1: TextStyle = typographyStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight.Bold
    ),
    val subHeader2: TextStyle = typographyStyle(
        fontSize = 12.sp,
        fontWeight = FontWeight.SemiBold,
        lineHeight = 1.2.em
    ),

    // Body
    val body1: TextStyle = typographyStyle(
        fontSize = 20.sp,
        fontWeight = FontWeight.SemiBold
    ),
    val body2: TextStyle = typographyStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight.Bold,
        letterSpacing = (-0.04).em
    ),
    val body3: TextStyle = typographyStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight.Medium,
        letterSpacing = (-0.04).em
    ),
    val body4: TextStyle = typographyStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal,
        letterSpacing = (-0.04).em,
        lineHeight = 1.46.em
    ),
    val body5: TextStyle = typographyStyle(
        fontSize = 12.sp,
        fontWeight = FontWeight.SemiBold
    ),

    // Caption
    val caption1: TextStyle = typographyStyle(
        fontSize = 12.sp,
        fontWeight = FontWeight.Normal,
        letterSpacing = (-0.02).em
    ),
    val caption2: TextStyle = typographyStyle(
        fontSize = 11.sp,
        fontWeight = FontWeight.Medium,
        letterSpacing = (-0.02).em
    ),
    val caption3: TextStyle = typographyStyle(
        fontSize = 11.sp,
        fontWeight = FontWeight.Normal
    ),
    val caption4: TextStyle = typographyStyle(
        fontSize = 10.sp,
        fontWeight = FontWeight.Medium
    ),
    val caption5: TextStyle = typographyStyle(
        fontSize = 10.sp,
        fontWeight = FontWeight.Normal
    )
)

val everytimeTypography = EverytimeTypography()