package com.aura.music.player.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

/**
 * AURA MUSIC: Native Google Android Material 3 Design System & Theme Engine
 * 
 * 1. Professional UI conversion: Entirely curates Web Sans font files by enforcing custom Google Sans Display style structural typography.
 * 2. Chromatic Luxury: Incorporates absolute True Black (#000000) true baseline baseline schemes to maximize OLED battery longevity exactly.
 */

val DarkOledColorScheme = darkColorScheme(
    background = Color(0xFF000000),
    surface = Color(0xFF121214),
    primary = Color(0xFF0A84FF),
    secondary = Color(0xFFBF5AF2),
    tertiary = Color(0xFF30D158),
    onBackground = Color(0xFFFFFFFF),
    onSurface = Color(0xFFFFFFFF),
    surfaceVariant = Color(0xFF1C1C1E),
    onSurfaceVariant = Color(0xFFD1D1D6)
)

val LightColorScheme = lightColorScheme(
    background = Color(0xFFF2F2F7),
    surface = Color(0xFFFFFFFF),
    primary = Color(0xFF0A84FF),
    secondary = Color(0xFFBF5AF2),
    tertiary = Color(0xFF30D158),
    onBackground = Color(0xFF000000),
    onSurface = Color(0xFF000000),
    surfaceVariant = Color(0xFFE5E5EA),
    onSurfaceVariant = Color(0xFF3A3A3C)
)

// Material 3 Structural Google typography baseline fallback setup
val AuraTypography = Typography(
    displayLarge = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Black,
        fontSize = 48.sp,
        letterSpacing = (-0.03).em
    ),
    displayMedium = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 32.sp,
        letterSpacing = (-0.02).em
    ),
    headlineLarge = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        letterSpacing = (-0.01).em
    ),
    bodyLarge = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Monospace,
        fontWeight = FontWeight.Black,
        fontSize = 11.sp,
        letterSpacing = 0.05.em
    )
)

@Composable
fun AuraMusicTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    oledTheme: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        oledTheme -> DarkOledColorScheme
        darkTheme -> DarkOledColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = AuraTypography,
        content = content
    )
}
