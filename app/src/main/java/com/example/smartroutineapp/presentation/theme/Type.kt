package com.example.smartroutineapp.presentation.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

private val baseTextStyle = TextStyle(
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.W400,
    fontSize = 14.sp,
    lineHeight = 24.sp,
    letterSpacing = 0.sp,
)


// Set of Material typography styles to start with
val Typography = Typography(
    titleLarge = baseTextStyle.copy(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight(590),
        fontSize = 24.sp,
        lineHeight = 34.sp,
    ),
    titleMedium = baseTextStyle.copy(
        fontSize = 14.sp,
        lineHeight = 18.sp,
    ),
    bodyLarge = baseTextStyle.copy(
        fontSize = 16.sp,
        letterSpacing = 0.5.sp
    ),
    bodyMedium = baseTextStyle.copy(
        lineHeight = 20.sp,
    ),
    bodySmall = baseTextStyle
)




