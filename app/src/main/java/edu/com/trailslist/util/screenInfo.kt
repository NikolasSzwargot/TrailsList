package edu.com.trailslist.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun screenInfo(): ScreenInfoData {
    val configuration = LocalConfiguration.current
    return ScreenInfoData(
        screenWidthData = when {
            configuration.screenWidthDp < 600 -> ScreenInfoData.ScreenType.Phone
            configuration.screenWidthDp < 840 -> ScreenInfoData.ScreenType.RotatedPhone
            else -> ScreenInfoData.ScreenType.Tablet
        },
        screenHeightData = when {
            configuration.screenHeightDp < 900 -> ScreenInfoData.ScreenType.Phone
            else -> ScreenInfoData.ScreenType.Tablet
        },
        screenWidth = configuration.screenWidthDp.dp,
        screenHeight = configuration.screenHeightDp.dp
    )

}

data class ScreenInfoData(
    val screenWidthData: ScreenType,
    val screenHeightData: ScreenType,
    val screenWidth: Dp,
    val screenHeight: Dp
    ) {
    sealed class ScreenType {
        object Phone: ScreenType()
        object RotatedPhone: ScreenType()
        object  Tablet: ScreenType()
    }
}