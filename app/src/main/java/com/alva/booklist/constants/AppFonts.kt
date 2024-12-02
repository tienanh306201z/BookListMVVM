package com.alva.booklist.constants

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import com.alva.booklist.R

object AppFonts {
    private val PoppinsFont = FontFamily(
        Font(R.font.poppins_black, weight = FontWeight.Black),
        Font(R.font.poppins_blackitalic, weight = FontWeight.Black, style = FontStyle.Italic),
        Font(R.font.poppins_bold, weight = FontWeight.Bold),
        Font(R.font.poppins_bolditalic, weight = FontWeight.Bold, style = FontStyle.Italic),
        Font(R.font.poppins_extrabold, weight = FontWeight.ExtraBold),
        Font(R.font.poppins_extrabolditalic, weight = FontWeight.ExtraBold, style = FontStyle.Italic),
        Font(R.font.poppins_extralight, weight = FontWeight.ExtraLight),
        Font(R.font.poppins_extralightitalic, weight = FontWeight.ExtraLight, style = FontStyle.Italic),
        Font(R.font.poppins_light, weight = FontWeight.Light),
        Font(R.font.poppins_lightitalic, weight = FontWeight.Light, style = FontStyle.Italic),
        Font(R.font.poppins_medium, weight = FontWeight.Medium),
        Font(R.font.poppins_mediumitalic, weight = FontWeight.Medium, style = FontStyle.Italic),
        Font(R.font.poppins_regular, weight = FontWeight.Normal),
        Font(R.font.poppins_italic, weight = FontWeight.Normal, style = FontStyle.Italic),
        Font(R.font.poppins_semibold, weight = FontWeight.SemiBold),
        Font(R.font.poppins_semibolditalic, weight = FontWeight.SemiBold, style = FontStyle.Italic),
        Font(R.font.poppins_thin, weight = FontWeight.Thin),
    )

    val appTypography = Typography(
        displayLarge = TextStyle.Default.copy(fontFamily = PoppinsFont),
        displayMedium = TextStyle().copy(fontFamily = PoppinsFont),
        displaySmall = TextStyle().copy(fontFamily = PoppinsFont),
        headlineLarge = TextStyle().copy(fontFamily = PoppinsFont),
        headlineMedium = TextStyle().copy(fontFamily = PoppinsFont),
        headlineSmall = TextStyle().copy(fontFamily = PoppinsFont),
        titleLarge = TextStyle().copy(fontFamily = PoppinsFont),
        titleMedium = TextStyle().copy(fontFamily = PoppinsFont),
        titleSmall = TextStyle().copy(fontFamily = PoppinsFont),
        bodyLarge = TextStyle().copy(fontFamily = PoppinsFont),
        bodyMedium = TextStyle().copy(fontFamily = PoppinsFont),
        bodySmall = TextStyle().copy(fontFamily = PoppinsFont),
        labelLarge = TextStyle().copy(fontFamily = PoppinsFont),
        labelMedium = TextStyle().copy(fontFamily = PoppinsFont),
        labelSmall = TextStyle().copy(fontFamily = PoppinsFont),
    )
}