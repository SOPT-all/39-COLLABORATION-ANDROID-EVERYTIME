package com.cookandroid.everytime.core.designsystem.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf

private val LocalEverytimeColorsProvider = staticCompositionLocalOf {
    defaultEverytimeColors
}

private val LocalEverytimeTypographyProvider = staticCompositionLocalOf {
    everytimeTypography
}

@Composable
fun EVERYTIMETheme(
    colors: EverytimeColors = defaultEverytimeColors,
    typography: EverytimeTypography = everytimeTypography,
    content: @Composable () -> Unit
) {
    // 현재 Figma의 라이트 테마를 기준으로 적용하고 동적 색상은 제외
    // 명시하지 않은 Material 색상 역할은 Material 기본값을 사용
    val materialColorScheme = lightColorScheme(
        primary = colors.buttonPrimaryBackground,
        onPrimary = colors.buttonPrimaryContent,
        background = colors.backgroundDefault
    )

    CompositionLocalProvider(
        LocalEverytimeColorsProvider provides colors,
        LocalEverytimeTypographyProvider provides typography
    ) {
        MaterialTheme(
            colorScheme = materialColorScheme,
            content = content
        )
    }
}

// 화면에서는 EverytimeTheme.colors와 EverytimeTheme.typography로 접근한다
object EverytimeTheme {

    val colors: EverytimeColors
        @Composable
        @ReadOnlyComposable
        get() = LocalEverytimeColorsProvider.current

    val typography: EverytimeTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalEverytimeTypographyProvider.current
}