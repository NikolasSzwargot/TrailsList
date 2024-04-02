package edu.com.trailslist

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun screenInfo(): screenInfoData {
    val configuration = LocalConfiguration.current
    return screenInfoData(
        screenWidthData = when {
            configuration.screenWidthDp < 840 -> screenInfoData.ScreenType.Phone
            else -> screenInfoData.ScreenType.Tablet
        },
        screenHeightData = when {
            configuration.screenHeightDp < 900 -> screenInfoData.ScreenType.Phone
            else -> screenInfoData.ScreenType.Tablet
        },
        screenWidth = configuration.screenWidthDp.dp,
        screenHeight = configuration.screenHeightDp.dp
    )

}

data class screenInfoData(
    val screenWidthData: ScreenType,
    val screenHeightData: ScreenType,
    val screenWidth: Dp,
    val screenHeight: Dp
    ) {
    sealed class ScreenType {
        object Phone: ScreenType()
        object  Tablet: ScreenType()
    }
}