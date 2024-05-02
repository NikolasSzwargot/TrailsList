package edu.com.trailslist.compose.appcomponents.homecomponents

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import edu.com.trailslist.util.ScreenInfoData
import edu.com.trailslist.util.screenInfo
import edu.com.trailslist.viewmodels.TrailViewModel

@Composable
fun HomeScreen(viewModel: TrailViewModel) {
    val screenInfoData = screenInfo()
    val logoAlpha = remember {
        Animatable(0f)
    }

    Box(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        if (screenInfoData.screenWidthData is ScreenInfoData.ScreenType.Phone) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                item {
                    Logo(Modifier, viewModel)
                }
                item {
                    ApplicationDescription(Modifier)
                }
            }
        }
        else {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
            ) {
                Logo(Modifier, viewModel)
                ApplicationDescription(Modifier)
                }
            }
        }
}