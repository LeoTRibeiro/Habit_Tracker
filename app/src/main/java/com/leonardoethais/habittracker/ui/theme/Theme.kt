package com.leonardoethais.habittracker.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val CustomColorScheme = lightColorScheme(
    primary = GreenPrimary,
    secondary = GreenSecondary,
    background = BackgroundLight,
    surface = SurfaceLight,
    onPrimary = SurfaceLight,
    onBackground = TextPrimary,
    onSurface = TextPrimary
)

@Composable
fun HabitTrackerTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = CustomColorScheme,
        typography = Typography,
        content = content
    )
}