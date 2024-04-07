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
        setContent {
            TrailApp()
        }
    }
}

@Composable
fun TrailApp(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "trailsList") {
        composable("trailsList") {
            TrailsList(navController)
        }
        composable( "trailDetails/{trailName}/{trailDescription}/{trailImage}") { backStackEntry ->
            val trailName = backStackEntry.arguments?.getString("trailName").orEmpty()
            val trailDescription = backStackEntry.arguments?.getString("trailDescription").orEmpty()
            val trailImage = backStackEntry.arguments?.getString("trailImage").orEmpty().toInt()

            val trail = Trail(
                1,
                trailName,
                trailDescription,
                trailImage)
            TrailDetails(trail)
        }
    }
}