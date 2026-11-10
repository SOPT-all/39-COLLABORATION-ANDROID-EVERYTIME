package com.cookandroid.everytime.core.designsystem.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

// 기존 Material 테마에서 참조하는 샘플 색상으로 Theme.kt의 참조를 교체한 뒤 함께 제거한다
val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650A4)
val PurpleGrey40 = Color(0xFF625B71)
val Pink40 = Color(0xFF7D5260)

// DS 팔레트

// Base
private val white = Color(0xFFFFFFFF)

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

/**
 * 화면과 컴포넌트에서 사용하는 역할별 색상 토큰
 * 같은 색상값을 사용하더라도 역할 다르면 별도 프로퍼티로 관리한다
 */
@Immutable
data class EverytimeColors(
    // 기본 화면 배경
    val backgroundDefault: Color,

    // 주요 버튼: 평가하기, 적용 등
    val buttonPrimaryBackground: Color,
    val buttonPrimaryContent: Color,

    // 테두리가 있는 보조 버튼: 마법사, 직접 추가 등
    val buttonSecondaryBackground: Color,
    val buttonSecondaryContent: Color,
    val buttonSecondaryBorder: Color,

    // 하단 탭: 선택 배경과 글자·아이콘
    val tabSelectedBackground: Color,
    val tabContent: Color,

    // 검색 필터 칩: 선택값, 배경, 보조 글자, 삭제 아이콘
    val filterSelectedText: Color,
    val filterChipBackground: Color,
    val filterSecondaryText: Color,
    val filterClearBackground: Color,
    val filterClearContent: Color,

    // 강의평: 본문, 구분선, 정렬 필터, 추천·신고 버튼
    val reviewBodyText: Color,
    val reviewDivider: Color,
    val reviewFilterBackground: Color,
    val reviewActionBackground: Color,
    val reviewActionContent: Color,

    // 수강 학기, 시간 선택 안내 등 보조 설명
    val textDescription: Color,

    // 기본 아이콘과 보조 아이콘
    val iconPrimary: Color,
    val iconSecondary: Color,

    // 상태 점: Figma의 Under·Over variant 이름을 사용한다
    val stateUnder: Color,
    val stateOver: Color,

    // 별점: 평균 점수, 채워진 별, 비어 있는 별
    // 반쪽 별은 active와 inactive 색상을 조합한다
    val ratingScoreText: Color,
    val ratingActive: Color,
    val ratingInactive: Color,

    // 시간표: 격자선, 시간 숫자, 블록 내부 글자
    val timetableGridLine: Color,
    val timetableTimeText: Color,
    val timetableBlockContent: Color,

    // 시간표 블록 배경 색상
    val timetableCoral: Color,
    val timetableRose: Color,
    val timetableOrange: Color,
    val timetableGreen: Color,
    val timetableOlive: Color,
    val timetableMint: Color,
    val timetableBlue: Color,
    val timetablePurple: Color,
    val timetableGray: Color
)

// 역할별 토큰과 DS 팔레트를 연결한 기본 색상 객체
val defaultEverytimeColors = EverytimeColors(
    // 기본 화면 배경
    backgroundDefault = white,

    // 주요 버튼
    buttonPrimaryBackground = primary900,
    buttonPrimaryContent = white,

    // 보조 버튼
    buttonSecondaryBackground = white,
    buttonSecondaryContent = neutral800,
    buttonSecondaryBorder = neutral300,

    // 하단 탭
    tabSelectedBackground = primary100,
    tabContent = neutral900,

    // 검색 필터 칩
    filterSelectedText = primary900,
    filterChipBackground = neutral50,
    filterSecondaryText = neutral700,
    filterClearBackground = neutral300,
    filterClearContent = white,

    // 강의평
    reviewBodyText = neutral800,
    reviewDivider = neutral200,
    reviewFilterBackground = neutral100,
    reviewActionBackground = neutral50,
    reviewActionContent = neutral400,

    // 보조 설명
    textDescription = neutral400,

    // 아이콘
    iconPrimary = neutral900,
    iconSecondary = neutral400,

    // 상태 점
    stateUnder = stateGreen,
    stateOver = stateOrange,

    // 별점
    ratingScoreText = neutral900,
    ratingActive = ratingActive,
    ratingInactive = ratingInactive,

    // 시간표 공통 요소
    timetableGridLine = neutral200,
    timetableTimeText = neutral800,
    timetableBlockContent = white,

    // 시간표 블록 배경
    timetableCoral = timetableCoral,
    timetableRose = timetableRose,
    timetableOrange = timetableOrange,
    timetableGreen = timetableGreen,
    timetableOlive = timetableOlive,
    timetableMint = timetableMint,
    timetableBlue = timetableBlue,
    timetablePurple = timetablePurple,
    timetableGray = timetableGray
)