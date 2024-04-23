package edu.com.trailslist.navigation

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import edu.com.trailslist.compose.appcomponents.trailscomponents.TrailsList
import edu.com.trailslist.viewmodels.TrailViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TabletNavigation(navController: NavHostController, viewModel: TrailViewModel){
        NavHost(navController = navController, startDestination = "trailList") {
            composable("trailList") {
                viewModel.setToAll()
                TrailsList(navController = navController, viewModel = viewModel)
            }
        }
}

fun onBackPressed(navController: NavController, viewModel: TrailViewModel) {
    if (viewModel.lastOpenedTrailId != -1) {
        viewModel.updateSelectedTrailId(viewModel.lastOpenedTrailId)
        viewModel.updateLastOpenedTrailId(-1)
    } else {
        navController.popBackStack()
    }
}