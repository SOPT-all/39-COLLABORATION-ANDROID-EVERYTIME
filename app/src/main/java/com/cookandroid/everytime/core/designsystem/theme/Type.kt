package com.cookandroid.everytime.core.designsystem.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.cookandroid.everytime.R

val pretendardFontFamily = FontFamily(
    Font(R.font.pretendard_regular, FontWeight.Normal),
    Font(R.font.pretendard_semibold, FontWeight.SemiBold),
    Font(R.font.pretendard_bold, FontWeight.Bold)
)

val typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = pretendardFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
)

data class EverytimeTypography(
    // Header 설정
    val header1: TextStyle = TextStyle(
        fontFamily = pretendardFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp,
        letterSpacing = 0.em
    ),
    val header2: TextStyle = TextStyle(
        fontFamily = pretendardFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 17.sp,
        letterSpacing = 0.em
    ),

    // SubHeader 설정
    val subHeader1: TextStyle = TextStyle(
        fontFamily = pretendardFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        letterSpacing = 0.em
    ),
    val subHeader2: TextStyle = TextStyle(
        fontFamily = pretendardFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 12.sp,
        letterSpacing = 0.em
    ),

    // Body 설정
    val body1: TextStyle = TextStyle(
        fontFamily = pretendardFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        letterSpacing = 0.em
    ),
    val body2: TextStyle = TextStyle(
        fontFamily = pretendardFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        letterSpacing = (-0.04).em
    ),
    val body3: TextStyle = TextStyle(
        fontFamily = pretendardFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 12.sp,
        letterSpacing = 0.em
    ),

    // Caption 설정 
    val caption1: TextStyle = TextStyle(
        fontFamily = pretendardFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        letterSpacing = (-0.02).em
    ),
    val caption2: TextStyle = TextStyle(
        fontFamily = pretendardFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 13.sp,
        letterSpacing = (-0.02).em
    ),
    val caption3: TextStyle = TextStyle(
        fontFamily = pretendardFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 12.sp,
        letterSpacing = 0.em
    ),
    val caption4: TextStyle = TextStyle(
        fontFamily = pretendardFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 12.sp,
        letterSpacing = 0.em
    ),
    val caption5: TextStyle = TextStyle(
        fontFamily = pretendardFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        letterSpacing = 0.em
    ),
    val caption6: TextStyle = TextStyle(
        fontFamily = pretendardFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        letterSpacing = 0.02.em
    ),
    val caption7: TextStyle = TextStyle(
        fontFamily = pretendardFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        letterSpacing = (-0.02).em
    ),
    val caption8: TextStyle = TextStyle(
        fontFamily = pretendardFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        letterSpacing = (-0.04).em
    ),
    val caption9: TextStyle = TextStyle(
        fontFamily = pretendardFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 11.sp,
        letterSpacing = 0.em
    ),
    val caption10: TextStyle = TextStyle(
        fontFamily = pretendardFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 11.sp,
        letterSpacing = (-0.02).em
    ),
    val caption11: TextStyle = TextStyle(
        fontFamily = pretendardFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 10.sp,
        letterSpacing = 0.em
    )
)

val everytimeTypography = EverytimeTypography()