package dev.softikk.acksy.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val LightColorScheme = lightColorScheme(
    primary = PastelBlue,
    secondary = SkyBlue,
    onSecondary = Lavender,
    tertiary = IceBlue,
    surface = MilkyWhite,
    onSurface = LightBlack,
    onSurfaceVariant = DarkGray,
    surfaceContainer = Gray,
    error = ErRed
)

private val DarkColorScheme = darkColorScheme(
    primary = PastelBlue,
    secondary = NightlyDarkBlue,
    onSecondary = Lavender,
    tertiary = NightlyBlue,
    surface = LightBlack,
    onSurface = White,
    onSurfaceVariant = LightGray,
    surfaceContainer = NightlyBlack,
    error = ErRed
)

@Composable
fun AcksyTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false, content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme, shapes = Shapes, typography = Typography, content = content
    )
}