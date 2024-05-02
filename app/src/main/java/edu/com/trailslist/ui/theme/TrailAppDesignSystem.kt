package edu.com.trailslist.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle

//colors
data class AppColorScheme(
    val background: Color,
    val primary: Color,
    val secondary: Color,
    val icons: Color,
    val borders: Color
)

//typography
data class AppTypography(
    val titleLarge: TextStyle,
    val titleNormal: TextStyle,
    val body: TextStyle,
    val label: TextStyle
)

//shape
data class AppShape(
    val card: Shape,
    val button: Shape
)

val TrailAppColorScheme = staticCompositionLocalOf {
    AppColorScheme(
        background = Color.Unspecified,
        primary = Color.Unspecified,
        secondary = Color.Unspecified,
        icons = Color.Unspecified,
        borders = Color.Unspecified
    )
}

val TrailAppTypography = staticCompositionLocalOf {
    AppTypography(
        titleLarge = TextStyle.Default,
        titleNormal = TextStyle.Default,
        body = TextStyle.Default,
        label = TextStyle.Default
    )
}

val TrailAppShape = staticCompositionLocalOf {
    AppShape(
        card = RectangleShape,
        button = RectangleShape
    )
}