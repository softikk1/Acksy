package dev.softikk.acksy.ui.theme

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

object Dimens {
    object Anim {
        const val MEDIUM_ANIM_DURATION_MILLIS = 400
        const val LARGE_DURATION_MILLIS = 600
    }

    object Shapes {
        val xsShape = 2.dp
        val smallShape = 4.dp
        val mediumShape = 8.dp
        val largeShape = 16.dp
    }

    object Font {
        val labelSmallFontSize = 11.sp
        val labelMediumFontSize = 12.sp
        val labelLargeFontSize = 14.sp
        val bodySmallFontSize = 15.sp
        val bodyMediumAndEmphasizedFontSize = 16.sp
        val headlineMediumFontSize = 20.sp
        val headlineLargeFontSize = 32.sp
        val displaySmallFontSize = 36.sp
    }

    object Paddings {
        val xsPadding = 2.dp
        val smallPadding = 8.dp
        val mediumPadding = 16.dp
        val largePadding = 24.dp
    }

    val heightComponent = 56.dp
    val maxWidthElement = 400.dp
}