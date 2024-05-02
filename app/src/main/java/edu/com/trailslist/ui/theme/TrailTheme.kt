package edu.com.trailslist.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val defaultColorScheme = AppColorScheme(
    background = Color.White,
    primary = Purple40,
    secondary = Color.Gray,
    icons = Color.Black,
    borders = Color(0, 153, 76)
)

private val typography = AppTypography(
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp
    ),
    titleNormal = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp
    ),
    body = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp
    ),
    label = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 20.sp
    ),

)

private val shape = AppShape(
    card = RoundedCornerShape(16.dp),
    button = RoundedCornerShape(50)
)

@Composable
fun TrailTheme(
    content: @Composable () -> Unit
) {
    val colorScheme = defaultColorScheme
    CompositionLocalProvider(
        TrailAppColorScheme provides colorScheme,
        TrailAppTypography provides typography,
        TrailAppShape provides shape,
        content = content
    )
}

object TrailTheme {
    val colorScheme: AppColorScheme
        @Composable get() = TrailAppColorScheme.current
    val typography: AppTypography
        @Composable get() = TrailAppTypography.current
    val shape: AppShape
        @Composable get() = TrailAppShape.current
}