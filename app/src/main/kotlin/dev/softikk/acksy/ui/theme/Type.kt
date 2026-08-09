package dev.softikk.acksy.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import dev.softikk.acksy.R

val inter = FontFamily(
    Font(R.font.inter, FontWeight.Normal), Font(R.font.inter, FontWeight.Medium)
)

val Typography = Typography(
    labelSmall = TextStyle(
        fontFamily = inter, fontWeight = FontWeight.Normal, fontSize = Dimens.Font.labelSmallFontSize
    ), labelMedium = TextStyle(
        fontFamily = inter, fontWeight = FontWeight.Normal, fontSize = Dimens.Font.labelMediumFontSize
    ), labelLarge = TextStyle(
        fontFamily = inter, fontWeight = FontWeight.Medium, fontSize = Dimens.Font.labelLargeFontSize
    ), bodySmall = TextStyle(
        fontFamily = inter, fontWeight = FontWeight.Normal, fontSize = Dimens.Font.bodySmallFontSize
    ), bodyMedium = TextStyle(
        fontFamily = inter,
        fontWeight = FontWeight.Normal,
        fontSize = Dimens.Font.bodyMediumAndEmphasizedFontSize
    ), bodyLarge = TextStyle(
        fontFamily = inter,
        fontWeight = FontWeight.Medium,
        fontSize = Dimens.Font.bodyMediumAndEmphasizedFontSize
    ), headlineMedium = TextStyle(
        fontFamily = inter, fontWeight = FontWeight.Normal, fontSize = Dimens.Font.headlineMediumFontSize
    ), headlineLarge = TextStyle(
        fontFamily = inter, fontWeight = FontWeight.Medium, fontSize = Dimens.Font.headlineLargeFontSize
    ), displaySmall = TextStyle(
        fontFamily = inter, fontWeight = FontWeight.Normal, fontSize = Dimens.Font.displaySmallFontSize
    )
)