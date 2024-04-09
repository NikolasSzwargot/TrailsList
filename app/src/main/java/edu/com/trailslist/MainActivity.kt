package edu.com.trailslist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        TrailDatabase.TrailDatabaseProvider.initialize(applicationContext)
        val trailDaoProvider = TrailDatabase.TrailDatabaseProvider
        val viewModel = TrailViewModel(trailDaoProvider)
        //viewModel.deleteTrails()
        viewModel.insertTrailsIfEmpty {
            recreate()
        }
        setContent {
            TrailApp(viewModel = viewModel)
        }
    }
}

@Composable
fun TrailApp(viewModel: TrailViewModel){

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "trailsList") {
        composable("trailsList") {
            TrailsList(navController, viewModel)
        }
        composable( "trailDetails/{trailId}/{trailName}/{trailDescription}/{trailImage}/{trailTime}") { backStackEntry ->
            val trailId = backStackEntry.arguments?.getString("trailId").orEmpty().toInt()
            val trailName = backStackEntry.arguments?.getString("trailName").orEmpty()
            val trailDescription = backStackEntry.arguments?.getString("trailDescription").orEmpty()
            val trailImage = backStackEntry.arguments?.getString("trailImage").orEmpty().toInt()
            val trailTime = backStackEntry.arguments?.getString("trailTime").orEmpty().toLong()

            val trail = Trail(
                trailId,
                trailName,
                trailDescription,
                trailImage,
                trailTime)
            TrailDetails(trail, viewModel)
        }
    }
}