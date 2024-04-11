package edu.com.trailslist

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@SuppressLint("SuspiciousIndentation")
@Composable
fun TrailItem(trail: Trail) {
    val screenInfoData = screenInfo()
    val isTablet = screenInfoData.screenWidthData is screenInfoData.ScreenType.Tablet
            || screenInfoData.screenHeightData is screenInfoData.ScreenType.Tablet

        Box(
            modifier = Modifier
                .fillMaxSize()
                .border(if (isTablet) 2.dp else 1.dp, Color.Black)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(trail.image),
                    contentDescription = "Trail image",
                    modifier = Modifier
                        .size(if (isTablet) 180.dp else 140.dp)
                        .clip(RoundedCornerShape(if (isTablet) 45.dp else 40.dp))
                )
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(
                        text = trail.name,
                        fontSize = if (isTablet) 25.sp else 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                }
            }
        }
}

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

@Composable
fun TrailsList(navController: NavController, viewModel: TrailViewModel) {
    val screenInfoData = screenInfo()
    val trails by  viewModel.trails.collectAsState()
    if (screenInfoData.screenWidthData is screenInfoData.ScreenType.Phone &&
        screenInfoData.screenHeightData is screenInfoData.ScreenType.Phone) {
        PhoneUI(navController = navController, trails)
    }
    else if (screenInfoData.screenWidthData is screenInfoData.ScreenType.Tablet) {
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