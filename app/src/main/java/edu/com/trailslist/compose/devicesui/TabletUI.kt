package edu.com.trailslist.compose.devicesui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import edu.com.trailslist.compose.appcomponents.TrailDetails
import edu.com.trailslist.compose.appcomponents.TrailItem
import edu.com.trailslist.database.entities.Trail
import edu.com.trailslist.database.implementation.TrailDatabase
import edu.com.trailslist.viewmodels.TrailViewModel

@Composable
fun TabletUI(vertical: Boolean, trails: List<Trail>, viewModel: TrailViewModel) {
    var expanded by remember { mutableStateOf(true) }
    var selectedTrail by remember { mutableStateOf<Trail?>(null) }
    val selectedTrailId = viewModel.selectedTrailId

    if (selectedTrailId != -1) {
        val database = TrailDatabase.getDatabaseInstance(LocalContext.current)
        val dao = database.trailDao()
        expanded = false
        LaunchedEffect(Unit) {
            selectedTrail = dao.getTrailById(selectedTrailId)
        }
    }

    val fill = if (expanded) 1f else 0.5f

    LazyColumn (modifier = Modifier.fillMaxHeight(if (vertical) fill else 1f)) {
        items(trails.size) { index ->
            val trail = trails[index]
            Box(modifier = Modifier
                .fillMaxWidth(if (vertical) 1f else fill)
                .clickable {
                    expanded = false
                    selectedTrail = trail
                    viewModel.updateSelectedTrailId(selectedTrail!!.id!!)
                }) {
                TrailItem(trail = trail)
            }
        }
    }

    selectedTrail?.let { trail: Trail ->
        TrailDetails(trail = trail, viewModel = viewModel)
    }
}