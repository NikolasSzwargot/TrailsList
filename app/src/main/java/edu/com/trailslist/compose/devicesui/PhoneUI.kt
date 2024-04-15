package edu.com.trailslist.compose.devicesui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import edu.com.trailslist.compose.appcomponents.trailscomponents.TrailItem
import edu.com.trailslist.database.entities.Trail

@Composable
fun PhoneUI(navController: NavController, trails: List<Trail>) {
    LazyVerticalGrid(columns = GridCells.Adaptive(minSize = 140.dp),
        modifier = Modifier.padding(bottom = 80.dp)) {
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
