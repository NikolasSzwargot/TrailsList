package edu.com.trailslist

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import edu.com.trailslist.compose.appcomponents.components.BottomNavBar
import edu.com.trailslist.compose.appcomponents.homecomponents.HomeScreen
import edu.com.trailslist.compose.appcomponents.trailscomponents.TrailDetails
import edu.com.trailslist.compose.appcomponents.trailscomponents.TrailsList
import edu.com.trailslist.database.entities.Trail
import edu.com.trailslist.database.implementation.TrailDatabase
import edu.com.trailslist.viewmodels.TrailViewModel

class MainActivity : ComponentActivity() {

    private val trailDaoProvider = TrailDatabase.TrailDatabaseProvider
//    private val viewModel = viewModels<TrailViewModel>(trailDaoProvider)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        trailDaoProvider.initialize(applicationContext)
        setContent {
            val viewModel = viewModel<TrailViewModel>(
                factory = object : ViewModelProvider.Factory {
                    override fun <T : ViewModel> create(modelClass: Class<T>): T {
                        return TrailViewModel(trailDaoProvider = trailDaoProvider) as T
                    }
                }
            )

            viewModel.insertTrailsIfEmpty {
                recreate()
            }
            TrailApp(viewModel = viewModel)
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TrailApp(viewModel: TrailViewModel){
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomNavBar(navController = navController) }
    ) { _ ->
        NavHost(navController = navController, startDestination = "homeScreen") {
            composable("trailsList") {
                TrailsList(navController, viewModel)
            }
            composable("homeScreen") {
                HomeScreen()
            }
            composable("trailDetails/{trailId}/{trailName}/{trailDescription}/{trailImage}/{trailTime}") { backStackEntry ->
                val trailId = backStackEntry.arguments?.getString("trailId").orEmpty().toInt()
                val trailName = backStackEntry.arguments?.getString("trailName").orEmpty()
                val trailDescription =
                    backStackEntry.arguments?.getString("trailDescription").orEmpty()
                val trailImage = backStackEntry.arguments?.getString("trailImage").orEmpty().toInt()
                val trailTime = backStackEntry.arguments?.getString("trailTime").orEmpty().toLong()

                val trail = Trail(
                    trailId,
                    trailName,
                    trailDescription,
                    trailImage,
                    trailTime
                )
                TrailDetails(trail, viewModel)
            }
        }
    }
}