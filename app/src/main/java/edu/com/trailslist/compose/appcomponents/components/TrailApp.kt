package edu.com.trailslist.compose.appcomponents.components

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import edu.com.trailslist.navigation.PhoneNavigation
import edu.com.trailslist.navigation.TabletNavigation
import edu.com.trailslist.util.ScreenInfoData
import edu.com.trailslist.util.screenInfo
import edu.com.trailslist.viewmodels.TrailViewModel

@Composable
fun TrailApp(viewModel: TrailViewModel){
    val screenInfoData = screenInfo()
    val navController = rememberNavController()
    if ((screenInfoData.screenWidthData is ScreenInfoData.ScreenType.Phone || screenInfoData.screenWidthData is ScreenInfoData.ScreenType.RotatedPhone) &&
        (screenInfoData.screenHeightData is ScreenInfoData.ScreenType.Phone || screenInfoData.screenHeightData is ScreenInfoData.ScreenType.RotatedPhone)) {
        PhoneNavigation(navController = navController, viewModel = viewModel)
    }
    else {
        TabletNavigation(navController = navController, viewModel = viewModel)
    }
}