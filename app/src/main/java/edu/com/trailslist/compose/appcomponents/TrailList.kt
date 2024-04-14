package edu.com.trailslist.compose.appcomponents

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import edu.com.trailslist.compose.devicesui.PhoneUI
import edu.com.trailslist.compose.devicesui.TabletUI
import edu.com.trailslist.util.ScreenInfoData
import edu.com.trailslist.util.screenInfo
import edu.com.trailslist.viewmodels.TrailViewModel

@Composable
fun TrailsList(navController: NavController, viewModel: TrailViewModel) {
    val screenInfoData = screenInfo()
    val trails by  viewModel.trails.collectAsState()
    if (screenInfoData.screenWidthData is ScreenInfoData.ScreenType.Phone &&
        screenInfoData.screenHeightData is ScreenInfoData.ScreenType.Phone) {
        PhoneUI(navController = navController, trails)
    }
    else if (screenInfoData.screenWidthData is ScreenInfoData.ScreenType.RotatedPhone &&
        screenInfoData.screenHeightData is ScreenInfoData.ScreenType.Phone) {
        PhoneUI(navController = navController, trails = trails)
    }
    else if (screenInfoData.screenWidthData is ScreenInfoData.ScreenType.Tablet) {
        Row {
            TabletUI(false, trails, viewModel)
        }
    }
    else {
        Column {
            TabletUI(true, trails, viewModel)
        }
    }
}