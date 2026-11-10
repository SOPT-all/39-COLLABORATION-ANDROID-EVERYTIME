package com.cookandroid.everytime.core.designsystem.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

// DS 팔레트는 파일 내부에서 관리

// Base
private val white = Color(0xFFFFFFFF)
private val black = Color(0xFF000000)

// Primary
private val primary900 = Color(0xFFF91F15)
private val primary100 = Color(0xFFFFF3F3)

// Neutral
private val neutral900 = Color(0xFF292929)
private val neutral800 = Color(0xFF444444)
private val neutral700 = Color(0xFF737373)
private val neutral400 = Color(0xFFA6A6A6)
private val neutral300 = Color(0xFFD6D6D6)
private val neutral200 = Color(0xFFEDEDED)
private val neutral100 = Color(0xFFF2F2F2)
private val neutral50 = Color(0xFFF9F9F9)

// Timetable
private val timetableCoral = Color(0xFFF08676)
private val timetableRose = Color(0xFFDB8C87)
private val timetableOrange = Color(0xFFFCAA67)
private val timetableGreen = Color(0xFFA7C970)
private val timetableOlive = Color(0xFF6C8249)
private val timetableMint = Color(0xFF7CD1C1)
private val timetableBlue = Color(0xFF7AA5E9)
private val timetablePurple = Color(0xFF9E86E1)
private val timetableGray = Color(0xFFA5A5A5)

// Rating
private val ratingActive = Color(0xFFFFC900)
private val ratingInactive = Color(0xFFD6D6D6)

// State
private val stateGreen = Color(0xFF2BCF0E)
private val stateOrange = Color(0xFFFF8F0B)

// Point
private val pointYellow = Color(0xFFFBF3B6)

// 화면에서는 EverytimeTheme.colors를 통해 DS 색상을 사용
@Immutable
data class EverytimeColors(
    // Base
    val white: Color,
    val black: Color,

    // Primary
    val primary900: Color,
    val primary100: Color,

    // Neutral
    val neutral900: Color,
    val neutral800: Color,
    val neutral700: Color,
    val neutral400: Color,
    val neutral300: Color,
    val neutral200: Color,
    val neutral100: Color,
    val neutral50: Color,

    // Timetable
    val timetableCoral: Color,
    val timetableRose: Color,
    val timetableOrange: Color,
    val timetableGreen: Color,
    val timetableOlive: Color,
    val timetableMint: Color,
    val timetableBlue: Color,
    val timetablePurple: Color,
    val timetableGray: Color,

    // Rating
    val ratingActive: Color,
    val ratingInactive: Color,

    // State
    val stateGreen: Color,
    val stateOrange: Color,

    // Point
    val pointYellow: Color
)

// DS 팔레트를 연결한 기본 색상 객체
val defaultEverytimeColors = EverytimeColors(
    // Base
    white = white,
    black = black,

    // Primary
    primary900 = primary900,
    primary100 = primary100,

    // Neutral
    neutral900 = neutral900,
    neutral800 = neutral800,
    neutral700 = neutral700,
    neutral400 = neutral400,
    neutral300 = neutral300,
    neutral200 = neutral200,
    neutral100 = neutral100,
    neutral50 = neutral50,

    // Timetable
    timetableCoral = timetableCoral,
    timetableRose = timetableRose,
    timetableOrange = timetableOrange,
    timetableGreen = timetableGreen,
    timetableOlive = timetableOlive,
    timetableMint = timetableMint,
    timetableBlue = timetableBlue,
    timetablePurple = timetablePurple,
    timetableGray = timetableGray,

    // Rating
    ratingActive = ratingActive,
    ratingInactive = ratingInactive,

    // State
    stateGreen = stateGreen,
    stateOrange = stateOrange,

    // Point
    pointYellow = pointYellow
)