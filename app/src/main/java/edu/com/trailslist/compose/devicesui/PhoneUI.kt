package edu.com.trailslist.compose.devicesui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import edu.com.trailslist.compose.appcomponents.TrailItem
import edu.com.trailslist.database.entities.Trail

@Composable
fun PhoneUI(navController: NavController, trails: List<Trail>) {
    LazyColumn {
        items(trails.size) { index ->
            val trail = trails[index]
            Box(modifier = Modifier
                .clickable {
                    navController.navigate(
                        "trailDetails/${trail.id}/${trail.name}/${trail.description}/${trail.image}/${trail.measuredTime}"
                    )
                }) {
                TrailItem(trail = trail)
            }
        }
    }
}
